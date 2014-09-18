package me.migsect.Bitkit.Tools;

import java.util.HashMap;
import java.util.List;

import me.migsect.Bitkit.Player.BitkitPlayer;

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
		if(!item.getType().isBlock() && !lore.get(0).startsWith("Tool: ")) return;
		if(!item.getType().isBlock())
		{
			data.put("tool", "Block");
		}
		for(int i = 0; i < lore.size(); i++)
		{
			if(lore.get(i).startsWith("Tool: "))data.put("tool", lore.get(0).substring(6));
			if(lore.get(i).startsWith("State: ")) data.put("state", lore.get(i).substring(7));
			if(lore.get(i).startsWith("Size: "))data.put("size", lore.get(i).substring(6));
			if(lore.get(i).startsWith("Region: "))data.put("region", lore.get(i).substring(8));
			
		}
	}
	
	public Tool getToolType()
	{
		return handler.getTool(data.get("tool"));
	}
	public String getData(String string)
	{
		return data.get(string);
	}
	public ItemStack getItem()
	{
		return item;
	}
}
