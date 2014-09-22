package me.migsect.Bitkit.Palette;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Palette
{
	// Palettes are used to store upto 9 items.  They are used to easily switch
	//   between selected items as creative is really annoying to switch cards.
	//   Palettes are only stored information on item-load outs and only operate on
	//   an active-response basis.
	List<ItemStack> items = new ArrayList<ItemStack>();
}
