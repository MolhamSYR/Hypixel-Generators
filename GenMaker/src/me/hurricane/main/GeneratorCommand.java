package me.hurricane.main;
import me.hurricane.main.ItemGenerator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GeneratorCommand implements CommandExecutor {
  private ItemGenerator plugin;
  
  public GeneratorCommand(ItemGenerator plugin) {
    this.plugin = plugin;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    if (sender instanceof Player && sender.isOp()) {
      Player p = (Player)sender;
      if (args.length >= 1) {
        if (args[0].equalsIgnoreCase("set"))
          if (args.length >= 3) {
            Material m = Material.matchMaterial(args[1]);
            if (m == null) {
              sender.sendMessage(String.valueOf(args[1]) + " is not an item material!");
              return false;
            } 
            if (isDouble(args[2])) {
              Block b = p.getTargetBlock(null, 3);
              if (!b.getType().isSolid()) {
                p.sendMessage(ChatColor.RED + "You must look at a solid block!");
                return false;
              } 
              this.plugin.getGeneratorManager().addGenerator(b, new ItemStack(m), Double.parseDouble(args[2]));
              return true;
            } 
          } else {
            sender.sendMessage(ChatColor.RED + "/generator [set/remove] [itemType] [seconds]");
          }  
        if (args[0].equalsIgnoreCase("remove")) {
          Block b = p.getTargetBlock(null, 3);
          if (!b.getType().isSolid()) {
            p.sendMessage(ChatColor.RED + "You must look at a solid block!");
            return false;
          } 
          this.plugin.getGeneratorManager().removeGenerator(b);
          return false;
        } 
      } else {
        sender.sendMessage(ChatColor.RED + "/generator [set/remove] [itemType] [seconds]");
      } 
    } 
    return false;
  }
  
  private boolean isDouble(String text) {
    try {
      Double.parseDouble(text);
      return true;
    } catch (Exception e) {
      return false;
    } 
  }
}
