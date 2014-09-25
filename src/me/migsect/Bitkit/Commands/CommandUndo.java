package me.migsect.Bitkit.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.Player.BitkitPlayer;

public class CommandUndo extends BaseCommand
{

	public CommandUndo(Bitkit plugin)
	{
		super(plugin);
		this.tag = "undo";
		this.info = "Undos the last block changes you have made.";
		this.moreInfo.add("TODO: Put more stuff for this.  Undo is complex.");
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
		if(args.length == 1)
		{
			player.undoActions(1);
			return true;
		}
		else if(args.length == 2)
		{
			int num = 0;
			try
			{
				num = Integer.parseInt(args[1]);
			}
			catch (NumberFormatException e)
			{
				// TODO: Add an actual warning to the player.
				plugin.logger.warning("Player needed to enter a number for undos.");
				return true;
			}
			player.undoActions(num);
			
			return true;
		}
		return true;
	}

}
