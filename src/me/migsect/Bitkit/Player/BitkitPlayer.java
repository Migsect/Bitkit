package me.migsect.Bitkit.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class BitkitPlayer
{
	private Player player;
	
	private List<BlockAction> undo_list = new ArrayList<BlockAction>();
	private List<BlockAction> redo_list = new ArrayList<BlockAction>();
	int max_redos = 100; // TODO: make these configurable.
	int max_undos = 100;
	
	private HashMap<String, Boolean> toggled_options = new HashMap<String, Boolean>();
	
	// BitKitPlayer() constructor takes an input of Player.  This is a basic extension of a player.
	public BitkitPlayer(Player player)
	{
		this.player = player;
	}
	
	// getPlayer() returns the player that this class wraps.
	public Player getPlayer()
	{
		return player;
	}
	
	// getToggle() returns a bool if the player can do that toggled_opt.
	//   if they don't have that currently set then it returns false;
	public boolean getToggle(String toggled_opt)
	{
		if(toggled_options.containsKey(toggled_opt)) return toggled_options.get(toggled_opt);
		return false;
	}
	
	// setToggle() will hard set a toggled option to a boolean value.
	public void setToggle(String toggled_opt, boolean bool)
	{
		toggled_options.put(toggled_opt, bool);
	}
	// toggleToggle() will toggle the option.  If there is currently no option it will set it to true.
	public void toggleToggle(String toggled_opt)
	{
		if(!toggled_options.containsKey(toggled_opt))
		{
			toggled_options.put(toggled_opt, true);
			return;
		}
		toggled_options.put(toggled_opt, !toggled_options.get(toggled_opt));
		
	}
	
	public void addUndoAction(BlockAction action)
	{
		if(max_undos == undo_list.size()) undo_list.remove(0);
		undo_list.add(action);
	}
	public void addRedoAction(BlockAction action)
	{
		if(max_redos == redo_list.size()) redo_list.remove(0);
		redo_list.add(action);
	}
	
	// []Actions() will start on the last put in item and execute down the stack
	//   until the end.   Any redo or undo will be placed in the other list.
	public void undoActions(int num)
	{
		// We go backwards because of redos being added.
		for(int i = undo_list.size(); i >= undo_list.size() - num; i--)
		{
			BlockAction new_redo = undo_list.get(i).doBlocks();
			redo_list.add(new_redo);
		}
	}
	public void redoActions(int num)
	{
		for(int i = redo_list.size(); i >= redo_list.size() - num; i--)
		{
			BlockAction new_undo = redo_list.get(i).doBlocks();
			redo_list.add(new_undo);
		}
	}
	
	// purge[]() will clear the lists of their actions.  This can be used whenever
	//   a player wishes to clean their history.
	public void purgeUndoActions()
	{
		undo_list.clear();
	}
	public void purgeRedoActions()
	{
		redo_list.clear();
	}
}
