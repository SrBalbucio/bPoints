package xyz.balbucio.bpoint.spigot.PlaceHolder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xyz.balbucio.bpoint.spigot.Points.Manager;

public class Placeholder extends PlaceholderExpansion {

    private Plugin plugin;

    @Override
    public boolean register(){
        return true;
    }
    @Override
    public boolean canRegister() {
        return true;
    }
    public Placeholder(Plugin plugin){
        this.plugin = plugin;
    }
    @Override
    public String getIdentifier() {
        return "bpoints";
    }

    @Override
    public String getAuthor() {
        return "Sr_Balbucio";
    }

    @Override
    public String getVersion() {
        return "2.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        return String.valueOf(Manager.get(player.getName()));
    }
}
