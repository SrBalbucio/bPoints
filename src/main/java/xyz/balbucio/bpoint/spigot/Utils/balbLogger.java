package xyz.balbucio.bpoint.spigot.Utils;

import org.bukkit.Bukkit;

public class balbLogger {
    private static String prefix = "§a[balbBPoint]";
    public static void sendInfo(String msg){
        Bukkit.getConsoleSender().sendMessage(prefix+" §f[INFO] "+msg);
    }
    public static void sendError(String msg){
        Bukkit.getConsoleSender().sendMessage(prefix+" §4[ERROR] §c"+msg);
    }
    public static void sendLoadLog(String msg){
        Bukkit.getConsoleSender().sendMessage(prefix+" §f[LOAD] "+msg);
    }
    public static void sendStopLog(String msg){
        Bukkit.getConsoleSender().sendMessage(prefix+" §c[STOP] §f"+msg);
    }
    public static void sendSqlLog(String msg){
        Bukkit.getConsoleSender().sendMessage(prefix+" §c[SQL] §f"+msg);
    }
    public static void sendQueryLog(String msg){
        Bukkit.getConsoleSender().sendMessage(prefix+" §c[MANAGER] §4"+msg);
    }
    public static void sendSyncLog(String msg){
        Bukkit.getConsoleSender().sendMessage(prefix+" §f[SYNC] "+msg);
    }
}
