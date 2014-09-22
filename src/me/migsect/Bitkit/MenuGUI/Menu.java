package me.migsect.Bitkit.MenuGUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Options.Option;
import me.migsect.Bitkit.Player.BitkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu
{
	private BitkitPlayer player; // player this menu is being displayed to
	private Inventory inventory; // Inventory that will be displayed.
	private int max_slots = 54; // Slots the inventory will have.
	private String inv_title; // title of the inventory.  This cannot be mutated.
	
	private MenuHandler handler;
	
	private HashMap<Integer,Option> options = new HashMap<Integer, Option>(); // a list of all the items in the inventory.
	
	// Menu() main constructor.
	public Menu(BitkitPlayer player, Bitkit plugin, String menu_title)
	{
		this.player = player;
		this.handler = plugin.menuHandler;
		this.handler.register(this);
		this.inv_title = menu_title;
		inventory = Bukkit.createInventory(null, max_slots, inv_title);
	}
	
	
	// updateInventory() will loop through the list of menuItems and update them.
	public void updateInventory()
	{
		List<Integer> active_slots = new ArrayList<Integer>();
		active_slots.addAll(options.keySet());
		for(int i = 0; i < active_slots.size(); i++)
		{
			ItemStack item = options.get(i).generateItemStack();
			inventory.setItem(i, item);
		}
	}
	
	// addOption() will add an option to the menu.  This will automatically set the options menu as well.
	public void addOption(Option opt, int slot)
	{
		options.put(slot, opt);
		opt.setMenu(this);
	}
	
	// remOption() will remove an option from a menu.  This should be used for a clean removal of an option.
	public void remOption(int slot)
	{
		options.get(slot).setMenu(null);
		options.remove(slot);
	}
	
	// isViewed() will return a boolean on whether the inventory is currently
	//   being viewed by its player. (should only be its player)
	//   
	public boolean isViewed()
	{
		if(inventory.getViewers().size() > 0) return true;
		return false;
	}
	
	// open() will open the menu for the player.  It will also force an update of the menu.
	public void open()
	{
		this.updateInventory();
		player.getPlayer().openInventory(inventory);
	}
	
	// close() will do all the neccessary clean up of the menu when it needs to be emptied.
	//   This is for preventing a memory-link when using the menus.
	public void close()
	{
		handler.deregister(this);
	}
	
	// getPlayer() returns the menu's player.
	public BitkitPlayer getPlayer(){return player;}
	
	// activeOption() will make the option perform its "onClick()" operation.
	public void clickOption(int slot)
	{
		Option option = options.get(slot);
		option.onClick(player);
	}
	
	// getInventory() will return the inventory of this menu.  This is mostly used for checking.
	//   we may need to protect this and give the menu handler access to it so other classes dont try to be smart.
	public Inventory getInventory(){return inventory;}
}
