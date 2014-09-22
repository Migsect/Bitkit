package me.migsect.Bitkit.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BitkitPlayer
{
	private Player player;
	
	@SuppressWarnings("unused")
	private List<ItemStack> palette = new ArrayList<ItemStack>();
	@SuppressWarnings("unused")
	private List<BlockAction> blockHistory = new ArrayList<BlockAction>();
	
	private HashMap<String, Boolean> toggled_options = new HashMap<String, Boolean>();
	
	// BitKitPlayer() constructor takes an input of Player.  This is a basic extension of a player.
	public BitkitPlayer(Player player)
	{
		this.player = player;
	}
	
	// getPlayer() returns the player that this class wraps.
	public Player getPlayer()
	{
		return player;
	}
	
	// getToggle() returns a bool if the player can do that toggled_opt.
	//   if they don't have that currently set then it returns false;
	public boolean getToggle(String toggled_opt)
	{
		if(toggled_options.containsKey(toggled_opt)) return toggled_options.get(toggled_opt);
		return false;
	}
	
	// setToggle() will hard set a toggled option to a boolean value.
	public void setToggle(String toggled_opt, boolean bool)
	{
		toggled_options.put(toggled_opt, bool);
	}
	// toggleToggle() will toggle the option.  If there is currently no option it will set it to true.
	public void toggleToggle(String toggled_opt)
	{
		if(!toggled_options.containsKey(toggled_opt))
		{
			toggled_options.put(toggled_opt, true);
			return;
		}
		toggled_options.put(toggled_opt, !toggled_options.get(toggled_opt));
		
	}
}
