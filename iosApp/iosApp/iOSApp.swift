import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

 //this alos happens once in an iosApp lifetime
    init() {
    //what happens is that we call the file InitKoinKt then access the function inside it
        InitKoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}