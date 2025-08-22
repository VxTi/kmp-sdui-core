package nl.q42.core.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SFFilterKey
{
    CATEGORY_ID("cgid"),
    UNIT("c_unit-description"),
    SIZE("c_size"),
    COLOR("c_maincolour"),
    PRICE("price"),
    PRODUCT_TYPE("c_producttype"),
    PATTERN("c_pattern"),
    SLEEVE_LENGTH("c_sleevelength"),
    WIDTH("c_widthcminches"),
    HEIGHT("c_lengthcminches"),
    COLLECTION("c_mode"),
    SEARCH_QUERY("q"),
    SORT_RULE("srule"),
    MATERIAL("c_materialcomposition"),
    BRAND("c_brand"),
    WEIGHT("name"), // yes, really
    ID("ID"),
    IS_NEW("c_isNew"),
    ORDERABLE_ONLY("orderable_only"),
    PROMOTION_ID("pmid"),
    PRICE_RANGE_MIN("pmin"),
    PRICE_RANGE_MAX("pmax"),

    // SFCC requires artikel_status to exist, but this is not something we should provide as a filter for the users.
    // See: https://hemaecom.atlassian.net/browse/A20-4736
    STATUS("c_artikel_status"),
    TYPE("htype"),
    SEARCHABLE("searchable");

    private final String key;
}
