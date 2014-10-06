package me.migsect.Bitkit.Tools;

import java.util.List;
import java.util.ArrayList;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Options.Option;

import org.bukkit.Location;
import org.bukkit.block.Block;

public abstract class Tool
{
	protected String tag = "tool";
	protected String displayName = "Tool";
	
	protected boolean canBreakBlocks = false;
	protected boolean canShiftBreakBlocks = true;
	protected boolean canBePlaced = false;
	protected boolean canBeUsed = false;
	
	protected Bitkit plugin;
	/*	item_data is used as a means of remembering what kind of data that
	 *  a tool carries.  This can is generally used for when you need to switch
	 *  the data of a tool item and we need to list all that a tool has.
	 */
	protected List<String> item_data = new ArrayList<String>();

	public Tool(Bitkit plugin)
	{
		this.plugin = plugin;
	}
	
	public final String getTag(){return tag;}
	public final String getName(){return displayName;}
	public final List<String> getItemData(){return item_data;}
	public final boolean canBreak(){return canBreakBlocks;}
	public final boolean canShiftBreak(){return canShiftBreakBlocks;}
	public final boolean canPlace(){return canBePlaced;}
	public final boolean canUsed(){return canBeUsed;}
	
	// MakeOptions will make the options for the tool box for this tool.
	public abstract List<Option> makeToolBoxOptions();
	
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
