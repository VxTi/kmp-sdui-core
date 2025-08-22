package nl.q42.sdui.screen.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RefreshIdentifiers
{
  VOUCHERS("vouchers"),
  BASKET("basket"),
  FAVORITES("favorites");

  private final String identifier;
}
