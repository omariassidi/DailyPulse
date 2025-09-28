import SwiftUI
import shared

struct ContentView: View {
    @State var showSheet = false
    var body: some View {
        NavigationStack {
            ArticlesScreen()
                .popover(isPresented: $showSheet) {
                    AboutScreen()
                }
                .toolbar {
                    ToolbarItem(placement: .navigationBarTrailing) {
                        Button(action: {
                            showSheet = true
                        }) {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        }
                    }
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
