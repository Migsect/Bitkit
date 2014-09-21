package me.migsect.Bitkit.MenuGUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.Inventory;

public class MenuHandler
{
	public List<Menu> activeMenus = new ArrayList<Menu>();
	
	public void addActiveMenu(Menu menu)
	{
		activeMenus.add(menu);
	}
	public void remActiveMenu(Menu menu)
	{
		if(activeMenus.contains(menu)) activeMenus.remove(menu);
		for(int i = 0; i < menu.updateInventory().getViewers().size(); i++)
		{
			menu.updateInventory().getViewers().get(i).closeInventory();
		}
	}
	
	public void remAllActiveMenus()
	{
		for(int i = 0; i < activeMenus.size(); i++)
		{
			remActiveMenu(activeMenus.get(i));
		}
	}
	
	public boolean isActiveMenu(Inventory inv)
	{
		for(int i = 0; i < activeMenus.size(); i++)
		{
			if(activeMenus.get(i).equals(inv)) return true;
		}
		return false;
	}
	public Menu getMenu(Inventory inv)
	{
		for(int i = 0; i < activeMenus.size(); i++)
		{
			if(activeMenus.get(i).getInventory().equals(inv)) return activeMenus.get(i);
		}
		return null;
	}
}
