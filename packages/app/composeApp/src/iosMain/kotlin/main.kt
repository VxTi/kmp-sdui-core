import androidx.compose.ui.window.ComposeUIViewController
import nl.q42.App
import nl.q42.ViewController
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App(ViewController())
    }
}