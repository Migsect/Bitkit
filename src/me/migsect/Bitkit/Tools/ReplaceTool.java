package me.migsect.Bitkit.Tools;

import java.util.ArrayList;
import java.util.List;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Menu;
import me.migsect.Bitkit.MenuGUI.Options.DataChange;
import me.migsect.Bitkit.MenuGUI.Options.Option;
import me.migsect.Bitkit.MenuGUI.Options.OptionDataChange;
import me.migsect.Bitkit.MenuGUI.Options.OptionItem;
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
	public ReplaceTool(Bitkit plugin)
	{
		super(plugin);
		this.tag = "replacer";
		this.displayName = "Replacer";
		this.canBreakBlocks = false;
		this.canShiftBreakBlocks = true;
	}
	
	@Override
	public List<Option> makeToolBoxOptions()
	{
		List<Option> options = new ArrayList<Option>();
		
		// Basic Replacer Tool, does not have Surface nor Corners on.
		ItemStack item1 = new ItemStack(Material.GOLD_SPADE);
		ItemMeta im1 = item1.getItemMeta();
		im1.setDisplayName(ChatColor.GOLD + "Replace Block Tool");
		
		
		List<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Tool: Replacer");
		lore1.add(ChatColor.YELLOW + "Block: " + Material.AIR.toString());
		lore1.add(ChatColor.YELLOW + "Reach: 20");
		lore1.add(ChatColor.YELLOW + "Surface: False");
		lore1.add(ChatColor.YELLOW + "Corners: False");
		im1.setLore(lore1);
		
		item1.setItemMeta(im1);
		
		OptionItem option1= new OptionItem(item1);
		option1.setMaterial(Material.GOLD_SPADE);
		option1.setName(ChatColor.GOLD + "Replacer Tool");
		option1.getLoreText().add(ChatColor.WHITE + "Used to replace groups of blocks.");
		options.add(option1);
		
		// Next item
		ItemStack item2 = new ItemStack(Material.GOLD_SPADE);
		ItemMeta im2 = item2.getItemMeta();
		im2.setDisplayName(ChatColor.GOLD + "Replace Block Tool");
		
		
		List<String> lore2 = new ArrayList<String>();
		lore2.add(ChatColor.YELLOW + "Tool: Replacer");
		lore2.add(ChatColor.YELLOW + "Block: " + Material.AIR.toString());
		lore2.add(ChatColor.YELLOW + "Reach: 20");
		lore2.add(ChatColor.YELLOW + "Surface: True");
		lore2.add(ChatColor.YELLOW + "Corners: False");
		im2.setLore(lore2);
		
		item2.setItemMeta(im2);
		
		OptionItem option2 = new OptionItem(item2);
		option2.setMaterial(Material.GOLD_SPADE);
		option2.setName(ChatColor.GOLD + "Replacer Tool");
		option2.getLoreText().add(ChatColor.WHITE + "Used to replace groups of blocks.");
		option2.getLoreText().add(ChatColor.WHITE + "[SURFACE VERSION]");
		options.add(option2);

		
		// Next Item
		ItemStack item3 = new ItemStack(Material.GOLD_SPADE);
		ItemMeta im3 = item3.getItemMeta();
		im3.setDisplayName(ChatColor.GOLD + "Replace Block Tool");
		
		
		List<String> lore3 = new ArrayList<String>();
		lore3.add(ChatColor.YELLOW + "Tool: Replacer");
		lore3.add(ChatColor.YELLOW + "Block: " + Material.AIR.toString());
		lore3.add(ChatColor.YELLOW + "Reach: 20");
		lore3.add(ChatColor.YELLOW + "Surface: False");
		lore3.add(ChatColor.YELLOW + "Corners: True");
		im3.setLore(lore3);
		
		item3.setItemMeta(im3);
		
		OptionItem option3 = new OptionItem(item3);
		option3.setMaterial(Material.GOLD_SPADE);
		option3.setName(ChatColor.GOLD + "Replacer Tool");
		option3.getLoreText().add(ChatColor.WHITE + "Used to replace groups of blocks.");
		option3.getLoreText().add(ChatColor.WHITE + "[CORNERS VERSION]");
		options.add(option3);
	
		
		return options;
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
				if(!corners)
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
				else // this will do 26 checks and may be slower than above.  As such we give this as an option.
				{
					// This is very inefficient compared to the above one and has room
					//   for optimization.  Horever we will still need to check if each
					//   block being checked is withing the list and there is no
					//   easy way to calculating this.  (Of course the counter will depict
					//   the overall square range the block will be from the center and
					//   we will only add blocks in this range.  We would have corner blocks
					//   and other wise.  This will be optimized later when the plugin needs it.
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
		if(surface)
		{
			List<BlockState> new_to_change = new ArrayList<BlockState>();
			for(int k = 0; k < to_change.size(); k++)
			{
				if(nextToAir(to_change.get(k).getBlock()))
				{
					new_to_change.add(to_change.get(k));
				}
			}
			to_change = new_to_change;
		}
		for(int j = 0; j < to_change.size(); j++)
		{
			// We are going to handle surface checks within the block changing.
			if(surface && !nextToAir(to_change.get(j).getBlock())) continue;
			
			// Adding the block to the action.
			new_action.addBlock(to_change.get(j).getBlock().getState());
			
			// Actually doing the block change.
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

	// Used to change different features of the tool.
	@Override
	public void shiftRightClick(Location loc, ToolData data)
	{
		Menu menu = new Menu(data.getHoldingPlayer(), plugin, this.displayName + "'s options.");
		
		OptionDataChange opt1 = new OptionDataChange(data.getData("surface"), "Surface", DataChange.TOGGLE);
		menu.addOption(opt1, 1);
		
		
		menu.open();
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
