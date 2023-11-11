package net.ddns.vcccd;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Target(), this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
