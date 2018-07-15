package custommobdrops_listeners;

import custommobdrops_main.Mobs;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class MobsDeath implements Listener {
    private Plugin plugin;
    public static List<Mobs> list=new ArrayList<>();
    private List<UUID> allmobs=new ArrayList<>();
    @EventHandler
    public void onMobKill(EntityDeathEvent event){
            if(event.getEntity().getKiller() instanceof Player) {
                Player player = event.getEntity().getKiller();
                PlayerInventory inv = player.getInventory();
                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).getLoot_permission().equalsIgnoreCase("")) {
                        if (!player.hasPermission(list.get(i).getLoot_permission())) {
                            return;
                        }
                    }
                    if (!list.get(i).isMob_from_spawner() && isSame(event.getEntity().getUniqueId())) {
                        return;
                    }
                    String mob = event.getEntity().getType().name();
                    if (list.get(i).isMob_kill_tool()) {
                        if (!list.get(i).getMob_kill_tool_items().contains("" + player.getItemInHand().getType().getId())) {
                            if (!(player.getItemInHand().getType().name().equalsIgnoreCase("AIR") && list.get(i).getMob_kill_tool_items().contains("hand"))) {
                                return;
                            }
                        }
                    }
                    if (!list.get(i).getMob_type().equalsIgnoreCase(mob)) {
                        return;
                    }
                    if (!list.get(i).isLoot_naturaldrop()) {
                        event.getDrops().clear();
                    }
                    if (list.get(i).getLoot_chance() == 0) {
                        return;
                    }
                    if (chance(list.get(i).getLoot_chance())) {
                        ItemStack item = new ItemStack(Integer.parseInt(list.get(i).getLoot_first_id()), list.get(i).getLoot_amount(), Short.parseShort(list.get(i).getLoot_second_id()));
                        ItemMeta itemMeta = item.getItemMeta();
                        itemMeta.setDisplayName(list.get(i).getLoot_name());
                        itemMeta.setLore(Arrays.asList(list.get(i).getLoot_lore()));
                        item.setItemMeta(itemMeta);
                        for (int j = 0; j < list.get(i).getLoot_enchantments().size(); j++) {
                            String[] c = list.get(i).getLoot_enchantments().get(j).split("-");
                            if (Enchantment.getByName(c[0]) != null) {
                                item.addUnsafeEnchantment(Enchantment.getByName(c[0]), Integer.parseInt(c[1]));
                            }
                        }
                        if(event.getEntity().getName().equalsIgnoreCase(list.get(i).getOnly_drop_special_named_mob_name()) && list.get(i).isOnly_drop_special_named_mob()){
                            if (!list.get(i).isLoot_transfer_inventory()) {
                                event.getDrops().add(item);
                                event.getEntity().getKiller().sendMessage(list.get(i).getLoot_message());
                            } else {
                                inv.addItem(item);
                                event.getEntity().getKiller().sendMessage(list.get(i).getLoot_message());
                            }
                        } //Das verhindert dass wenn es auf true gesetzt ist aber er einen  anderen mob tötet dass geschaut wird ob es eh auf false ist ...
                        if(!list.get(i).isOnly_drop_special_named_mob()){
                            if (!list.get(i).isLoot_transfer_inventory()) {
                                event.getDrops().add(item);
                                event.getEntity().getKiller().sendMessage(list.get(i).getLoot_message());
                            } else {
                                inv.addItem(item);
                                event.getEntity().getKiller().sendMessage(list.get(i).getLoot_message());
                            }
                        }
                    }
                }
            }
        }
    @EventHandler
    public void onSpawn(CreatureSpawnEvent event){
        for (int i = 0; i < list.size(); i++) {
            if(event.getSpawnReason()==CreatureSpawnEvent.SpawnReason.NATURAL || event.getSpawnReason()==CreatureSpawnEvent.SpawnReason.SPAWNER_EGG || event.getSpawnReason()==CreatureSpawnEvent.SpawnReason.SPAWNER) {
                if(event.getEntity().getType().name().equalsIgnoreCase(list.get(i).getMob_type())){
                    if(!event.getEntity().getName().equalsIgnoreCase(list.get(i).getOnly_drop_special_named_mob_name())){
                        event.getEntity().setCustomName(list.get(i).getMob_name());
                    }
                }
            }
        }
        if(event.getSpawnReason()==CreatureSpawnEvent.SpawnReason.SPAWNER || event.getSpawnReason()==CreatureSpawnEvent.SpawnReason.SPAWNER_EGG){
            allmobs.add(event.getEntity().getUniqueId());
        }
    }
    private boolean isSame(UUID id){
        for (int i = 0; i < allmobs.size(); i++) {
            if(id.equals(allmobs.get(i))){
                return true;
            }
        }
        return false;
    }
    private boolean chance(double chance){
        Random r=new Random();
        double result=r.nextDouble();
        if(result<=chance/100){
            return true;
        }
        return false;
    }
    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
    public void setList(List<Mobs> list) {
        this.list = list;
    }
}
