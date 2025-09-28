//
//  AboutScreen.swift
//  iosApp
//
//  Created by Omar Assidi on 19/09/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutScreen: View {
    @Environment(\.dismiss) private var dismiss
    var body: some View {
        NavigationStack {
            AboutScreenContent().navigationTitle("About Device")
                .toolbar {
                    ToolbarItem(placement: .primaryAction) {
                        Button(action: {
                            dismiss()
                        }) {
                            Text("Done").bold()
                        }
                    }
                }
        }
    }
}

struct AboutScreenContent: View {
    var items = makeItems()
    var body: some View {
        List(items, id: \.0) { item in
            RowView(item: item)
        }
            
    }
}


struct RowView: View {
    var item: (String, String)
    var body: some View {
        VStack(alignment: .leading) {
            Text(item.0).font(.footnote).foregroundStyle(Color.gray)
            Text(item.1).font(.body)
        }
    }
}
func makeItems() -> [(String, String)] {
    let platform = Platform()
    return [
        ("Operating System", "\(platform.osName) \(platform.osVersion)"),
        ("Device", platform.deviceModel),
        ("Density", String(platform.density))
    ]
    
}

