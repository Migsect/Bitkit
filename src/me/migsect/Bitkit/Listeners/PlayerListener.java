package me.migsect.Bitkit.Listeners;

import me.migsect.Bitkit.Bitkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
}
