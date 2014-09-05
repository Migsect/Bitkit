package me.migsect.Bitkit;

import java.util.logging.Logger;

import me.migsect.Bitkit.Commands.CommandHandler;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Bitkit extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public CommandHandler commandHandler;
	
	// Enabling
	public void onEnable()
	{
		// Server Log Messaging
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info(pdf.getName() + " Version " + pdf.getVersion() + " has been enabled.");
		
		// Listener Handling
		
		// Config Handling
		
		// Command handling
		commandHandler = new CommandHandler(this);
	}
	
	// Disabling
	public void onDisable()
	{
		// Server Log Message
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info(pdf.getName() + " has been disabled");
		
	}
	
	
}
