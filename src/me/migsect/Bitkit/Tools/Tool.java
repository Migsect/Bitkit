package me.migsect.Bitkit.Tools;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public abstract class Tool
{
	protected final String name = "Tool";
	protected final boolean canBreak = true;


	
	public final String getName(){return name;}
	public final boolean canBreak(){return canBreak;}
	
	public abstract void rightClick(Location loc, ItemStack tool);
	public abstract void leftClick(Location loc, ItemStack tool);
	public abstract void shiftRightClick(Location loc, ItemStack tool);
	public abstract void shiftLeftClick(Location loc, ItemStack tool);
	
}
