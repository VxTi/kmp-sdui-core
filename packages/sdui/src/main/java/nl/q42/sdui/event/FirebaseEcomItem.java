package nl.q42.sdui.event;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class FirebaseEcomItem
{
  public           int    index;
  public @Nullable String itemId;
  public @Nullable String itemName;
  public @Nullable String itemVariant;
  public           double price;
  public           int    quantity;
  public @Nullable String brand;
  public @Nullable String itemCategory;
  public @Nullable String itemCategory2;
  public @Nullable String itemCategory3;
  public @Nullable String itemCategory4;
  public @Nullable String itemCategory5;
}
