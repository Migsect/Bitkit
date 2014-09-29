package me.migsect.Bitkit.MenuGUI.Options;

import org.bukkit.inventory.ItemStack;

import me.migsect.Bitkit.Player.BitkitPlayer;

public class OptionItem extends Option
{
	ItemStack given_item;
	
	public OptionItem(ItemStack given_item)
	{
		this.given_item = given_item;
	}
	
	@Override
	public void onClick(BitkitPlayer player)
	{
		player.getPlayer().getInventory().addItem(given_item);
		
	}

}
