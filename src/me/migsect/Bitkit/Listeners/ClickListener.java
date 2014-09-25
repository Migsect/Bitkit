package me.migsect.Bitkit.Listeners;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.Player.BitkitPlayer;
import me.migsect.Bitkit.Player.BlockAction;
import me.migsect.Bitkit.Tools.ToolData;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener
{
	
	Bitkit plugin;
	
	public ClickListener(Bitkit plugin)
	{
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		// A player with no item in hand will be treated as if they have no tool in hand.  Empty hand is RESERVED
		//   as a no-tool action.
		if(event.getPlayer().getItemInHand().getType() == Material.AIR || event.getPlayer().getItemInHand() == null) return;
		// Tools will only be used IF the player is in creative.  No survival mode tools, this mod is only used for creative mode
		//   until further features are added for survival.
		if(event.getPlayer().getGameMode() != GameMode.CREATIVE) return;
		
		ItemStack item = event.getPlayer().getItemInHand();
		BitkitPlayer player = plugin.playerHandler.getPlayer(event.getPlayer());
		ToolData toolData = new ToolData(item, plugin.toolHandler, player);
		
		// we need to make sure the tool has data.
		if(!toolData.hasData("tool")) return;
		if(event.getAction() == Action.RIGHT_CLICK_AIR)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftRightClickAir(event.getPlayer().getLocation(), toolData);
			else toolData.getToolType().rightClickAir(event.getPlayer().getLocation(), toolData);
			
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftRightClickBlock(event.getClickedBlock(), toolData);
			else toolData.getToolType().rightClickBlock(event.getClickedBlock(), toolData);
			
			
		}
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftRightClick(event.getPlayer().getLocation(), toolData);
			else toolData.getToolType().rightClick(event.getPlayer().getLocation(), toolData);
			
		}
		
		if(event.getAction() == Action.LEFT_CLICK_AIR)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftLeftClickAir(event.getPlayer().getLocation(), toolData);
			else toolData.getToolType().leftClickAir(event.getPlayer().getLocation(), toolData);
			
			
		}
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftLeftClickBlock(event.getClickedBlock(), toolData);
			else toolData.getToolType().leftClickBlock(event.getClickedBlock(), toolData);
			
			
			
		}
		if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftLeftClick(event.getPlayer().getLocation(), toolData);
			else toolData.getToolType().leftClick(event.getPlayer().getLocation(), toolData);
			
			
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerBlockBreak(BlockBreakEvent event)
	{
			if(event.isCancelled()) return;
			// A player with no item in hand will be treated as if they have no tool in hand.  Empty hand is RESERVED
			//   as a no-tool action.
			if(event.getPlayer().getItemInHand().getType() == Material.AIR || event.getPlayer().getItemInHand() == null) return;
			// Tools will only be used IF the player is in creative.  No survival mode tools, this mod is only used for creative mode
			//   until further features are added for survival.
			if(event.getPlayer().getGameMode() != GameMode.CREATIVE) return;
			
			ItemStack item = event.getPlayer().getItemInHand();
			BitkitPlayer player = plugin.playerHandler.getPlayer(event.getPlayer());
			ToolData toolData = new ToolData(item, plugin.toolHandler, player);

			// we need to make sure the tool has data.
			if(!toolData.hasData("tool")) return;
			
			if(!toolData.getToolType().canBreak())
			{
				event.setCancelled(true);
			}
			else
			{
				BlockAction new_action = new BlockAction(player);
				new_action.addBlock(event.getBlock().getState());
			}
			if(event.isCancelled() && toolData.getToolType().canShiftBreak() && event.getPlayer().isSneaking())
			{
				event.setCancelled(false);
				BlockAction new_action = new BlockAction(player);
				new_action.addBlock(event.getBlock().getState());
			}
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftBlockBreak(event.getBlock(), toolData);
			else toolData.getToolType().blockBreak(event.getBlock(), toolData);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(event.isCancelled()) return;
		BitkitPlayer player = plugin.playerHandler.getPlayer(event.getPlayer());
		
		BlockAction new_action = new BlockAction(player);
		new_action.addBlock(event.getBlock().getState());
		player.addUndoAction(new_action);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if(event.isCancelled()) return;
		BitkitPlayer player = plugin.playerHandler.getPlayer(event.getPlayer());
		
		BlockAction new_action = new BlockAction(player);
		new_action.addBlock(event.getBlockReplacedState());
		player.addUndoAction(new_action);
	}
}
