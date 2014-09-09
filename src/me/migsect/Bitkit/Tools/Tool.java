package me.migsect.Bitkit.Tools;

import org.bukkit.Location;

public abstract class Tool
{
	protected final String tag = "tool";
	protected final String displayName = "Tool";
	protected final boolean canBreakBlocks = true;


	public final String getTag(){return tag;}
	public final String getName(){return displayName;}
	public final boolean canBreak(){return canBreakBlocks;}
	
	// location will either be the block that is clicked or if the player clicks air
	public abstract void rightClick(Location loc, ToolData data);
	public abstract void leftClick(Location loc, ToolData data);
	public abstract void shiftRightClick(Location loc, ToolData data);
	public abstract void shiftLeftClick(Location loc, ToolData data);
	
}
