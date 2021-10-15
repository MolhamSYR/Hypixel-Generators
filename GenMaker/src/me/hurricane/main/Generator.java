package me.hurricane.main;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Generator extends BukkitRunnable {
  private Block b;
  
  private ArmorStand armorStand;
  
  private boolean floatLoop;
  
  private Location location, sameLocation;
  
  private ItemStack item;
  
  private double time;
  
  private final double timeX;
  
  public Generator(Block b, ItemStack item, double time) {
    this.b = b;
    this.item = item;
    this.time = time;
    this.timeX = time;
  }
  
  @SuppressWarnings("deprecation")
public void run() {
    
	
      this.b.getWorld().dropItem(this.b.getLocation().add(0.5D, 1.0D, 0.5D), this.item);
      armorStand = (ArmorStand) this.b.getWorld().spawnEntity(b.getLocation().add(0,2,0), EntityType.ARMOR_STAND);
      armorStand.setGravity(false);
      armorStand.setHelmet(item);
      armorStand.setCustomName(ChatColor.GREEN + "Generating..!");
      armorStand.setCustomNameVisible(true);
      armorStand.setVisible(false);
      armorStand.setSmall(true);
      
      this.time = this.timeX;
   
     
    this.time -= 0.05D;
    update();
  
    
  }
  public void update() {
	  location = armorStand.getLocation();

	    if (!this.floatLoop) {
	        location.add(0, 0.01, 0);
	        location.setYaw((location.getYaw() + 7.5F));

	        armorStand.teleport(location);

	        if (armorStand.getLocation().getY() > (0.25 + sameLocation.getY()))
	            this.floatLoop = true;
	    } 
	    else {
	        location.subtract(0, 0.01, 0);
	        location.setYaw((location.getYaw() - 7.5F));

	        armorStand.teleport(location);

	        if (armorStand.getLocation().getY() < (-0.25 + sameLocation.getY()))
	            this.floatLoop = false;
	    }
  }

}

