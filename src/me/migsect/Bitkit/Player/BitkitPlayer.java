package me.migsect.Bitkit.Player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BitkitPlayer
{
	private Player player;
	
	private List<ItemStack> palette = new ArrayList<ItemStack>();
	private List<BlockAction> blockHistory = new ArrayList<BlockAction>();
	
	public BitkitPlayer(Player player)
	{
		this.player = player;
	}
	
	public Player getPlayer()
	{
		return player;
	}
}
