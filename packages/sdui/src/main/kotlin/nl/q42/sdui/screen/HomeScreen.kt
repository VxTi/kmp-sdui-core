package nl.q42.sdui.screen

import nl.q42.common.ButtonComponent
import nl.q42.common.ButtonVariant
import nl.q42.common.CurrencyType
import nl.q42.common.ServerComponent
import nl.q42.common.ImageComponent
import nl.q42.common.ListItemContainer
import nl.q42.common.NavigationEvent
import nl.q42.common.ScrollableContainer
import nl.q42.common.SearchBarComponent
import nl.q42.common.SpacerComponent
import nl.q42.common.TextComponent
import nl.q42.common.TransactionListItem
import nl.q42.core.AppRequestContext
import org.springframework.stereotype.Component

@Component
class HomeScreen : ScreenInstance {

    override fun content(context: AppRequestContext): List<ServerComponent> {
        return listOf(
            SearchBarComponent(placeholder = "Search...", contentId = "search-2"),
            SpacerComponent(size = 3, contentId = "spacer-2"),
            ListItemContainer(
                listOf(
                    TransactionListItem(
                        "Transaction 1",
                        "Description 1",
                        "-$10.00",
                        CurrencyType.USD,
                        "https://mediamarkt.nl/public/manifest/favicon-Media-48x48.png",
                        listOf(
                            NavigationEvent("search")
                        ),
                        itemId = "item-1"
                    ),
                    TransactionListItem(
                        "Transaction 2",
                        "Description 2",
                        "-$10.00",
                        CurrencyType.USD,
                        "https://mediamarkt.nl/public/manifest/favicon-Media-48x48.png",
                        itemId = "item-2"
                    ),
                    TransactionListItem(
                        "Transaction 3",
                        "Description 3",
                        "-$10.00",
                        CurrencyType.USD,
                        "https://mediamarkt.nl/public/manifest/favicon-Media-48x48.png",
                        itemId = "item-3"
                    ),
                ),
                "Recent Transactions",
                "test-list-item-container"
            ),
            ButtonComponent(
                "Hello world from SDUI",
                ButtonVariant.NORMAL,
                listOf(NavigationEvent("profile")),
                "test-button"
            ),
        )
    }

    override fun name(): String {
        return SCREEN_IDENTIFIER
    }

    companion object {
        const val SCREEN_IDENTIFIER: String = "home"
    }
}
