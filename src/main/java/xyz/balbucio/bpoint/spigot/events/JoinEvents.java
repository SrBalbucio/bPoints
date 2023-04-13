package xyz.balbucio.bpoint.spigot.events;

import me.vagdedes.mysql.database.SQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.balbucio.bpoint.spigot.FileManager;
import xyz.balbucio.bpoint.spigot.Initialize;

public class JoinEvents implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent evt){
        Player player = evt.getPlayer();
        String name = player.getName();
        try {
            if (!FileManager.returnOficialConfig().contains(name)) {
                FileManager.returnConfig(name, (long) SQL.get("value", "nome", "=", name, Initialize.table));
            }
        }catch (Exception e){
            FileManager.returnConfig(name, 0);
        }
    }
}
