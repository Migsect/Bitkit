package me.migsect.Bitkit.MenuGUI.Options;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.migsect.Bitkit.Player.BitkitPlayer;

public class OptionToggle extends Option
{
	private String option;
	
	// Switch Display Modifiers (these replace the old text of the switched item.\
	//   switch is also know as the false state.
	private String switch_item_name = "Default Switch Title";
	List<String> switch_lore_text = new ArrayList<String>();
	private Material switch_material = Material.DIRT;
	private short switch_material_data = 0;
	
	// OptionToggle() constructor will take an additional string argument
	//   this will direct to the player's toggle hashmap and toggle that.
	public OptionToggle(String option)
	{
		this.option = option;
	}
	
	public void setSwitchName(String name){this.switch_item_name = name;}
	public void setSwitchLoreText(List<String> lore_text){this.switch_lore_text = lore_text;}
	public void setSwitchMaterial(Material material){this.switch_material = material;}
	public void setSwitchMaterialData(short data){this.switch_material_data = data;}
	
	public List<String> getSwitchLoreText(){return switch_lore_text;}
	
	// setSwitchMaterial() will set the material that is switched whenever the click is made.
	
	@Override
	public void onClick(BitkitPlayer player)
	{
		player.toggleToggle(option);
	}
	
	// this will now have different items depending on the state of the player's toggle.
	@Override
	public ItemStack generateItemStack()
	{
		boolean toggle = menu.getPlayer().getToggle(option);
		if(toggle)
		{
			ItemStack item = new ItemStack(material, 1, material_data);
			item.getItemMeta().setLore(lore_text);
			item.getItemMeta().setDisplayName(item_name);
			return item;
		}
		else
		{
			ItemStack item = new ItemStack(switch_material, 1, switch_material_data);
			item.getItemMeta().setLore(switch_lore_text);
			item.getItemMeta().setDisplayName(switch_item_name);
			return item;
		}
	}

}
