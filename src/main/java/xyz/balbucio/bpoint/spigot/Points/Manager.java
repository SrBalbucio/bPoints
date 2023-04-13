package xyz.balbucio.bpoint.spigot.Points;

import me.vagdedes.mysql.database.SQL;
import xyz.balbucio.bpoint.spigot.FileManager;
import xyz.balbucio.bpoint.spigot.Initialize;
import xyz.balbucio.bpoint.spigot.Utils.balbLogger;
import xyz.balbucio.bpoint.spigot.logs.LogManager;
import xyz.balbucio.bpoint.spigot.logs.LogType;

public class Manager {

    public static void check(String name){
        try {
            Initialize.prepareMySQL();
            if (!SQL.exists("nome", name, Initialize.table)) {
                SQL.insertData("nome, value", "'" + name + "', '0'", Initialize.table);
            }
        } catch (Exception e){
        }
    }

    public static boolean set(String name, long amount, String adm, boolean salvar){
        if(!(amount < 0)) {
            try {
                check(name);
                SQL.set("value", amount, "nome", "=", name, Initialize.table);
            } catch (Exception e) {
                SQLOrder.order.put(name, amount);
            }
            if(salvar) {
                LogManager.addToLogs(name, adm, amount, LogType.SET);
            }
            FileManager.returnConfig(name, amount);
            return true;
        }
        return false;
    }
    public static Long get(String name){
        try {
            check(name);
            long f = (long) SQL.get("value", "nome", "=", name, Initialize.table);
            FileManager.returnConfig(name, f);
            return f;
        } catch (Exception e){
            return FileManager.returnLong(name);
        }
    }
    public static boolean add(String name, int amount, String adm) {
        if(!(amount < 0)) {
            long fin = get(name) + amount;
            set(name, fin, adm, false);
            LogManager.addToLogs(name, adm, amount, LogType.ADD);
            return true;
        }
        return false;
    }
    public static boolean remove(String name, int amount, String adm){
        if(!(amount < 0)) {
            long fin = get(name) - amount;
            if (!(fin < 0)) {
                set(name, fin, adm, false);
                LogManager.addToLogs(name, adm, amount, LogType.REMOVER);
                return true;
            } else {
                balbLogger.sendInfo("§cEm uma das transações do player §f" + name + "§c o saldo do mesmo ficou negativo!");
                return false;
            }
        } else{
            return false;
        }
    }
    public static void clear(String name, String adm){
        LogManager.addToLogs(name, adm, 0, LogType.CLEAR);
        set(name, 0, adm, false);
    }
}
