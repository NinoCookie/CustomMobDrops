package custommobdrops_commands;

import custommobdrops_main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Cmd implements CommandExecutor {
    Plugin plugin;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (plugin != null && commandSender.hasPermission("cmd.reload")) {
            if (strings.length > 0) {
                if (strings[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    Main.init(plugin);
                    commandSender.sendMessage("Reloaded");
                    return true;
                }
            }
        }
        return false;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
}
