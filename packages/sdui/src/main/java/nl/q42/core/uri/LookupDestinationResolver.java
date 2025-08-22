package nl.q42.core.uri;

import nl.q42.core.HemaWebEnvironment;
import nl.q42.core.contentful.CFInspectUrlResult;
import nl.q42.core.search.SFFilterKey;
import nl.q42.core.support.SupportedLocale;
import nl.q42.core.util.NumberUtils;
import nl.q42.core.util.UrlUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nl.q42.core.uri.URIParameters.getQueryParameterSegments;
import static nl.q42.core.uri.URIParameters.getQueryParameterValue;
import static nl.q42.core.util.UrlUtils.cleanUrlPathname;

public class LookupDestinationResolver
{
  static Map<String, Boolean> systemAttributes;
  static Function<String, LookupDestination>[] lookupResolvers = new Function[6];

  static
  {
    lookupResolvers[0] = LookupDestinationResolver::checkForLookupDestinationContentScreen;
    lookupResolvers[1] = LookupDestinationResolver::checkForLookupDestinationVoucherDetails;
    lookupResolvers[2] = LookupDestinationResolver::checkForLookupDestinationVouchersOverview;
    lookupResolvers[3] = LookupDestinationResolver::checkForLookupDestinationPromotionsOverview;
    lookupResolvers[4] = LookupDestinationResolver::checkForLookupDestinationBasket;
    lookupResolvers[5] = LookupDestinationResolver::checkForLookupDestinationProduct;

    // hardcoded list from Emakina, should not change (often)
    systemAttributes = Stream.of(
        "EAN",
        "ID",
        "UPC",
        "UUID",
        "available",
        "brand",
        "creationDate",
        "facebookEnabled",
        "image",
        "lastModified",
        "localizedTaxClassID",
        "longDescription",
        "manufacturerName",
        "manufacturerSKU",
        "minOrderQuantity",
        "name",
        "onlineFlag",
        "onlineFrom",
        "onlineTo",
        "pageDescription",
        "pageKeywords",
        "pageTitle",
        "pageURL",
        "pinterestEnabled",
        "searchPlacement",
        "searchRank",
        "searchable",
        "searchableIfUnavailable",
        "shortDescription",
        "siteMapChangeFrequency",
        "siteMapIncluded",
        "siteMapPriority",
        "stepQuantity",
        "storeForcePriceEnabled",
        "storeNonDiscountableEnabled",
        "storeNonInventoryEnabled",
        "storeNonRevenueEnabled",
        "storeReceiptName",
        "storeTaxClass",
        "taxClassID",
        "template",
        "thumbnail",
        "unit",
        "unitMeasure",
        "unitQuantity"
    ).collect(Collectors.toMap(key -> key, key -> true));
  }

  /**
   * Resolves a destination for the given inspect URL.
   * The inspect URL is used to resolve a destination for a specific screen or action.
   * For a screen action, the given url must conform to the following format:
   * <code>inspecturl://search-show?...</code>
   * <p>
   *
   * @param inspectUrl The inspect URL to resolve a destination for.
   * @return The resolved destination, or null if the inspect URL is not supported.
   */
  public static @Nullable LookupDestination resolveUrl(@NonNull String inspectUrl)
  {
    if (inspectUrl.toLowerCase().contains("inspecturl://search-show?"))
      return searchDestinationForInspectUrl(inspectUrl);

    return null;
  }

  public static LookupDestination localized(String originalUrl)
  {
    SupportedLocale locale = SupportedLocale.fromUrl(originalUrl);
    if (locale == null)
      locale = SupportedLocale.DEFAULT;

    return new LookupDestination.Localized(null, originalUrl, locale);
  }

  /**
   * Checks if the given URL is a HEMA web URL.
   * <p>
   * A HEMA web URL is a URL that is formatted in either of the following ways:
   * <ul>
   *     <li>www.hema.nl</li>
   *     <li>acc.hema.nl</li>
   *     <li>www.hema.com/nl-nl</li>
   *     <li>acc.hema.com/nl-be</li>
   * </ul>
   *
   * @param url The URL to check.
   * @param env The environment to check against.
   * @return True if the URL is a HEMA web URL, false otherwise.
   */
  public static boolean isHemaWebUrl(String url, HemaWebEnvironment env)
  {
    URL urlObject = UrlUtils.createHttpUrl(url);
    if (urlObject == null) return false;

    var matches =
        Pattern.compile("^(www\\.|acc\\.)?hema\\.([a-z]{2,3})$").matcher(
            urlObject.getHost());
    if (!matches.matches()) return false;

    switch (env)
    {
      case HemaWebEnvironment.INT:
        // Needs to be acc.
        if (!matches.group(1).equals("acc.")) return false;
        break;
      case HemaWebEnvironment.PROD:
        // Can in this case be hema.nl and www.hema.nl
        if (matches.group(1) == null || !matches.group(1).equals("www.")) return false;
        break;
    }

    // The 'com' domain can be of a different locale than nl-nl so we need to check which and if it's supported
    if (matches.group(matches.groupCount()).equals("com"))
      return SupportedLocale.is(urlObject.getPath().split("/")[1]);

    return true;
  }

  public static LookupDestination checkForLookupDestinationContentScreen(String url)
  {
    String parsed = cleanUrlPathname(url);
    // Use the pathname without any query parameters
    assert parsed != null;
    String[] split = parsed.split("/");

    assert split.length > 0;
    String slug = split[split.length - 1];
    split = Arrays.copyOf(split, split.length - 1); // Pop the last segment
    // The remaining items form the rest of the url without the last segment (which was popped).
    if (!String.join("/", split).endsWith("/app-content") || slug == null)
      return null;

    var originalUrlAndLocale = (LookupDestination.Localized) localized(url);

    return new LookupDestination.ContentScreen(
        originalUrlAndLocale.originalUrl,
        originalUrlAndLocale.locale,
        slug,
        null
    );
  }

  public static LookupDestination checkForLookupDestinationProduct(String url)
  {
    var matches = Pattern.compile("/(\\d+)\\.html").matcher(url);
    var productId = (matches.groupCount() > 0 && matches.group(1) != null) ? matches.group(
        1) : null;

    var originalUrlAndLocale = (LookupDestination.Localized) localized(url);

    return productId != null ?
           new LookupDestination.Product(
               originalUrlAndLocale.originalUrl,
               originalUrlAndLocale.locale, productId
           ) :
           null;
  }

  public static LookupDestination checkForLookupDestinationVouchersOverview(String url)
  {
    String parsed = cleanUrlPathname(url);

    assert parsed != null;

    String[] suffixes = {"/meerhema/vouchers", "/pas/vouchers", "/hema-extra/bons", "/hema-extra/vouchers"};

    return Arrays.stream(suffixes)
                 .filter(parsed::endsWith)
                 .map(suffix -> new LookupDestination.VouchersOverview(
                     LookupDestination.VouchersOverview.PresentationStyle.MODAL))
                 .findFirst()
                 .orElse(null);
  }

  public static LookupDestination checkForLookupDestinationVoucherDetails(String url)
  {
    String path = cleanUrlPathname(url);

    /* Use the pathname without any query parameters */
    String[] splitPath = path.split("/");

    /* Check if there are query params */
    String voucherParam = getQueryParameterValue(url, "VoucherTypeId");

    String voucherId = voucherParam != null ? voucherParam : splitPath[splitPath.length - 1];

    if (voucherId == null)
      return null;

    String[] suffixes = {"/meerhema/voucher", "/pas/voucher", "/hema-extra/bon", "/hema-extra/voucher"};

    // [].pop(), but uglier.
    splitPath = Arrays.copyOf(splitPath, splitPath.length - 1);

    /* Stitch path back together, now without voucherId */
    String pathWithoutVoucherId = cleanUrlPathname(String.join("/", splitPath));
    var    originalUrlAndLocale = (LookupDestination.Localized) localized(url);

    assert pathWithoutVoucherId != null;

    /* Make sure our path ends with one of the allowed urls. */
    return Arrays.stream(suffixes)
                 .filter(pathWithoutVoucherId::endsWith)
                 .map(suffix -> new LookupDestination.VoucherDetails(
                     originalUrlAndLocale.originalUrl, originalUrlAndLocale.locale,
                     voucherId
                 ))
                 .findFirst()
                 .orElse(null);
  }

  public static LookupDestination checkForLookupDestinationPromotionsOverview(String url)
  {
    // pathname without any query parameters + removes trailing slash
    String   parsed   = cleanUrlPathname(url);
    String[] suffixes = {"/aanbiedingen", "/nl-be/aanbiedingen", "/fr-be/offres-speciales"};

    return Arrays.stream(suffixes).findAny().map(
        suffix -> new LookupDestination.PromotionsOverview()).orElse(null);

  }

  public static LookupDestination checkForLookupDestinationBasket(String url)
  {
    return Objects.requireNonNull(cleanUrlPathname(url)).endsWith("/cart") ?
           new LookupDestination.Basket() :
           null;
  }

  /**
   * Resolves a destination for the given URL.
   * The URL is used to resolve a destination for a specific screen or action.
   * For a screen action, the given url must conform to the following format:
   * <code>https://www.hema.nl/...</code>
   * <p>
   *
   * @param url The URL to resolve a destination for.
   * @return The resolved destination, or null if the URL is not supported.
   */
  public static LookupDestination destinationForUrl(String url, HemaWebEnvironment env)
  {
    if (!isHemaWebUrl(url, env)) return new LookupDestination.External(url);

    for (var fn : lookupResolvers)
    {
      LookupDestination result = fn.apply(url);
      if (result != null) return result;
    }
    return new LookupDestination.External(url);
  }

  public static LookupDestination lookupDestinationFromUrl(String url,
                                                           List<CFInspectUrlResult> inspectUrlResults,
                                                           HemaWebEnvironment env)
  {
    return inspectUrlResults.stream()
                            .filter(result -> result.originalUrl.equals(url))
                            .map(result -> result.inspectUrl)
                            .filter(Objects::nonNull)
                            .findFirst()
                            .map(LookupDestinationResolver::resolveUrl)
                            .orElseGet(() -> destinationForUrl(url, env));
  }

  public static LookupDestination.Search searchDestinationForInspectUrl(String inspectUrl)
  {
    String[] params        = getQueryParameterSegments(inspectUrl);
    String   categoryId    = getQueryParameterValue(params, "cgid");
    String   searchQuery   = getQueryParameterValue(params, "q");
    String   priceRangeMin = getQueryParameterValue(params, "pmin");
    String   priceRangeMax = getQueryParameterValue(params, "pmax");
    String   promotionId   = getQueryParameterValue(params, "pmid");
    String   sort          = getQueryParameterValue(params, "srule");
    boolean showOrderableOnly = Objects.equals(
        getQueryParameterValue(
            params, "showallproducts"), "false"
    );
    Map<String, String[]> filters = new HashMap<>();

    if (
        priceRangeMin != null &&
        priceRangeMax != null &&
        !Float.isNaN(NumberUtils.parseFloatSafe(priceRangeMin)) &&
        !Float.isNaN(NumberUtils.parseFloatSafe(priceRangeMax))
    )
    {
      filters.put(
          SFFilterKey.PRICE.getKey(),
          new String[]{String.format("(%s..%s)", priceRangeMin, priceRangeMax)}
      );
    }
    if (promotionId != null)
    {
      filters.put(SFFilterKey.PROMOTION_ID.getKey(), promotionId.split("\\|"));
    }

    if (showOrderableOnly)
    {
      filters.put(SFFilterKey.ORDERABLE_ONLY.getKey(), new String[]{"true"});
    }

    for (int i = 1; i <= 9; i += 1)
    {
      String key   = getQueryParameterValue(params, "prefn" + i);
      String value = getQueryParameterValue(params, "prefv" + i);
      if (key != null && value != null)
      {
        String sfKey = systemAttributes.get(key) ? key : "c_" + key;
        filters.put(sfKey, value.split("\\|"));
      }
    }

    return new LookupDestination.Search(
        SearchParams.builder()
                    .term(searchQuery)
                    .categoryId(categoryId)
                    .sort(sort)
                    .filters(filters)
                    .build()
    );
  }
}
