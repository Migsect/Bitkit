package me.migsect.Bitkit.Player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

public class BlockAction
{
	// BlockActions will represent a set of blocks that are changed.
	//   BlockActions can range from no blocks to a large group of blocks.
	//   It will make life easier by handling undos and redos.
	List<BlockState> states = new ArrayList<BlockState>();
	BitkitPlayer acting_player;
	
	public BlockAction(BitkitPlayer player)
	{
		this.acting_player = player;
	}
	
	// addBlock() will add a block state to the list.
	public void addBlock(BlockState state)
	{
		this.states.add(state);
	}
	
	// getBlockCount() will return an int of how many blocks this class is storing.
	public int getBlockCount()
	{
		return states.size();
	}
	
	// doBlocks() will update all blocks in the block actions.
	//   doBlocks() will also return a new BlockAction that can be used to be placed
	//   in another storage vector due to doBlocks will be a block action.
	public BlockAction doBlocks()
	{
		BlockAction changes = new BlockAction(acting_player);
		for(int i = 0; i < states.size(); i++)
		{
			Location loc = states.get(i).getLocation();
			BlockState old = loc.getBlock().getState();
			changes.addBlock(old);
			
			states.get(i).update(true);
		}
		
		return changes;
	}
	
}
