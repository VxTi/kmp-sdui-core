package nl.q42.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import kotlinx.cinterop.ExperimentalForeignApi
import nl.q42.ViewController
import platform.UIKit.UITabBarController
import platform.UIKit.UIViewController

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun NavigationBar(controller: ViewController) {
    // It is a dummy implementation of a navigation bar, as it is not used in the iOS app
    // The navigation bar is implemented in the iOS app using SwiftUI
    // This implementation is only used for testing purposes

    val viewControllers = controller.tabs.value.map { tab ->
        UIViewController().apply {
            // Set the title of the view controller to the title of the tab
            // This title will be displayed in the navigation bar
            this.title = tab.title
        }
    }

    // Create a tab bar controller
    val tabBarController = UITabBarController().apply {
        // Set the view controllers of the tab bar controller
        this.viewControllers = viewControllers

        // Set the selected index of the tab bar controller to the selected tab index of the view controller
        this.selectedIndex = controller.selectedTabIndex.value.toULong();
    }

    // Embed the tab bar controller in a Composable
    UIKitViewController(
        factory = { tabBarController },
        modifier = Modifier,
        update = { /* Update the tab bar controller if needed */ }
    )
}