package xyz.balbucio.bpoint.spigot.PlaceHolder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.balbucio.bpoint.spigot.Points.Manager;
import xyz.balbucio.bpoint.spigot.Spigot;

public class PointHolder extends PlaceholderExpansion
{
    private final Spigot plugin;

    public PointHolder(final Spigot plugin) {
        this.plugin = plugin;
    }

    public String getAuthor() {
        return "Sr_Balbucio";
    }

    public String getIdentifier() {
        return "bPoints";
    }

    public String getVersion() {
        return "2.5";
    }

    public boolean persist() {
        return true;
    }

    public String onPlaceholderRequest(final Player p, final String params) {
        return String.valueOf(Manager.get(p.getName()));
    }

    public String onRequest(final OfflinePlayer player, final String params) {
        return String.valueOf(Manager.get(player.getName()));
    }
}
