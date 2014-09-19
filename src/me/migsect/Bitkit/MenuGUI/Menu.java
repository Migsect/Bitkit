package me.migsect.Bitkit.MenuGUI;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Menu
{
	HashMap<Integer,MenuItem> menuItems = new HashMap<Integer, MenuItem>();
	String title = "Default Menu Title";
	int maxSlot = 0;
	
	// Menus will be constructed on hand.  MENUs SHOULD NOT BE KEPT PERMANENT.
	public Menu()
	{
		
	}
	
	public void addItem(int slot, MenuItem item)
	{
		if(maxSlot < slot) maxSlot = slot;
		
	}
	public MenuItem getItem(int slot, MenuItem item)
	{
		if(!menuItems.containsKey(slot)) return null;
		return menuItems.get(slot);
	}
	
	private Inventory generateInventory()
	{
		int inventorySize = 9 + maxSlot / 9;
		Inventory inventory = Bukkit.createInventory(null, inventorySize, title);
	
		
		
		return null;
	}
}
