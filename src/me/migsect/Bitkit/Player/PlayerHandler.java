package me.migsect.Bitkit.Player;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerHandler
{
	
	HashMap<String, BitkitPlayer> players = new HashMap<String, BitkitPlayer>();
	
	public PlayerHandler()
	{
		
	
	}
	
	public void registerPlayer(Player player)
	{
		BitkitPlayer bPlayer = new BitkitPlayer(player);
		players.put(player.getName(), bPlayer); // May need to update to UUID
		player.sendMessage(ChatColor.GOLD + "You have been registered.");
	}
	
	public void deregisterPlayer(Player player)
	{
		players.remove(player.getName());
		player.sendMessage(ChatColor.GOLD + "You have been deregistered.");
	}
	public void deregisterPlayer(BitkitPlayer player)
	{
		deregisterPlayer(player.getPlayer());
	}
	
	public void registerAllPlayters()
	{
		for(int i = 0; i < Bukkit.getOnlinePlayers().length; i++)
		{
			this.registerPlayer(Bukkit.getOnlinePlayers()[i]);
		}
	}
	public void deregisterAllPlayters()
	{
		for(int i = 0; i < Bukkit.getOnlinePlayers().length; i++)
		{
			this.deregisterPlayer(Bukkit.getOnlinePlayers()[i]);
		}
	}
	
	public BitkitPlayer getPlayer(String playerName)
	{
		return players.get(playerName);
	}
	
	public BitkitPlayer getPlayer(Player player)
	{
		return getPlayer(player.getName());
	}
}
