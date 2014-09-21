package me.migsect.Bitkit.Listeners;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Menu;
import me.migsect.Bitkit.Player.BitkitPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
		BitkitPlayer player = plugin.playerHandler.getPlayer((Player) event.getWhoClicked());
		Inventory inv = event.getInventory();
		if(!plugin.menuHandler.isActiveMenu(inv)) return;
		event.setCancelled(true);
		Menu menu = plugin.menuHandler.getMenu(inv);
		int slot = event.getSlot();
		
		menu.getItem(slot).onClick(player);
	}
}
