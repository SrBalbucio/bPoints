package xyz.balbucio.bpoint.spigot;

import me.vagdedes.mysql.database.MySQL;
import me.vagdedes.mysql.database.SQL;
import xyz.balbucio.bpoint.spigot.Points.SQLOrder;
import xyz.balbucio.bpoint.spigot.Utils.balbLogger;

public class Initialize {

    public static String table = "balbBPoint";

    public static void prepareMySQL() {
        try {
            if (MySQL.getConnection() != null) {
                if (!SQL.tableExists(table)) {
                    SQL.createTable(table, "nome VARCHAR(255), value BIGINT(225)");
                }
            }
        } catch (Exception e) {
            balbLogger.sendSqlLog("Não foi possível iniciar uma conexão com o MySQL!");
        }
    }

    public static boolean isSQLConnected(){
        try {
            if (MySQL.isConnected() || MySQL.getConnection() != null || !MySQL.getConnection().isClosed()) {
                SQLOrder.loadOrder();
                return true;
            } else {
                MySQL.connect();
                return false;
            }
        } catch(Exception e){
            balbLogger.sendSqlLog("Não foi possível iniciar uma conexão com o MySQL!");
        }
        return false;
    }
}
