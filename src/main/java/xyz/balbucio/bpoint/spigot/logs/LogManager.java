package xyz.balbucio.bpoint.spigot.logs;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.balbucio.bpoint.spigot.FileManager;

import java.util.ArrayList;

public class LogManager {

    static ArrayList<String> transferencias = new ArrayList<>();

    public static void addToLogs(String name, String admin, long amount, LogType type) {
        try {
            if(transferencias.size() < 30) {
                transferencias.add(type.get().replace("%player%", name).replace("%admin%", admin).replace("%amount%", String.valueOf(amount)));
            } else {
                YamlConfiguration config = FileManager.returnOficialLog();
                if (config.contains("Transferencias")) {
                    for (String trs : transferencias) {
                        config.getStringList("Transferencias").add(trs);
                    }
                } else {
                    config.set("Transferencias", transferencias);
                }
                config.save(FileManager.returnLogFile());
                transferencias.clear();
                transferencias.add(type.get().replace("%player%", name).replace("%admin%", admin).replace("%amount%", String.valueOf(amount)));
            }
        } catch (Exception e) {
        }
    }

    public static ArrayList<String> getTransferencias() {
        return transferencias;
    }
}
