package nl.q42.common.actions;

public class NavigationAction extends Action
{
  public final String path;
  public boolean cachePrevious;

  public NavigationAction(String path, boolean cachePrevious)
  {
    super(ActionTypes.NAVIGATION);
    this.path = path;
    this.cachePrevious = cachePrevious;
  }
}
