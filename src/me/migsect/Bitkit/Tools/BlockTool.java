package me.migsect.Bitkit.Tools;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class BlockTool extends Tool
{
	public BlockTool()
	{
		this.tag = "block";
		this.displayName = "Block";
		this.canBreakBlocks = true;
	}
	@Override
	public void blockBreak(Block block, ToolData data)
	{
		ItemStack item = data.getItem();
		
	}

	@Override
	public void shiftBlockBreak(Block block, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightClick(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftClick(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftRightClick(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftLeftClick(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightClickBlock(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftClickBlock(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftRightClickBlock(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftLeftClickBlock(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightClickAir(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftClickAir(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftRightClickAir(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftLeftClickAir(Location loc, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

}
