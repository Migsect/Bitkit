package me.migsect.Bitkit.MenuGUI;

import java.util.ArrayList;
import java.util.List;

import me.migsect.Bitkit.Player.BitkitPlayer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class MenuItem
{
	// A menu item is the basic type.  It will consist of a "On click" in which
	//   will do something when a player does something.
	
	String title = "Default Item Title";
	List<String> loreText = new ArrayList<String>();
	Material material = Material.GRASS;
	int data = 0; // this refers to colors and stuff.
	Menu menu;
	
	public MenuItem(Menu menu)
	{
		this.menu = menu;
	}
	
	// On Click can do a multitude of things.  Firstly it can do a one time
	//   effect.  On the other hand it may toggle a state.
	public abstract void onClick(BitkitPlayer player);
	
	
	protected ItemStack generateItemStack(){
		
		
		return null;
	}
}
