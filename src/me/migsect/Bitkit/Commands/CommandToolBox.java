package me.migsect.Bitkit.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Menu;
import me.migsect.Bitkit.MenuGUI.Options.OptionItem;
import me.migsect.Bitkit.Player.BitkitPlayer;

public class CommandToolBox extends BaseCommand
{

	public CommandToolBox(Bitkit plugin)
	{
		super(plugin);
		
		this.tag = "tb";
		this.info = "";
		this.moreInfo.add("TODO");
		
		this.senderTypes.add(SenderType.PLAYER);
		this.perm = "bitkit.tools";
		
		this.minArgs = 0;
		this.maxArgs = 0;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args)
	{
		BitkitPlayer player = plugin.playerHandler.getPlayer((Player) sender);
		Menu toolMenu = new Menu(player, plugin, "Tool Box");
		
		// Replacer Tool Option
		ItemStack item1 = new ItemStack(Material.GOLD_SPADE);
		ItemMeta im1 = item1.getItemMeta();
		im1.setDisplayName(ChatColor.GOLD + "Replace Block Tool");
		
		
		List<String> lore1 = new ArrayList<String>();
		lore1.add(ChatColor.YELLOW + "Tool: Replacer");
		lore1.add(ChatColor.YELLOW + "Block: " + Material.AIR.toString());
		lore1.add(ChatColor.YELLOW + "Reach: 20");
		im1.setLore(lore1);
		
		item1.setItemMeta(im1);
		
		OptionItem option1= new OptionItem(item1);
		option1.setMaterial(Material.GOLD_SPADE);
		option1.setName(ChatColor.GOLD + "Replacer Tool");
		option1.getLoreText().add(ChatColor.WHITE + "Used to replace groups of blocks.");
		toolMenu.addOption(option1, 0);
		
		// Next item
		ItemStack item2 = new ItemStack(Material.GOLD_SPADE);
		ItemMeta im2 = item2.getItemMeta();
		im2.setDisplayName(ChatColor.GOLD + "Replace Block Tool");
		
		
		List<String> lore2 = new ArrayList<String>();
		lore2.add(ChatColor.YELLOW + "Tool: Replacer");
		lore2.add(ChatColor.YELLOW + "Block: " + Material.AIR.toString());
		lore2.add(ChatColor.YELLOW + "Reach: 20");
		lore2.add(ChatColor.YELLOW + "Surface: True");
		im2.setLore(lore2);
		
		item2.setItemMeta(im2);
		
		OptionItem option2 = new OptionItem(item2);
		option2.setMaterial(Material.GOLD_SPADE);
		option2.setName(ChatColor.GOLD + "Replacer Tool");
		option2.getLoreText().add(ChatColor.WHITE + "Used to replace groups of blocks.");
		option2.getLoreText().add(ChatColor.WHITE + "[SURFACE VERSION]");
		toolMenu.addOption(option2, 9);
		
		// Next Item
		ItemStack item3 = new ItemStack(Material.GOLD_SPADE);
		ItemMeta im3 = item3.getItemMeta();
		im3.setDisplayName(ChatColor.GOLD + "Replace Block Tool");
		
		
		List<String> lore3 = new ArrayList<String>();
		lore3.add(ChatColor.YELLOW + "Tool: Replacer");
		lore3.add(ChatColor.YELLOW + "Block: " + Material.AIR.toString());
		lore3.add(ChatColor.YELLOW + "Reach: 20");
		lore3.add(ChatColor.YELLOW + "Corners: True");
		im3.setLore(lore3);
		
		item3.setItemMeta(im3);
		
		OptionItem option3 = new OptionItem(item3);
		option3.setMaterial(Material.GOLD_SPADE);
		option3.setName(ChatColor.GOLD + "Replacer Tool");
		option3.getLoreText().add(ChatColor.WHITE + "Used to replace groups of blocks.");
		option3.getLoreText().add(ChatColor.WHITE + "[CORNERS VERSION]");
		toolMenu.addOption(option3, 18);
		
		
		toolMenu.open();
		return true;
	}

}
