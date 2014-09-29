package me.migsect.Bitkit.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.MenuGUI.Menu;
import me.migsect.Bitkit.MenuGUI.Options.Option;
import me.migsect.Bitkit.Player.BitkitPlayer;
import me.migsect.Bitkit.Tools.Tool;

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
		
		// We're going to let each of the tools handle what types of their tools
		//   they want in the toolbox.  Tools should also be manageable through
		//   their own means.
		List<Tool> tools = plugin.toolHandler.getToolTypes();
		int option_counter = 0;
		for(int i = 0; i < tools.size(); i++)
		{
			List<Option> options = tools.get(i).makeToolBoxOptions();
			for(int j = 0; j < options.size(); j++)
			{
				toolMenu.addOption(options.get(j), option_counter);
				option_counter++;
			}
		}
		
		toolMenu.open();
		return true;
	}

}
