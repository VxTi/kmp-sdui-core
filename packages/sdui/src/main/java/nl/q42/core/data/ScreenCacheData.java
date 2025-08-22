package nl.q42.core.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
public class ScreenCacheData
{
    public @Nullable String[] favoriteProductIds;
    public @Nullable BasketItem[] basketItems;
    public int basketItemCount = 0;
    public int pointBalance = 0;
}
