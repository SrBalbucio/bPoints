package xyz.balbucio.bpoint.spigot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import xyz.balbucio.bpoint.spigot.Points.Manager;
import xyz.balbucio.bpoint.spigot.Utils.balbLogger;

public class PointCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§aVocê tem um total de §f"+ Manager.get(sender.getName())+" bPoints §a!");
        }
        if(sender.hasPermission("bpoints.use") || sender instanceof ConsoleCommandSender) {
            if (args.length > 1 && args[0].equalsIgnoreCase("set")) {
                try {
                    String name = args[1];
                    int number = Integer.parseInt(args[2]);
                    if(Manager.set(name, number, sender.getName(), true)) {
                        sender.sendMessage("§aSetado com sucesso!");
                        balbLogger.sendInfo("§aO §f" + sender.getName() + "§a setou §f" + number + " bPoints §aao player §f" + name);
                    } else{
                        sender.sendMessage("§cVocê informou um valor incorreto!");
                    }
                } catch (Exception e) {
                    sender.sendMessage("§cUse /brp set <name> <valor>");
                }
            } else if (args.length > 1 && args[0].equalsIgnoreCase("add")) {
                try {
                    String name = args[1];
                    int number = Integer.parseInt(args[2]);
                    if(Manager.add(name, number, sender.getName())) {
                        sender.sendMessage("§aAdicionado com sucesso!");
                        balbLogger.sendInfo("§aO §f" + sender.getName() + "§a adicionou §f" + number + " bPoints §aao player §f" + name);
                    } else{
                        sender.sendMessage("§cVocê informou um valor incorreto!");
                    }
                } catch (Exception e) {
                    sender.sendMessage("§cUse /brp add <name> <valor>");
                }
            } else if (args.length > 1 && args[0].equalsIgnoreCase("remove")) {
                try {
                    String name = args[1];
                    int number = Integer.parseInt(args[2]);
                    if(Manager.remove(name, number, sender.getName())) {
                        sender.sendMessage("§aRemovido com sucesso!");
                        balbLogger.sendInfo("§aO §f" + sender.getName() + "§a removeu §f" + number + " bPoints §aao player §f" + name);
                    } else{
                        sender.sendMessage("§cVocê informou um valor incorreto!");
                    }
                } catch (Exception e) {
                    sender.sendMessage("§cUse /brp remove <name> <valor>");
                }
            } else if (args.length > 1 && args[0].equalsIgnoreCase("ver")) {
                try {
                    String name = args[1];
                    sender.sendMessage("§aO Player " + name + " tem um total de " + Manager.get(name));
                } catch (Exception e) {
                    sender.sendMessage("§cUse /brp ver <name>");
                }
            } else if (args.length > 1 && args[0].equalsIgnoreCase("clear")) {
                try {
                    String name = args[1];
                    Manager.clear(name, sender.getName());
                    sender.sendMessage("§aO Player " + name + " teve seus Points resetados!");
                    balbLogger.sendInfo("§aO §f" + sender.getName() + "§a resetou todos os pontos do " + name);
                } catch (Exception e) {
                    sender.sendMessage("§cUse /brp clear <name>");
                }
            }
        }
        return false;
    }
}
