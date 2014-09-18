package me.migsect.Bitkit.Listeners;

import me.migsect.Bitkit.Bitkit;
import me.migsect.Bitkit.Player.BitkitPlayer;
import me.migsect.Bitkit.Tools.ToolData;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
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
		if(event.getAction() == Action.RIGHT_CLICK_AIR)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftRightClickAir(event.getClickedBlock().getLocation(), toolData);
			else toolData.getToolType().rightClickAir(event.getClickedBlock().getLocation(), toolData);
			
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftRightClickBlock(event.getClickedBlock().getLocation(), toolData);
			else toolData.getToolType().rightClickBlock(event.getClickedBlock().getLocation(), toolData);
			
			
		}
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftRightClick(event.getClickedBlock().getLocation(), toolData);
			else toolData.getToolType().rightClick(event.getClickedBlock().getLocation(), toolData);
			
		}
		
		if(event.getAction() == Action.LEFT_CLICK_AIR)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftLeftClickAir(event.getClickedBlock().getLocation(), toolData);
			else toolData.getToolType().leftClickAir(event.getClickedBlock().getLocation(), toolData);
			
			
		}
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftLeftClickBlock(event.getClickedBlock().getLocation(), toolData);
			else toolData.getToolType().leftClickBlock(event.getClickedBlock().getLocation(), toolData);
			
			
			
		}
		if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR)
		{
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftLeftClick(event.getClickedBlock().getLocation(), toolData);
			else toolData.getToolType().leftClick(event.getClickedBlock().getLocation(), toolData);
			
			
		}
	}
	
	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent event)
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
			
			if(!toolData.getToolType().canBreak())
			{
				event.setCancelled(true);
				return;
			}
			if(event.getPlayer().isSneaking()) toolData.getToolType().shiftBlockBreak(event.getBlock().getLocation(), toolData);
			else toolData.getToolType().blockBreak(event.getBlock().getLocation(), toolData);
			
	}
}
