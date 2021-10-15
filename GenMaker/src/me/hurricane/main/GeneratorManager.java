package me.hurricane.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;



public class GeneratorManager {
  private Map<Location, Generator> gens;
  
  private Plugin plugin;
  
  public GeneratorManager(Plugin plugin) {
    this.gens = new HashMap<>();
    this.plugin = plugin;
  }
  
  public void addGenerator(Block b, ItemStack item, double time) {
    this.gens.put(b.getLocation(), new Generator(b, item, time));
    ((Generator)this.gens.get(b.getLocation())).runTaskTimer(this.plugin, 0L, 1L);
  }
  
  public void removeGenerator(Block b) {
    if (this.gens.containsKey(b.getLocation())) {
      ((Generator)this.gens.get(b.getLocation())).cancel();
      this.gens.remove(b);
    } 
  }
}
