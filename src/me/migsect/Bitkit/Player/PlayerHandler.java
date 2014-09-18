package me.migsect.Bitkit.Player;

import java.util.HashMap;

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
	}
	
	public void deregisterPlayer(Player player)
	{
		players.remove(player.getName());
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
