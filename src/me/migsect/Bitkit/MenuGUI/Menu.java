package me.migsect.Bitkit.MenuGUI;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Menu
{
	Player player;
	MenuHandler handler;
	HashMap<Integer,MenuItem> menuItems = new HashMap<Integer, MenuItem>();
	String title = "Default Menu Title";
	Inventory inventory;
	int maxSlot = 0;
	
	// Menus will be constructed on hand.  MENUs SHOULD NOT BE KEPT PERMANENT.
	public Menu(Player player, MenuHandler handler)
	{
		this.player = player;
		this.updateInventory();
		this.handler = handler;
	}
	
	public void addItem(int slot, MenuItem item)
	{
		if(maxSlot < slot) maxSlot = slot;
		
	}
	public boolean slotTaken(int slot)
	{
		if(menuItems.containsKey(slot)) return true;
		return false;
	}
	public MenuItem getItem(int slot)
	{
		if(!menuItems.containsKey(slot)) return null;
		return menuItems.get(slot);
	}
	// openMenu opens the menu for the player.  This also adds the menu to the player who it is set up for.
	public void openMenu()
	{
		player.openInventory(updateInventory());
	}
	// isBeingViewed represents that the menu is being viewed
	public boolean isBeingViewed()
	{
		return inventory.getViewers().size() > 0;
	}
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public Inventory updateInventory()
	{
		int inventorySize = 9 + maxSlot / 9;
		Inventory newInventory = Bukkit.createInventory(null, inventorySize, title);
		// TODO: Code to add all menu items to the menu.

		inventory = newInventory;
		return inventory;
	}
}
