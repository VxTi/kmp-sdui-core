package nl.q42.core.uri;

import lombok.AllArgsConstructor;
import nl.q42.core.contentful.CFContentScreen;
import nl.q42.core.support.SupportedLocale;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class LookupDestination
{

    public String type;

    public static class Localized extends LookupDestination
    {
        public final String          originalUrl;
        public final SupportedLocale locale;

        public Localized(String type, String originalUrl, SupportedLocale locale)
        {
            super(type);
            this.originalUrl = originalUrl;
            this.locale      = locale;
        }
    }

    public static class External extends LookupDestination
    {
        public String url;

        public External(String url)
        {
            super("external");
            this.url = url;
        }
    }

    public static class Basket extends LookupDestination
    {
        public Basket()
        {
            super("basket");
        }
    }

    public static class Search extends LookupDestination
    {
        public SearchParams params;

        public Search(SearchParams params)
        {
            super("search");
            this.params = params;
        }
    }

    public static class Product extends Localized
    {
        public final String productId;

        public Product(String originalUrl, SupportedLocale locale, String productId)
        {
            super("product", originalUrl, locale);
            this.productId = productId;
        }
    }

    public static class VouchersOverview extends LookupDestination
    {
        public PresentationStyle presentationStyle;

        public VouchersOverview(PresentationStyle presentationStyle)
        {
            super("vouchers");
            this.presentationStyle = presentationStyle;
        }

        public enum PresentationStyle
        {
            MODAL,
            DETAIL
        }
    }

    public static class PromotionsOverview extends LookupDestination
    {
        public PromotionsOverview()
        {
            super("promotions");
        }
    }

    public static class VoucherDetails extends Localized
    {
        public final String voucherId;

        public VoucherDetails(String originalUrl, SupportedLocale locale, String voucherId)
        {
            super("voucher", originalUrl, locale);
            this.voucherId = voucherId;
        }
    }

    public static class ContentScreen extends Localized
    {
        public final     String          slug;
        public @Nullable CFContentScreen prefetchedContent;

        public ContentScreen(String originalUrl, SupportedLocale locale, String slug, @Nullable CFContentScreen prefetchedContent)
        {
            super("content", originalUrl, locale);
            this.slug              = slug;
            this.prefetchedContent = prefetchedContent;
        }
    }
}
