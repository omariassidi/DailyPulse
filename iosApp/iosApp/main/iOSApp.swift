import SwiftUI
import shared

@main
struct iOSApp: App {
    init () {
        doInitKoin()
        
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
