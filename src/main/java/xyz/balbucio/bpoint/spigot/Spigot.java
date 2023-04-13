package xyz.balbucio.bpoint.spigot;

import me.vagdedes.mysql.database.SQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.balbucio.bpoint.spigot.PlaceHolder.PointHolder;
import xyz.balbucio.bpoint.spigot.Points.SQLOrder;
import xyz.balbucio.bpoint.spigot.Utils.balbLogger;
import xyz.balbucio.bpoint.spigot.events.JoinEvents;

import java.util.ArrayList;

public final class Spigot extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("bpoints").setExecutor(new PointCommand());
        this.getCommand("bpoint").setExecutor(new PointCommand());
        this.getCommand("cash").setExecutor(new PointCommand());
        this.getCommand("brp").setExecutor(new PointCommand());
        this.getCommand("brpadm").setExecutor(new PointAdminCommand());
        Bukkit.getPluginManager().registerEvents(new JoinEvents(), this);
        balbLogger.sendLoadLog("§aPlugin carregado com sucesso!");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PointHolder(this).register();
        }
        syncBackup();
    }
    @Override
    public void onLoad(){
        balbLogger.sendLoadLog("§aLoad iniciado!");
        FileManager.loadConfig(this);
    }

    @Override
    public void onDisable() {
        balbLogger.sendStopLog("§cDesativando o plugin.");
    }

    private void syncBackup(){
        balbLogger.sendSyncLog("§aTimers iniciados com sucesso!");
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                try {
                    SQLOrder.loadOrder();
                    balbLogger.sendSyncLog("§aUma nova thread sync foi iniciada!");
                    YamlConfiguration config = FileManager.returnOficialConfig();
                    for (String name : config.getKeys(false)) {
                        config.set(name, SQL.get("value", "nome", "=", name, Initialize.table));
                    }
                    config.save(FileManager.returnFile());
                    balbLogger.sendSyncLog("§aBackup sincronizado com sucesso, finalizando thread sync.");
                } catch(Exception e){
                    balbLogger.sendSyncLog("§4Ocorreu um problema na Thread sync! Daqui 5 Minutos haverá uma nova tentativa.");
                    balbLogger.sendSyncLog("§4Caso o erro persista §cDELETE§4 o backup.yml! (Não é necessário reiniciar o servidor para completar a ação)");
                }
            }
        }, 1, 6000L);
    }
}
