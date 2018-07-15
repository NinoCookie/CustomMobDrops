package custommobdrops_main;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;


public class Mobs {
    private String mob_type;
    private  List<String> mob_kill_tool_items;
    private boolean mob_kill_tool;
    private boolean mob_from_spawner;
    private String loot_message;
    private String loot_permission;
    private String loot_first_id;
    private String loot_second_id;
    private int loot_amount;
    private int loot_chance;
    private boolean loot_naturaldrop;
    private List<String> loot_worlds;
    private List<String> loot_enchantments;
    private String loot_name;
    private String loot_lore;
    private boolean loot_transfer_inventory;
    private String mob_name;
    private boolean only_drop_special_named_mob;
    private String only_drop_special_named_mob_name;
    public Mobs(ConfigurationSection section){
        only_drop_special_named_mob_name=section.getString("only_drop_special_named_mob_name");
        only_drop_special_named_mob=section.getBoolean("only_drop_special_named_mob");
        mob_name=section.getString("mob_name");
        loot_message=section.getString("loot_message");
        mob_type=section.getString("mob_type");
        mob_kill_tool_items=section.getStringList("mob_kill_tool_items");
        mob_kill_tool=section.getBoolean("mob_kill_tool");
        mob_from_spawner=section.getBoolean("mob_from_spawner");
        loot_permission=section.getString("loot_permission");
        loot_first_id=section.getString("loot_first_id");
        loot_second_id=section.getString("loot_second_id");
        loot_amount=section.getInt("loot_amount");
        loot_chance=section.getInt("loot_chance");
        loot_naturaldrop=section.getBoolean("loot_naturaldrop");
        loot_worlds=section.getStringList("loot_worlds");
        loot_enchantments=section.getStringList("loot_enchantments");
        loot_name=section.getString("loot_name");
        loot_lore=section.getString("loot_lore");
        loot_transfer_inventory=section.getBoolean("loot_transfer_inventory");
    }

    public String getMob_type() {
        return mob_type;
    }

    public boolean isMob_from_spawner() {
        return mob_from_spawner;
    }

    public String getLoot_permission() {
        return loot_permission;
    }

    public String getLoot_first_id() {
        return loot_first_id;
    }

    public String getLoot_second_id() {
        return loot_second_id;
    }

    public int getLoot_amount() {
        return loot_amount;
    }

    public int getLoot_chance() {
        return loot_chance;
    }

    public boolean isLoot_naturaldrop() {
        return loot_naturaldrop;
    }

    public List<String> getLoot_worlds() {
        return loot_worlds;
    }

    public List<String> getLoot_enchantments() {
        return loot_enchantments;
    }

    public String getLoot_name() {
        return loot_name;
    }

    public String getLoot_lore() {
        return loot_lore;
    }

    public boolean isLoot_transfer_inventory() {
        return loot_transfer_inventory;
    }

    public List<String> getMob_kill_tool_items() {
        return mob_kill_tool_items;
    }

    public boolean isMob_kill_tool() {
        return mob_kill_tool;
    }

    public String getLoot_message() {
        return loot_message;
    }

    public String getMob_name() {
        return mob_name;
    }

    public boolean isOnly_drop_special_named_mob() {
        return only_drop_special_named_mob;
    }

    public String getOnly_drop_special_named_mob_name() {
        return only_drop_special_named_mob_name;
    }
}
