import UIKit
import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        Frameworks_iosKt.doInitKoinIos()
    }
    var body: some Scene {
        WindowGroup {		
            ContentView()
        }
    }
}
