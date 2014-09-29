package me.migsect.Bitkit.Commands;

import java.util.Date;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.Player.BitkitPlayer;
import me.migsect.Bitkit.Player.BlockAction;

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
			long second_tol = 2;
			List<BlockAction> undos = player.getUndoActions();
			if(undos.isEmpty()) return true;
			Date last_time = undos.get(undos.size()-1).getTime();
			Date next_time = undos.get(undos.size()-1).getTime();
			int counter = 0;
			while((last_time.getTime() - next_time.getTime())/1000 < second_tol)
			{
				counter++;
				if(undos.size() <= counter) break;
				last_time = next_time;
				next_time = undos.get(undos.size() - 1 - counter).getTime();
				// Bukkit.getLogger().info("" + next_time.getTime() + " | " + last_time.getTime() + " | " + (next_time.getTime() - last_time.getTime()));
			}
			player.undoActions(counter);
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
