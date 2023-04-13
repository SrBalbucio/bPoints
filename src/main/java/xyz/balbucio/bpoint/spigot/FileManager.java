package xyz.balbucio.bpoint.spigot;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import java.io.File;
import java.io.IOException;

public class FileManager {
    static File folder = new File("plugins/balbBPoint");

    public static void loadConfig(Plugin plg){
        if(!folder.exists()){
            folder.mkdir();
        }
        File file = new File("plugins/balbBPoint", "backup.yml");
        if(!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        file = new File("plugins/balbBPoint", "logs.yml");
        if(!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
            }
        }
    }
    public static YamlConfiguration returnOficialConfig() {
        File file = new File(folder, "backup.yml");
        if(!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }
    public static YamlConfiguration returnOficialLog() {
        File file = new File(folder, "logs.yml");
        if(!file.exists()) {
            try{
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }
    public static File returnFile(){
        return new File(folder, "backup.yml");
    }
    public static File returnLogFile(){
        return new File(folder, "logs.yml");
    }

    public static void returnConfig(String name, long att){
        File file = new File(folder, "backup.yml");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set(name, att);
            config.save(file);
        } catch (IOException e) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException ioException) {
            }
        }
    }

    public static Long returnLong(String name){
        Long value = Long.valueOf(0);
        File file = new File(folder, "backup.yml");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            value = config.getLong(name);
        } catch (IOException e) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException ioException) {
            }
        }
        return value;
    }
}
