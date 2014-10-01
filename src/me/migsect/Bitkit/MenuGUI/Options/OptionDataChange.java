package me.migsect.Bitkit.MenuGUI.Options;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.migsect.Bitkit.Player.BitkitPlayer;

public class OptionDataChange extends Option
{
	String data = "";
	String data_disp = "";
	int change = 0;
	DataChange type;
	
	/* data is the data that the option will change the data_disp to.
	 * DataChange types determine what kind of data that will be changed.
	 * data isn't required
	 */
	public OptionDataChange(String data, String data_disp, DataChange type)
	{
		this.data_disp = data_disp;
		this.type = type;
		if(type == DataChange.INCREMENT)
		{
			this.change = 1;
			this.material = Material.WATER_BUCKET;
			this.item_name = ChatColor.GOLD + "Decrement " + data_disp + " By One";
		}
		else if(type == DataChange.DECREMENT)
		{
			this.change = -1;
			this.material = Material.BUCKET;
			this.item_name = ChatColor.GOLD + "Increment " + data_disp + " By One";
		}
		else if(type == DataChange.CHANGE)
		{
			this.material = Material.MILK_BUCKET;
			this.item_name = ChatColor.GOLD + "Change " + data_disp + " To " + data;
			try
			{
				this.change = Integer.parseInt(data);
			}
			catch (NumberFormatException e)
			{
				// Do nothing  It's 0 now.
			}
			
		}
		else if(type == DataChange.SET)
		{
			this.material = Material.ARROW;
			this.item_name = ChatColor.GOLD + "Set " + data_disp + " To " + data;
			this.data = data;
		}
		else if(type == DataChange.TOGGLE)
		{
			if(data == "true")
			{
				this.material = Material.REDSTONE;
				this.item_name = ChatColor.GOLD + "Toggle " + data_disp + " To False";
			}
			else
			{
				this.material = Material.SUGAR;
				this.item_name = ChatColor.GOLD + "Toggle " + data_disp + " To True";
			}
		}
	}
	// We are only going to mutate the data on that item.  Each DataChangeOption 
	//   Should have a string to look for, this will be the data type.
	@Override
	public void onClick(BitkitPlayer player)
	{
		ItemStack item = player.getPlayer().getItemInHand();
		// We can't do anything that touches the handler with this class.
		//   We are only making use of this for parsing the item.
		ItemMeta im = item.getItemMeta();
		
		if(!im.hasLore()) return;
		List<String> lore = im.getLore();
		
		int found_i = 0;
		String lore_data = "";
		for(int i = 0; i < lore.size(); i++)
		{
			if(lore.get(i).startsWith(data_disp + ": "))
			{
				found_i = i;
				lore_data = lore.get(i).substring(data_disp.length() + 2);
				break;
			}
		}
		String new_lore = "";
		if(type == DataChange.CHANGE || type == DataChange.INCREMENT || type == DataChange.DECREMENT )
		{
			int num_data = 0;
			try
			{
				num_data = Integer.parseInt(lore_data);
			}
			catch (NumberFormatException e)
			{
				// Do nothing  It's 0 now.
			}
			num_data += change;
			if(num_data < 0) num_data = 0;
			new_lore = data_disp + ": " + num_data;
		}
		else
		{
			if(type == DataChange.SET)
			{
				new_lore = data_disp + ": " + data;
			}
			if(type == DataChange.TOGGLE)
			{
				if(lore_data.equalsIgnoreCase("true"))
				{
					new_lore = data_disp + ": " + "False";
					this.material = Material.REDSTONE;
					this.item_name = ChatColor.GOLD + "Toggle " + data_disp + " To True";
				}
				if(lore_data.equalsIgnoreCase("false"))
				{
					new_lore = data_disp + ": " + "True";
					this.material = Material.SUGAR;
					this.item_name = ChatColor.GOLD + "Toggle " + data_disp + " To False";
				}
			}
		}
		lore.set(found_i, new_lore);
		im.setLore(lore);
		item.setItemMeta(im);
	}

}
