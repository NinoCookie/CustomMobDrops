package custommobdrops_main;

import custommobdrops_commands.Cmd;
import custommobdrops_listeners.MobsDeath;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    public static List<Mobs> moblist;
    @Override
    public void onEnable() {
        setupConf();
        init(this);
        if(this.getConfig().getBoolean("CustomMobDrops.enable")){
            MobsDeath mobsDeath=new MobsDeath();
            mobsDeath.setPlugin(this);

            Bukkit.getPluginManager().registerEvents(mobsDeath, this);
            Bukkit.getConsoleSender().sendMessage("§a[CustomMobDrops]");
            Cmd cmd=new Cmd();
            cmd.setPlugin(this);
            this.getCommand("cmd").setExecutor(cmd);
        }
    }
    public void setupConf(){
        File file=new File(getDataFolder()+File.separator+"config.yml");
        if(!file.exists()){
            this.saveDefaultConfig();
        }
        this.reloadConfig();
    }
    public static void init(Plugin plugin){
        moblist=new ArrayList<>();
        ConfigurationSection section=plugin.getConfig().getConfigurationSection("Mobs");
        for(String key : section.getKeys(false)){
            moblist.add(new Mobs(section.getConfigurationSection(key)));
        }
        MobsDeath.list=moblist;
    }
}
