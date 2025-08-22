package nl.q42.sdui.event;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@RequiredArgsConstructor
public class AnalyticsFirebaseEcomEvent implements AnalyticsEvent, Serializable
{
    public final     String                type = "FIREBASE_ECOM";
    public final     EventName             name;
    public final     FirebaseEcomEventData ecomData;
    public @Nullable Map<String, String>   customData;

    public enum EventName
    {
        ADD_TO_CART,
        REMOVE_FROM_CART,
        VIEW_CART,
        BEGIN_CHECKOUT,
        PURCHASE,
        REFUND,
        VIEW_ITEM,
        SELECT_ITEM,
        VIEW_ITEM_LIST,
        ADD_TO_WISHLIST,
        ADD_PAYMENT_INFO,
        ADD_SHIPPING_INFO,
        SELECT_PROMOTION,
        VIEW_PROMOTION,
        SELECT_CONTENT,
        SHARE;
    }

}
