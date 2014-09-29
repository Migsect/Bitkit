package me.migsect.Bitkit;

import java.util.logging.Logger;

import me.migsect.Bitkit.Commands.CommandHandler;
import me.migsect.Bitkit.Commands.CommandHeader;
import me.migsect.Bitkit.Commands.CommandHistory;
import me.migsect.Bitkit.Commands.CommandRedo;
import me.migsect.Bitkit.Commands.CommandToolBox;
import me.migsect.Bitkit.Commands.CommandUndo;
import me.migsect.Bitkit.Listeners.ClickListener;
import me.migsect.Bitkit.Listeners.PlayerListener;
import me.migsect.Bitkit.MenuGUI.MenuHandler;
import me.migsect.Bitkit.Player.PlayerHandler;
import me.migsect.Bitkit.Tools.BlockTool;
import me.migsect.Bitkit.Tools.ReplaceTool;
import me.migsect.Bitkit.Tools.ToolHandler;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Bitkit extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public CommandHandler commandHandler;
	public PlayerHandler playerHandler;
	public ToolHandler toolHandler;
	public MenuHandler menuHandler;
	
	// Enabling
	public void onEnable()
	{
		// Server Log Messaging
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info(pdf.getName() + " Version " + pdf.getVersion() + " has been enabled.");
		
		// Listener Handling
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ClickListener(this), this);
		pm.registerEvents(new PlayerListener(this), this);
		
		// Config Handling
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		// Command handling
		commandHandler = new CommandHandler(this);
		getCommand(".").setExecutor(commandHandler);
		
		commandHandler.register(new CommandHeader(this));
		commandHandler.register(new CommandUndo(this));
		commandHandler.register(new CommandRedo(this));
		commandHandler.register(new CommandHistory(this));
		commandHandler.register(new CommandToolBox(this));
		
		// Player Handling
		playerHandler = new PlayerHandler();
		playerHandler.registerAllPlayters();
		
		// Tool Handling
		toolHandler = new ToolHandler(this);
		toolHandler.registerTool(new BlockTool());
		toolHandler.registerTool(new ReplaceTool());
		
		// Menu Handler
		menuHandler = new MenuHandler();
	}
	
	// Disabling
	public void onDisable()
	{
		// Server Log Message
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info(pdf.getName() + " has been disabled");
		
		// Player handling
		playerHandler.deregisterAllPlayters();
	}
	
	
}
