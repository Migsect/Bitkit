
package me.migsect.Bitkit.Commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.migsect.Bitkit.BKitHelper;
import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.Player.BitkitPlayer;
import me.migsect.Bitkit.Player.BlockAction;

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
		if(args.length == 1)
		{
			player.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.UNDERLINE + "Your Action History:                      ");
			player.getPlayer().sendMessage("");
			
			List<BlockAction> undo_list = player.getUndoActions();
			
			//for(int i = undo_list.size() - 1; i >= 0; i--)
			for(int i = 0; i < undo_list.size(); i++)
			{
				String block_count = "" + undo_list.get(i).getBlockCount();
				
				Date time_at = undo_list.get(i).getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				String time = sdf.format(time_at);
				String time_indent = BKitHelper.spaceString(4 - time.length());
				
				String index = "" + (undo_list.size() - i);
				String index_lead = BKitHelper.charString(3-index.length(), '0');
				
				player.sendFMessage("&e " + index_lead + index + " | " + time + time_indent + "&f : " + block_count + " Block Changes");
				if((undo_list.size() - i) % 5 == 0)
				{
					player.sendFMessage("");
				}
			}
		}
		if(args.length == 2)
		{
			
			
		}
		return true;
	}

}
