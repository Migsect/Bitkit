package me.migsect.Bitkit.Tools;

import java.util.ArrayList;
import java.util.List;

import me.migsect.Bitkit.Player.BlockAction;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class ReplaceTool extends Tool
{
	public ReplaceTool()
	{
		this.tag = "replacer";
		this.displayName = "Replacer";
		this.canBreakBlocks = false;
		this.canShiftBreakBlocks = true;
	}
	
	@Override
	public void blockBreak(Block block, ToolData data)
	{
		// Bukkit.getLogger().info(data.getData("block"));
		Material block_type = Material.getMaterial(data.getData("block"));
		// Bukkit.getLogger().info(block_type.toString());
		// Bukkit.getLogger().info(data.getData("block_data"));
		
		boolean corners = false;
		if(data.hasData("corners") && data.getData("corners").equalsIgnoreCase("true")) corners = true;
		boolean surface = false;
		if(data.hasData("surface") && data.getData("surface").equalsIgnoreCase("true")) surface = true;
		
		short block_data = 0;
		try
		{
			block_data = Short.parseShort(data.getData("block_data"));
		}
		catch (NumberFormatException e)
		{
			// Do nothing
		}
		// Bukkit.getLogger().info(data.getData("reach"));
		int reach = 1;
		try
		{
			reach = Integer.parseInt(data.getData("reach"));
		}
		catch (NumberFormatException e)
		{
			// Do nothing
		}
		
		MaterialData mat_data_break = block.getState().getData();
		List<BlockState> to_change = new ArrayList<BlockState>(); // these blocks will be set and will not have any blocks grown out of.
		List<BlockState> last_added = new ArrayList<BlockState>(); // these blocks will have their neighbors checked. 
		List<BlockState> last_added_new = new ArrayList<BlockState>(); // these are freshly added blocks that will be checked next loop.

		// We are going to use lists to add all the blocks.  We will then add 
		last_added.add(block.getState());
		for(int i = 0; i < reach; i++)
		{
			for(int c = 0; c < last_added.size(); c++)
			{
				BlockState check;
				if(!corners && !surface)
				{
					check = last_added.get(c).getBlock().getRelative(BlockFace.NORTH).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check)) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.SOUTH).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check)) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.EAST).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check)) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.WEST).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check)) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.UP).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check)) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.DOWN).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check)) last_added_new.add(check);
				}
				else if(!surface)// this will do 26 checks and may be slower than above.  As such we give this as an option.
				{
					for(int x = -1; x < 2; x++)
					{
						for(int y = -1; y < 2; y++)
						{
							for(int z = -1; z < 2; z++)
							{
								if(x == 0 && y == 0 && z == 0) continue;
								check = last_added.get(c).getBlock().getRelative(x, y, z).getState();
								if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check)) last_added_new.add(check);		
							}
						}
					}
				}
				else if(!corners)
				{
					check = last_added.get(c).getBlock().getRelative(BlockFace.NORTH).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check) && nextToAir(check.getBlock())) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.SOUTH).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check) && nextToAir(check.getBlock())) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.EAST).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check) && nextToAir(check.getBlock())) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.WEST).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check) && nextToAir(check.getBlock())) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.UP).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check) && nextToAir(check.getBlock())) last_added_new.add(check);
					check = last_added.get(c).getBlock().getRelative(BlockFace.DOWN).getState();
					if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check) && nextToAir(check.getBlock())) last_added_new.add(check);
				}
				else
				{
					for(int x = -1; x < 2; x++)
					{
						for(int y = -1; y < 2; y++)
						{
							for(int z = -1; z < 2; z++)
							{
								if(x == 0 && y == 0 && z == 0) continue;
								check = last_added.get(c).getBlock().getRelative(x, y, z).getState();
								if(check.getData().equals(mat_data_break) && !to_change.contains(check) && !last_added.contains(check) && !last_added_new.contains(check) && nextToAir(check.getBlock())) last_added_new.add(check);		
							}
						}
					}
				}
				// Bukkit.getLogger().info("Checked: " + check.getBlock().getLocation().toString());
			}
			to_change.addAll(last_added);
			last_added.clear();
			last_added.addAll(last_added_new);
			last_added_new.clear();
		}
		// setting the blocks to their new form.
		ItemStack replace_type = new ItemStack(block_type,1,block_data);
		
		BlockAction new_action = new BlockAction(data.getHoldingPlayer());
		for(int j = 0; j < to_change.size(); j++)
		{
			new_action.addBlock(to_change.get(j).getBlock().getState());
			to_change.get(j).setType(replace_type.getType());
			to_change.get(j).setData(replace_type.getData());
			to_change.get(j).update(true);
		}
		data.getHoldingPlayer().addUndoAction(new_action);
		
	}
	
	private boolean nextToAir(Block block){
		if(block.getRelative(BlockFace.NORTH).getType().equals(Material.AIR)) return true;
		if(block.getRelative(BlockFace.SOUTH).getType().equals(Material.AIR)) return true;
		if(block.getRelative(BlockFace.EAST).getType().equals(Material.AIR)) return true;
		if(block.getRelative(BlockFace.WEST).getType().equals(Material.AIR)) return true;
		if(block.getRelative(BlockFace.UP).getType().equals(Material.AIR)) return true;
		if(block.getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) return true;
		/*
		for(int x = -1; x < 2; x++)
		{
			for(int y = -1; y < 2; y++)
			{
				for(int z = -1; z < 2; z++)
				{
					if(x == 0 && y == 0 && z == 0) continue;
					if(block.getRelative(x, y, z).getType().equals(Material.AIR)) return true; 
				}
			}
		}
		*/
		return false;
	}
	
	@Override
	public void shiftBlockBreak(Block block, ToolData data)
	{
		// TODO Auto-generated method stub
		
	}
	
	// rightClick will select a block that the click on.
	@Override
	public void rightClick(Location loc, ToolData data)
	{
		
		
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

		BlockState state = block.getState();
		ItemStack tool = data.getHoldingPlayer().getPlayer().getItemInHand();
		ItemMeta im = tool.getItemMeta();
		
		Bukkit.getLogger().info("Click Registered at " + state.getType() + " | " + state.getLocation().toString());
		
		Material item_mat = state.getType();
		short item_data = state.getData().toItemStack().getDurability();
		
		List<String> lore = im.getLore();
		int block_i = 0;
		boolean has_data_spot = false;
		for(int i = 0; i < lore.size(); i++)
		{
			String lore_line = ChatColor.stripColor(lore.get(i));
			Bukkit.getLogger().info(lore_line);
			if(lore_line.startsWith("Block: "))
			{
				lore.set(i, ChatColor.YELLOW + "Block: " + item_mat.toString());
				block_i = i;
				Bukkit.getLogger().info(lore.get(i));
			}
			else if(lore_line.startsWith("Block Data: "))
			{
				lore.set(i, ChatColor.YELLOW + "Block Data: " + item_data);
				has_data_spot = true;
			}
		}
		if(item_data != 0 && !has_data_spot)
		{
			lore.add(block_i + 1,ChatColor.YELLOW + "Block Data: " + item_data);
		}
		
		im.setLore(lore);
		tool.setItemMeta(im);
		
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
