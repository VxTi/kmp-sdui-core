import androidx.compose.ui.window.ComposeUIViewController
import nl.q42.App
import nl.q42.ViewController
import nl.q42.core.AppInstance
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App(ViewController(AppInstance.fromConfig()))
    }
}