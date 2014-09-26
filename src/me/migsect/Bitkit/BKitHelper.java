package me.migsect.Bitkit;

import org.bukkit.ChatColor;

public class BKitHelper
{
	// BKitHelper hosts the constant commands meant to help other functions.
	public static String spaceString(int n)
	{
		String str = "";
		for(int i = 0; i < n; i++)
		{
			str += " ";
		}
		return str;
	}
	public static String charString(int n, char c)
	{
		String str = "";
		for(int i = 0; i < n; i++)
		{
			str += c;
		}
		return str;
	}
	
	
	public static String formatString(String string)
	{
		String newString = string;
		
		newString = newString.replace("&0", "" + ChatColor.BLACK);
		newString = newString.replace("&1", "" + ChatColor.DARK_BLUE);
		newString = newString.replace("&2", "" + ChatColor.DARK_GREEN);
		newString = newString.replace("&3", "" + ChatColor.DARK_AQUA);
		newString = newString.replace("&4", "" + ChatColor.DARK_RED);
		newString = newString.replace("&5", "" + ChatColor.DARK_PURPLE);
		newString = newString.replace("&6", "" + ChatColor.GOLD);
		newString = newString.replace("&7", "" + ChatColor.GRAY);
		newString = newString.replace("&8", "" + ChatColor.DARK_GRAY);
		newString = newString.replace("&9", "" + ChatColor.BLUE);
		newString = newString.replace("&a", "" + ChatColor.GREEN);
		newString = newString.replace("&b", "" + ChatColor.AQUA);
		newString = newString.replace("&c", "" + ChatColor.RED);
		newString = newString.replace("&d", "" + ChatColor.LIGHT_PURPLE);
		newString = newString.replace("&e", "" + ChatColor.YELLOW);
		newString = newString.replace("&f", "" + ChatColor.WHITE);
		
		newString = newString.replace("&k", "" + ChatColor.MAGIC);
		newString = newString.replace("&l", "" + ChatColor.BOLD);
		newString = newString.replace("&m", "" + ChatColor.STRIKETHROUGH);
		newString = newString.replace("&n", "" + ChatColor.UNDERLINE);
		newString = newString.replace("&o", "" + ChatColor.ITALIC);
		newString = newString.replace("&p", "" + ChatColor.RESET);
		
		
		return newString;
	}

}
