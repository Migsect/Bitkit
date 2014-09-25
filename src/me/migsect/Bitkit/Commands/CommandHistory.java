package me.migsect.Bitkit.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.Player.BitkitPlayer;

public class CommandHistory extends BaseCommand
{

	public CommandHistory(Bitkit plugin)
	{
		super(plugin);
		
		this.tag = "history";
		this.info = "Lists recent Block changes you have made.";
		this.moreInfo.add("TODO: Put stuff here.");
		
		this.senderTypes.add(SenderType.PLAYER);
		this.perm = "bitkit.history";
		
		this.minArgs = 0;
		this.maxArgs = 1;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args)
	{
		BitkitPlayer player = plugin.playerHandler.getPlayer((Player) sender);
		if(args.length == 0)
		{
			player.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.UNDERLINE + "Your Action History: ");
		
		}
		if(args.length == 1)
		{
			
			
		}
		return true;
	}

}
