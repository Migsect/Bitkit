package me.migsect.Bitkit.Tools;

import java.util.HashMap;
import java.util.List;

import me.migsect.Bitkit.Player.BitkitPlayer;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class ToolData
{
	// This class is constructed to get tool info.
	ToolHandler handler;
	
	ItemStack item;
	BitkitPlayer holdingPlayer;
	HashMap<String, String> data = new HashMap<String, String>();
	
	
	public ToolData(ItemStack item, ToolHandler handler, BitkitPlayer player)
	{
		this.handler = handler;
		this.holdingPlayer = player;
		this.item = item;
		this.constructData();
	}
	
	private void constructData()
	{
		List<String> lore = item.getItemMeta().getLore();
		if(item.getType().isBlock())
		{
			data.put("tool", "Block");
		}
		if(!item.getItemMeta().hasLore()) return;
		String lore_line_one = ChatColor.stripColor(lore.get(0));
		if(!item.getType().isBlock() && !lore_line_one.startsWith("Tool: ")) return;
		for(int i = 0; i < lore.size(); i++)
		{
			String lore_line = ChatColor.stripColor(lore.get(i));
			if(lore_line.startsWith("Tool: "))data.put("tool", lore_line.substring(6));
			if(lore_line.startsWith("Block: "))data.put("block", lore_line.substring(7));
			if(lore_line.startsWith("Block Data: "))data.put("block_data", lore_line.substring(12));
			if(lore_line.startsWith("State: "))data.put("state", lore_line.substring(7));
			if(lore_line.startsWith("Reach: "))data.put("reach", lore_line.substring(7));
			if(lore_line.startsWith("Size: "))data.put("size", lore_line.substring(6));
			if(lore_line.startsWith("Region: "))data.put("region", lore_line.substring(8));
			if(lore_line.startsWith("Corners: "))data.put("corners", lore_line.substring(9));
			if(lore_line.startsWith("Surface: "))data.put("surface", lore_line.substring(9));
			
		}
	}
	
	// getToolType() returns the tool type that matches with the string name.
	public Tool getToolType()
	{
		return handler.getTool(data.get("tool"));
	}
	// hasData() checks if the data exists, otherwise returns false;
	public boolean hasData(String string)
	{
		return data.containsKey(string);
	}
	// getData() returns the data that is stored in the data hashmap.
	//   make sure you test to make the the data actually exists before you try to grab it.
	public String getData(String string)
	{
		return data.get(string);
	}
	// getItem() returns the item that the toolData was constructed from.
	public ItemStack getItem()
	{
		return item;
	}
	
	// getBitkitPlayer returns the holding player of this tool.
	public BitkitPlayer getHoldingPlayer()
	{
		return holdingPlayer;
	}
}
