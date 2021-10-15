package me.hurricane.main;


import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemGenerator extends JavaPlugin {
  private GeneratorManager generatorManager;
  
  public void onEnable() {
    this.generatorManager = new GeneratorManager((Plugin)this);
    registerCommands();
  }
  
  private void registerCommands() {
    getCommand("generator").setExecutor((CommandExecutor)new GeneratorCommand(this));
  }
  
  public GeneratorManager getGeneratorManager() {
    return this.generatorManager;
  }
}
