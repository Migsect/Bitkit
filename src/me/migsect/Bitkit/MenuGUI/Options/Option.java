package me.migsect.Bitkit.MenuGUI.Options;

import java.util.ArrayList;
import java.util.List;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Menu;
import me.migsect.Bitkit.Player.BitkitPlayer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class Option
{
	// A menu item is the basic type.  It will consist of a "On click" in which
	//   will do something when a player does something.
	
	// Menu Display Modifiers (translated into an itemstack)
	String item_name = "Default Option Title";
	List<String> lore_text = new ArrayList<String>();
	Material material = Material.GRASS;
	short material_data = 0; // this refers to colors and stuff.
	
	// other stuff
	Menu menu;

	public void setMenu(Menu menu){this.menu = menu;}
	
	public Menu getMenu(){return menu;}
	
	public void setName(String name){this.item_name = name;}
	public void setLoreText(List<String> lore_text){this.lore_text = lore_text;}
	public void setMaterial(Material material){this.material = material;}
	public void setMaterialData(short data){this.material_data = data;}
	
	public List<String> getLoreText(){return lore_text;}
	
	
	// On Click can do a multitude of things.  Firstly it can do a one time
	//   effect.  On the other hand it may toggle a state.
	public abstract void onClick(BitkitPlayer player);
	
	
	public ItemStack generateItemStack()
	{
		ItemStack item = new ItemStack(material, 1, material_data);
		item.getItemMeta().setLore(lore_text);
		item.getItemMeta().setDisplayName(item_name);
		
		return item;
	}
}
