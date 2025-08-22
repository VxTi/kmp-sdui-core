package nl.q42.sdui;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@AllArgsConstructor
public abstract class AbstractContent implements Serializable
{
  public String contentId;
  public String type;
}
