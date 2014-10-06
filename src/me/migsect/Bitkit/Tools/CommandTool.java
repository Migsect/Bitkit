package me.migsect.Bitkit.Tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Options.Option;
import me.migsect.Bitkit.MenuGUI.Options.OptionItem;

// CommandTool is essentially used as, well, a command tool.  
// The data for this will be the following:
//   - RC
//   - LC
//   - SRC
//   - SLC
// Each of these will correspond to a different kind of command that can be
//   assigned to an item.  A command can be used to add commands to items.  Furthermore
//   this allows further flexibility and integration with other plugins.
//   The six different states that this allows will furthermore allow great flexibility
//   with commands and other plugins.
public class CommandTool extends Tool
{

	public CommandTool(Bitkit plugin)
	{
		super(plugin);
		this.tag = "commander";
		this.displayName = "Commmander";
		this.canBreakBlocks = false;
		this.canShiftBreakBlocks = false;
	}

	@Override
	public List<Option> makeToolBoxOptions()
	{
		List<Option> options = new ArrayList<Option>();
		
		ItemStack item1 = new ItemStack(Material.CHEST);
		ItemMeta im1 = item1.getItemMeta();
		im1.setDisplayName(ChatColor.GOLD + "Tool Box Opener");
		
		
		List<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Tool: Commander");
		lore1.add(ChatColor.YELLOW + "RC: /. tb");
		im1.setLore(lore1);
		
		item1.setItemMeta(im1);
		
		OptionItem option1= new OptionItem(item1);
		option1.setMaterial(Material.CHEST);
		option1.setName(ChatColor.GOLD + "Tool Box Tool");
		option1.getLoreText().add(ChatColor.WHITE + "Used to open the toolbox without using a command!");
		options.add(option1);
		
		
		return options;
	}

	@Override
	public void blockBreak(Block block, ToolData data)
	{
		if(!data.hasData("bb")) return;
		String command = data.getData("bb");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void shiftBlockBreak(Block block, ToolData data)
	{
		if(!data.hasData("sbb")) return;
		String command = data.getData("sbb");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void rightClick(Location loc, ToolData data)
	{
		if(!data.hasData("rc")) return;
		String command = data.getData("rc");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void leftClick(Location loc, ToolData data)
	{
		if(!data.hasData("lc")) return;
		String command = data.getData("lc");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void shiftRightClick(Location loc, ToolData data)
	{
		if(!data.hasData("src")) return;
		String command = data.getData("src");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void shiftLeftClick(Location loc, ToolData data)
	{
		if(!data.hasData("slc")) return;
		String command = data.getData("slc");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void rightClickBlock(Block block, ToolData data)
	{
		if(!data.hasData("rcb")) return;
		String command = data.getData("rcb");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void leftClickBlock(Block block, ToolData data)
	{
		if(!data.hasData("lcb")) return;
		String command = data.getData("lcb");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void shiftRightClickBlock(Block block, ToolData data)
	{
		if(!data.hasData("srcb")) return;
		String command = data.getData("srcb");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void shiftLeftClickBlock(Block block, ToolData data)
	{
		if(!data.hasData("slcb")) return;
		String command = data.getData("slcb");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void rightClickAir(Location loc, ToolData data)
	{
		if(!data.hasData("rca")) return;
		String command = data.getData("rca");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void leftClickAir(Location loc, ToolData data)
	{
		if(!data.hasData("lca")) return;
		String command = data.getData("lca");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void shiftRightClickAir(Location loc, ToolData data)
	{
		if(!data.hasData("srca")) return;
		String command = data.getData("srca");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

	@Override
	public void shiftLeftClickAir(Location loc, ToolData data)
	{
		if(!data.hasData("slca")) return;
		String command = data.getData("slca");
		data.getHoldingPlayer().getPlayer().chat(command);
		
	}

}
