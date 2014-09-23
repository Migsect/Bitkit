package me.migsect.Bitkit.Tools;

import org.bukkit.Location;
import org.bukkit.block.Block;

public abstract class Tool
{
	protected String tag = "tool";
	protected String displayName = "Tool";
	protected boolean canBreakBlocks = true;


	public final String getTag(){return tag;}
	public final String getName(){return displayName;}
	public final boolean canBreak(){return canBreakBlocks;}
	
	// location will either be the block that is clicked or if the player clicks air
	public abstract void blockBreak(Block block, ToolData data);
	public abstract void shiftBlockBreak(Block block, ToolData data);
	
	public abstract void rightClick(Location loc, ToolData data);
	public abstract void leftClick(Location loc, ToolData data);
	public abstract void shiftRightClick(Location loc, ToolData data);
	public abstract void shiftLeftClick(Location loc, ToolData data);
	
	public abstract void rightClickBlock(Block block, ToolData data);
	public abstract void leftClickBlock(Block block, ToolData data);
	public abstract void shiftRightClickBlock(Block block, ToolData data);
	public abstract void shiftLeftClickBlock(Block block, ToolData data);
	
	public abstract void rightClickAir(Location loc, ToolData data);
	public abstract void leftClickAir(Location loc, ToolData data);
	public abstract void shiftRightClickAir(Location loc, ToolData data);
	public abstract void shiftLeftClickAir(Location loc, ToolData data);
	
}
