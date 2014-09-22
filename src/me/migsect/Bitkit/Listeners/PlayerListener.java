package me.migsect.Bitkit.Listeners;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Menu;
import me.migsect.Bitkit.Player.BitkitPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

// This class will listen to player things like logging in and logging out.
public class PlayerListener implements Listener
{
	Bitkit plugin;
	
	public PlayerListener(Bitkit plugin)
	{
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		plugin.playerHandler.registerPlayer(player);
		
	}
	
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		plugin.playerHandler.deregisterPlayer(player);
	}
	
	public void onPlayerMenuSelect(InventoryClickEvent event)
	{
		Player player = (Player)event.getWhoClicked();
		if(!plugin.menuHandler.hasActiveMenu(player)) return;
		Menu menu = plugin.menuHandler.getMenu(player);
		
		int click_slot = event.getSlot();
		menu.clickOption(click_slot);
	}      
	public void onPlayerMenuClose(InventoryCloseEvent event)
	{
		Player player = (Player)event.getPlayer();
		if(!plugin.menuHandler.hasActiveMenu(player)) return;
		Menu menu = plugin.menuHandler.getMenu(player);
		
		menu.close();
	}
}
