package me.metallicgoat.specialItems.api;

import de.marcely.bedwars.api.game.specialitem.SpecialItem;
import de.marcely.bedwars.tools.Validate;
import me.metallicgoat.specialItems.customitems.CustomSpecialItem;
import org.jetbrains.annotations.Nullable;

/**
 * Enum representing different types of extra special items.
 */
public enum ExtraSpecialItemType {

  /**
   * Runs a custom command on use.
   * <p>
   *   There may be multiple items of this type.
   * </p>
   */
  COMMAND,

  /**
   * Launches an egg that places blocks on its way.
   */
  EGG_BRIDGER,

  /**
   * Places ice below the player.
   */
  ICE_BRIDGER,

  /**
   * Launches a snowball that spawns a silverfish wherever it lands.
   */
  SILVERFISH,

  /**
   * Launches the player in the direction they are looking.
   */
  SLINGSHOT,

  /**
   * Builds a tower around the player.
   */
  TOWER,

  /**
   * A firework that can be used to boost players endlessly for elytras.
   */
  ENDLESS_BOOST;

  private SpecialItem specialItem;

  /**
   * Get the SpecialItem associated with this type.
   * <p>
   *   Is <code>null</code> for {@link #hasMultipleItems()}
   *   or (potentially, if server is older) {@link #hasMinServerVersion()}.
   * </p>
   *
   * @return the SpecialItem. May be <code>null</code>
   */
  @Nullable
  public SpecialItem getItem() {
    return this.specialItem;
  }

  /**
   * Get whether there may be multiple items of this type.
   * <p>
   *   Only {@link #COMMAND} may have multiple items.
   * </p>
   *
   * @return <code>true</code> if there may be multiple items of this type, <code>false</code> otherwise
   */
  public boolean hasMultipleItems() {
    return this == COMMAND;
  }

  /**
   * Get whether this type requires a minimum server version.
   *
   * @return <code>true</code> if this type requires a minimum server version, <code>false</code> otherwise
   */
  public boolean hasMinServerVersion() {
    return this == ENDLESS_BOOST;
  }

  /**
   * Get the minimum server version required for this type.
   *
   * @return the minimum server version, or <code>null</code> if there is no minimum server version
   */
  @Nullable
  public Integer getMinServerVersion() {
    if (!hasMinServerVersion())
      return null;

    switch (this) {
      case ENDLESS_BOOST:
        return 12;
      default:
        throw new UnsupportedOperationException();
    }
  }

  /**
   * Get the ExtraSpecialItemType from a SpecialItem.
   *
   * @param item the SpecialItem to get the type from
   * @return the ExtraSpecialItemType, or <code>null</code> if the item is not from this addon
   */
  @Nullable
  public static ExtraSpecialItemType from(SpecialItem item) {
    Validate.notNull(item, "item");

    if (!(item instanceof CustomSpecialItem))
      return null;

    return ((CustomSpecialItem) item).getType();
  }
}
