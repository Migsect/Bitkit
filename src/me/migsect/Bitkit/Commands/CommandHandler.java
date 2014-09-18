package me.migsect.Bitkit.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.migsect.Bitkit.Bitkit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor
{
	@SuppressWarnings("unused")
	private Bitkit plugin;
	private static HashMap<String, BaseCommand> commands = new HashMap<String, BaseCommand>();
	
	public CommandHandler(Bitkit plugin)
	{
		this.plugin = plugin;
	}
	
	public void register(BaseCommand command)
	{
		commands.put(command.getTag(),command);
	}
	
	public boolean exists(String name)
	{
		return commands.containsKey(name);
	}
	public BaseCommand getExecutor(String name)
	{
		return commands.get(name);
	}
	public List<BaseCommand> getCommands()
	{
		List<BaseCommand> list = new ArrayList<BaseCommand>();
		list.addAll(commands.values());
		return list;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args)
	{
		// To simplify the process, this will send each command call through a test to see if that command is viable.  Using the command interface we can easily
		// Check to see if these are all correct:
		//  - If the command exists (which will be args[0])
		//  - If the command accepts the user type
		//  - If the command accepts the amount of arguments (args[1] through args[x]
		//  - If the command user has permission. (Only if the user is a player)
		if(args.length == 0) 
		{
			getExecutor("").onCommand(sender, cmd, commandLabel, args);
			return true;
		}
		if(!isCommand(sender, args[0])) return true;
		if(!isUsable(sender, args[0], args.length - 1)) return true;
		if(!isAllowed(sender, args[0])) return true;
		getExecutor(args[0]).onCommand(sender, cmd, commandLabel, args);
		return true;
	}
	
	// ================================>
	// Helper Methods below this point
	// ================================>
	private boolean isCommand(CommandSender sender, String tag)
	{
		if (!exists(tag))
		{
			String errorMsg = ChatColor.RED + "That Command does not exist.";
			sender.sendMessage(errorMsg);
			return false;
		}
		return true;
	}
	private boolean isUsable(CommandSender sender, String tag, int argsLength)
	{
		if(!getExecutor(tag).isAble(sender))
		{
			String errorMsg = ChatColor.RED + "Sorry, you aren't the correct type of sender.";
			sender.sendMessage(errorMsg);
			return false;
		}
		if(!getExecutor(tag).isUsable(argsLength))
		{
			String errorMsg = ChatColor.RED + "You may have used the command wrong.";
			sender.sendMessage(errorMsg);
			return false;
		}
		return true;
	}
	private boolean isAllowed(CommandSender sender, String tag)
	{
		if(!getExecutor(tag).isAllowed(sender))
		{
			String errorMsg = ChatColor.RED + "You are not alowed to use this command.";
			sender.sendMessage(errorMsg);
			return false;
		}
		return true;
	}
	
}
