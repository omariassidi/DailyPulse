//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Omar Assidi on 24/09/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

@Observable
class ArticlesViewModelWrapper {
    let viewModel: ArticlesViewModel
    var articleState: ArticlesState
    
    init() {
        viewModel = ArticlesViewModel()
        articleState = viewModel.state.value
    }
        
    
    func startObserving() {
        Task {
            for await articlesS in viewModel.state  {
                self.articleState = articlesS
            }
        }
    }
}

struct ArticlesScreen: View {
    var body: some View {
        ArticlesScreenContent(viewModel: ArticlesViewModelWrapper()).navigationTitle("Articles")
    }
    
    struct ArticlesScreenContent: View {
        @State var viewModel: ArticlesViewModelWrapper
        var body: some View {
            VStack {
                if viewModel.articleState.isLoading {
                    Loader()
                }
                if viewModel.articleState.errorMessage != nil {
                    ErrorView(message: viewModel.articleState.errorMessage!)
                }
                if viewModel.articleState.articles.count > 0 {
                    ScrollView {
                        LazyVStack(spacing: 10.0) {
                            ForEach(viewModel.articleState.articles, id: \.self) { article in
                                ArticleRow(article: article)
                            }
                        }
                    }
                }
                
            }.onAppear {
                viewModel.startObserving()
            }
        }
    }
    
    struct Loader: View {
        var body: some View {
            ProgressView()
        }
    }
    
    struct ErrorView: View {
        var message: String
        var body: some View {
            Text(message).font(.title)
        }
    }
    
    struct ArticleRow: View {
        var article: Article
        var body: some View {
            VStack(alignment: .leading, spacing: 8.0) {
                AsyncImage(url: URL(string: article.imageUrl)) { phase in
                    if phase.image != nil {
                        phase.image!.resizable().aspectRatio(contentMode: .fit)
                    } else if phase.error != nil {
                        Text("Failed to load image")
                    } else {
                        ProgressView()
                    }
                }
                Text(article.title).font(.title).fontWeight(.bold)
                Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(Color.gray)
            }.padding()
        }
    }
}

