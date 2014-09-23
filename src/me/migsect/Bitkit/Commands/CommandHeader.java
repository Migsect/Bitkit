package me.migsect.Bitkit.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.migsect.Bitkit.Bitkit;

public class CommandHeader extends BaseCommand
{
	// CommandHeader will list the commands available in the plugin.
	public CommandHeader(Bitkit plugin)
	{
		super(plugin);
		
		this.tag = "";
		this.info = "This is the command header for Bitkit";
		this.moreInfo.add("Not much else to say.");
		this.perm = "bitkit.command";
		
		this.senderTypes.add(SenderType.PLAYER);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args)
	{
		Player player = (Player) sender;
		player.sendMessage(ChatColor.GOLD + "Commands for Bitkit: ");
		for(int i = 0; i < plugin.commandHandler.getCommands().size(); i++)
		{
			if(plugin.commandHandler.getCommands().get(i).isAllowed(sender))
			{
				BaseCommand command = plugin.commandHandler.getCommands().get(i);
				player.sendMessage(ChatColor.AQUA + "  /. " + command.getTag() + ChatColor.YELLOW + " : " + command.getInfo());
			}
		}
		
		return true;
	}

}
