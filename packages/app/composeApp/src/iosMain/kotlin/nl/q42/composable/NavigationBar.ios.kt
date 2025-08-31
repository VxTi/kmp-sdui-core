package nl.q42.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import kotlinx.cinterop.ExperimentalForeignApi
import nl.q42.ViewController
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun NavigationBar(controller: ViewController) {
    val tabs by controller.tabs.collectAsState()
    val selectedTabIndex by controller.selectedTabIndex.collectAsState()

    val tabBarController = remember {
        UITabBarController().apply {
            // Configure tab bar appearance
            tabBar.backgroundColor = UIColor.systemBackgroundColor
            tabBar.tintColor = UIColor.systemBlueColor
        }
    }

    // Create a delegate class
    val delegate = remember {
        object : NSObject(), UITabBarControllerDelegateProtocol {
            override fun tabBarController(
                tabBarController: UITabBarController,
                didSelectViewController: UIViewController
            ) {
                val newIndex = tabBarController.selectedIndex.toInt()
                if (newIndex != selectedTabIndex) {
                    controller.setSelectedTabIndex(newIndex)
                }
            }
        }
    }

    // Update view controllers when tabs change
    LaunchedEffect(tabs) {
        val viewControllers = tabs.mapIndexed { index, tab ->
            UIViewController().apply {
                title = tab.title

                // Create tab bar item
                val tabBarItem = UITabBarItem(
                    title = tab.title,
                    image = null, // You can add icons here if needed
                    tag = index.toLong()
                )

                this.tabBarItem = tabBarItem

                // Set a background color or view to make it visible
                view.backgroundColor = UIColor.systemBackgroundColor
            }
        }

        tabBarController.setViewControllers(viewControllers, animated = false)
    }

    // Set the delegate
    LaunchedEffect(delegate) {
        tabBarController.delegate = delegate
    }

    // Update selected index when it changes
    LaunchedEffect(selectedTabIndex) {
        if (selectedTabIndex < tabs.size && selectedTabIndex >= 0) {
            tabBarController.selectedIndex = selectedTabIndex.toULong()
        }
    }

    UIKitViewController(
        factory = { tabBarController },
        modifier = Modifier,
        update = { updatedController ->
            // Update selected index if it has changed
            if (updatedController.selectedIndex.toInt() != selectedTabIndex) {
                updatedController.selectedIndex = selectedTabIndex.toULong()
            }
        }
    )
}