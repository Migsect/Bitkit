package me.migsect.Bitkit.Tools;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class BlockTool extends Tool
{
	public BlockTool()
	{
		this.tag = "block";
		this.displayName = "Block";
		this.canBreakBlocks = false;
	}
	@Override
	public void blockBreak(Block block, ToolData data)
	{
		ItemStack item = data.getItem();
		MaterialData mat_data = item.getData();
		
		BlockState state = block.getState();
		state.setType(mat_data.getItemType());
		state.setData(mat_data);
		state.update(true);
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
	public void rightClickBlock(Block block, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftClickBlock(Block block, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftRightClickBlock(Block block, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftLeftClickBlock(Block block, ToolData data)
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
