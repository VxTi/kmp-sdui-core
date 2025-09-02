package nl.q42.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import nl.q42.ViewController
import platform.UIKit.UITabBarController
import platform.UIKit.UITabBarControllerDelegateProtocol
import platform.UIKit.UITabBarItem
import platform.UIKit.UIViewController
import platform.UIKit.tabBarItem
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun NavigationBar(controller: ViewController) {
    val tabs by controller.tabs.collectAsState()
    val selectedTabIndex by controller.selectedTabIndex.collectAsState()

    val tabBarController = remember {
        // Create the TabBarController and its delegate only once
        val delegate = object : NSObject(), UITabBarControllerDelegateProtocol {
            override fun tabBarController(
                tabBarController: UITabBarController,
                didSelectViewController: UIViewController
            ) {
                // When a tab is selected in UIKit, update the state in Compose
                controller.setSelectedTabIndex(tabBarController.selectedIndex.toInt())
            }
        }

        UITabBarController().apply {
            this.delegate = delegate
        }
    }

    UIKitViewController(
        factory = { tabBarController },
        modifier = Modifier,
        update = {
            // The update block is called on every recomposition.
            // This is the ideal place to sync the UIKit view with the Compose state.

            // 1. Sync the selected tab index
            // Check if the selected index in UIKit is different from our state
            if (tabBarController.selectedIndex.toInt() != selectedTabIndex) {
                tabBarController.setSelectedIndex(selectedTabIndex.toULong())
            }

            // 2. Sync the view controllers (tabs)
            // Check if the number of tabs or their titles have changed
            val currentViewControllers =
                tabBarController.viewControllers?.mapNotNull { it as? UIViewController }
                    ?: emptyList()
            if (currentViewControllers.size != tabs.size || currentViewControllers.map { it.title } != tabs.map { it.title }) {

                // If they have changed, create new view controllers
                val newViewControllers = tabs.mapIndexed { index, tab ->
                    // Use ComposeUIViewController to host Composable content
                    ComposeUIViewController {
                        controller.getScreenById(tab.screenId)?.let { screen ->
                            DynamicContentList(screen.content, controller)
                        }
                    }.apply {
                        this.title = tab.title
                        this.tabBarItem = UITabBarItem(
                            title = tab.title,
                            image = null, // TODO: Add UIImage for icon
                            tag = index.toLong()
                        )
                    }
                }
                tabBarController.setViewControllers(newViewControllers, animated = false)
            }
        }
    )
}