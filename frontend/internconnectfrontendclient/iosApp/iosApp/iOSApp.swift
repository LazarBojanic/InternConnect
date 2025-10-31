import UIKit
import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        FrameworksKt.doInitKoinIos()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
