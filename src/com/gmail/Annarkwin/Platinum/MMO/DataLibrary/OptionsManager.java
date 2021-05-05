package com.gmail.Annarkwin.Platinum.MMO.DataLibrary;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.FileStorage;

public class OptionsManager
{

	private FileStorage file;
	private String section_portal_def = "PortalDefaults";
	private String section_zone_def = "ZoneDefaults";
	private String section_quarry_def = "QuarryDefaults";
	private String section_selection_def = "SelectionDefaults";
	private String section_user_def = "UserDefaults";

	HashMap<String, Object> portal_def = new HashMap<String, Object>();
	HashMap<String, Object> zone_def = new HashMap<String, Object>();
	HashMap<String, Object> quarry_def = new HashMap<String, Object>();
	HashMap<String, Object> selection_def = new HashMap<String, Object>();
	HashMap<String, Object> user_def = new HashMap<String, Object>();

	public OptionsManager( JavaPlugin plugin, String filename )
	{

		file = new FileStorage(plugin, filename);
		importData();

	}

	/*
	 * Portal Default Sets/Gets
	 */

	/*
	 * Zone Default Sets/Gets
	 */

	/*
	 * Quarry Default Sets/Gets
	 */

	/*
	 * Selection Default Sets/Gets
	 */

	/*
	 * User Default Sets/Gets
	 */

	@SuppressWarnings("unchecked")
	private void importData()
	{

		Object current_section;

		if ((current_section = file.getData().get(section_portal_def)) != null)
		{

			portal_def = (HashMap<String, Object>) current_section;

		}
		else
			portal_def = new HashMap<String, Object>();

		if ((current_section = file.getData().get(section_zone_def)) != null)
		{

			zone_def = (HashMap<String, Object>) current_section;

		}
		else
			zone_def = new HashMap<String, Object>();

		if ((current_section = file.getData().get(section_quarry_def)) != null)
		{

			quarry_def = (HashMap<String, Object>) current_section;

		}
		else
			quarry_def = new HashMap<String, Object>();

		if ((current_section = file.getData().get(section_selection_def)) != null)
		{

			selection_def = (HashMap<String, Object>) current_section;

		}
		else
			selection_def = new HashMap<String, Object>();

		if ((current_section = file.getData().get(section_user_def)) != null)
		{

			user_def = (HashMap<String, Object>) current_section;

		}
		else
			user_def = new HashMap<String, Object>();

	}

	public void saveData()
	{

		file.getData().set(section_portal_def, portal_def);
		file.getData().set(section_zone_def, zone_def);
		file.getData().set(section_quarry_def, quarry_def);
		file.getData().set(section_selection_def, selection_def);
		file.getData().set(section_user_def, user_def);
		file.saveToDisk();

	}

}
