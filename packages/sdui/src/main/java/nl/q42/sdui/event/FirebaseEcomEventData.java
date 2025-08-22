package nl.q42.sdui.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class FirebaseEcomEventData
{
  public @Nullable String             listId;
  public @Nullable String             listName;
  public @Nullable String             currency;
  public @Nullable String             value;
  public @Nullable String             coupon;
  public @Nullable String             shippingTier;
  public @Nullable String             paymentType;
  public @Nullable String             transactionId;
  public @Nullable String             affiliation;
  public @Nullable String             promotionID;
  public @Nullable String             promotionName;
  public @Nullable String             creativeName;
  public @Nullable String             creativeSlot;
  public @Nullable String             locationID;
  public           int                tax;
  public           int                shipping;
  public @Nullable String             contentType;
  public @Nullable String             method;
  public @Nullable String             itemId;
  public @Nullable String             searchTerm;
  public           double             discount;
  public           int                success;
  public @Nullable FirebaseEcomItem[] items;
}
