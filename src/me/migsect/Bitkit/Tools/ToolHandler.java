package me.migsect.Bitkit.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.migsect.Bitkit.Bitkit;

public class ToolHandler
{
	
	Bitkit plugin;
	// A Tool is anything that can be used to improve building production.  As such this
	//   definition is quite wide-scoped and that is for a good reason, this is to
	//   allow use to make abstract tools.
	// Tool handler will handle any inflow of tool actions.  There will be a few different actions:
	//  - Right Click
	//  - Left Click
	//  - Shift Right Click
	//  - Shift Left Click
	//  > Block Break (Only if the tool allows.)
	HashMap<String, Tool> tools = new HashMap<String, Tool>();
	
	
	public ToolHandler(Bitkit plugin)
	{
		this.plugin = plugin;
	}
	
	public void registerTool(Tool tool)
	{
			tools.put(tool.getName(), tool);
	}
	
	public Tool getTool(String name)
	{
		return tools.get(name);
	}
	
	public List<Tool> getToolTypes()
	{
		List<Tool> newList = new ArrayList<Tool>();
		newList.addAll(tools.values());
		return newList;
	}
}
