package me.migsect.Bitkit.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.migsect.Bitkit.Bitkit;

public abstract class BaseCommand
{
	protected Bitkit plugin;
	
	protected String tag = "tag";
	protected String info = "Info...";
	protected List<String> moreInfo = new ArrayList<String>();
	
	protected List<SenderType> senderTypes = new ArrayList<SenderType>();
	protected String perm = "bitkit.node";
	
	protected int minArgs = 0;
	protected int maxArgs = 0;
	
	public BaseCommand(Bitkit plugin)
	{
		this.plugin = plugin;
	}
	
	final protected void setTag(String tag){this.tag = tag;}
	final protected void setInfo(String info){this.info = info;}
	final protected void addMoreInfo(String info){this.moreInfo.add(info);}
	final protected void setPerm(String perm){this.perm = perm;}
	final protected void setMinArgs(int minArgs){this.minArgs = minArgs;}
	final protected void setMaxArgs(int maxArgs){this.maxArgs = maxArgs;}
	final protected void setSenderTypes(List<SenderType>senderTypes){this.senderTypes = senderTypes;}
	final protected void addSenderType(SenderType senderType){this.senderTypes.add(senderType);}
	
	final public String getTag(){return tag;}
	final public String getInfo(){return info;}
	final public List<String> getMoreInfo(){return moreInfo;}
	final public String getPerm(){return perm;}
	final public List<SenderType> getSenderTypes(){return senderTypes;}
	
	final public boolean isUsable(int argsLength)
	{
		if ((argsLength < minArgs) || (argsLength > maxArgs)) return false;
		return true;
	}
	final public boolean isAble(CommandSender sender)
	{
		SenderType type = SenderType.getSenderType(sender);
		if(senderTypes.contains(type)) return true;
		return false;
	}
	final public boolean isAllowed(CommandSender sender)
	{
		if(sender instanceof Player) return ((Player) sender).hasPermission(perm);
		return true;
	}
	
	abstract public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args);
}
