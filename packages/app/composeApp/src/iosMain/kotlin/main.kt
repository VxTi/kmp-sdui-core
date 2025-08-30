import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import nl.q42.App
import nl.q42.ViewController
import nl.q42.core.AppInstance
import platform.UIKit.UIViewController

@Composable
fun MainViewController(): UIViewController = ComposeUIViewController {

    lateinit var instance: AppInstance

    App(ViewController(instance));

    AppInstance.fromConfig {
        cfg -> instance = cfg
    }
}
