package com.gmail.Annarkwin.Platinum.MMO.DataLibrary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.Cube;
import com.gmail.Annarkwin.Platinum.API.FileStorage;
import com.gmail.Annarkwin.Platinum.MMO.Region;
import com.gmail.Annarkwin.Platinum.MMO.Exceptions.RegionIntersectException;

public class RegionManager
{

	private FileStorage file;
	private String section_regions = "regions";

	private HashSet<Region> regions;

	public RegionManager( JavaPlugin plugin, String filename )
	{

		file = new FileStorage(plugin, filename);
		importData();

	}

	public Region addRegion( Cube c, Player p ) throws RegionIntersectException
	{

		if (regions == null)
			regions = new HashSet<Region>();

		if (isIntersectingRegion(c))
		{

			throw new RegionIntersectException();

		}
		else
		{

			Region r = new Region(c, p);
			regions.add(r);
			return r;

		}

	}

	public boolean removeRegion( Region r )
	{

		return regions.remove(r);

	}

	// Return the highest region at location
	public Region getRegion( Location location )
	{

		for (Region r : regions)
		{

			if (r.getArea().containsPoint(location))
				return r;

		}

		return null;

	}

	// Return all regions
	public HashSet<Region> getRegions()
	{

		return regions;

	}

	// Return all regions owned by given player
	public ArrayList<Region> getRegions( UUID player )
	{

		ArrayList<Region> list = new ArrayList<Region>();

		for (Region r : regions)
		{

			if (r.getOwner().equals(player))
				list.add(r);

		}

		list.sort(null);
		return list;

	}

	public boolean isIntersectingRegion( Cube c )
	{

		for (Region r : getRegions())
		{

			if (r.getArea().intersectsCube(c))
				return true;

		}

		return false;

	}

	@SuppressWarnings("unchecked")
	private void importData()
	{

		if (file.getData().get(section_regions) != null)
		{

			regions = (HashSet<Region>) file.getData().get(section_regions);

		}
		else
			regions = new HashSet<Region>();

	}

	// Save file data
	public void saveData()
	{

		file.getData().set(section_regions, regions);
		file.saveToDisk();

	}

}
