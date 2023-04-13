package xyz.balbucio.bpoint.spigot;

import me.vagdedes.mysql.database.SQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import xyz.balbucio.bpoint.spigot.logs.LogManager;

public class PointAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.hasPermission("bpoint.admin")) {
            if (args.length == 0) {
                sender.sendMessage("§a§lAJUDA §f- §abPoints Admin");
                sender.sendMessage("§a/brpadm listar §f- §7Lista todas as Transferências feita no servidor");
                sender.sendMessage("§a/brpadm recarregar §f- §7Recarrega o Backup e o cache dos Membros");
            } else if (args.length > 0 && args[0].equalsIgnoreCase("listar")) {
                sender.sendMessage("§a§lLista de todas as Últimas 30 Transferências: ");
                for(String string : LogManager.getTransferencias()){
                    sender.sendMessage(string);
                }
                sender.sendMessage("§7Para saber todas as transferências confira no logs.yml!");
            } else if(args.length > 0 && args[0].equalsIgnoreCase("recarregar")){
                sender.sendMessage("§cRecarregar o backup de forma manual não é indicado, pode causar sobrecarregamentos e até problemas relacionados ao MySQL!");
                sender.sendMessage("§cTodos os erros serão retornados ao seu Chat para lhe informar dos problemas que podem ocorrer!");
                sender.sendMessage("§aIniciando reload...");
                YamlConfiguration config = FileManager.returnOficialConfig();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    config.set(player.getName(), SQL.get("value", "nome", "=", player.getName(), Initialize.table));
                }
                try {
                    config.save(FileManager.returnFile());
                }catch (Exception e){
                    sender.sendMessage("§cA config foi deletada? Não foi possível salvar as alterações!");
                }
                sender.sendMessage("§aRelaod das configurações concluído com sucesso!");
            }
        }
        return false;
    }
}
