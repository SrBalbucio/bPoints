package xyz.balbucio.bpoint.spigot.Points;

import me.vagdedes.mysql.database.SQL;
import xyz.balbucio.bpoint.spigot.Initialize;

import java.util.HashMap;

public class SQLOrder {

    public static HashMap<String, Long> order = new HashMap<>();
    public static void loadOrder(){
        try {
            for (String name : order.keySet()) {
                Manager.check(name);
                SQL.set("value", order.get(name), "nome", "=", name, Initialize.table);
            }
        }catch(Exception e){
        }
    }

}
