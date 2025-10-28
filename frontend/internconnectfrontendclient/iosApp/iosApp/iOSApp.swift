import SwiftUI

@main
struct iOSApp: App {

	init(){
		FrameworksKt.initKoin(baseUrl: "localhost:8800")
	}

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}