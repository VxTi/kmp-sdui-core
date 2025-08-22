package nl.q42.webdata;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
@AllArgsConstructor
public class Category
{
  public @Nullable Category[] categories;
  public           String     id;
  public @Nullable String     name;
  public @Nullable String     parent_category_id;
  public @Nullable String     c_appCategoryImage;
  public           boolean    c_showInMenu = false;

}
