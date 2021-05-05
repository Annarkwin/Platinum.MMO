package com.gmail.Annarkwin.Platinum.MMO.DataLibrary;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.Cube;
import com.gmail.Annarkwin.Platinum.API.FileStorage;
import com.gmail.Annarkwin.Platinum.MMO.Portal;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class PortalManager
{

	private FileStorage file;
	private String section_portals = "portals";

	private HashSet<Portal> portals;

	public PortalManager( JavaPlugin plugin, String filename )
	{

		file = new FileStorage(plugin, filename);
		importData();

	}

	public Collection<Portal> getPortals()
	{

		return portals;

	}

	public boolean isOverlappingPortal( Cube c )
	{

		for (Portal p : getPortals())
		{

			if (p.getArea().intersectsCube(c))
				return true;

		}

		return false;

	}

	public Portal getPortal( String name )
	{

		for (Portal p : portals)
		{

			if (p.getName().equalsIgnoreCase(name))
				return p;

		}

		return null;

	}

	public Portal getPortal( Location loc )
	{

		for (Portal p : portals)
		{

			if (p.getArea().containsPoint(loc))
				return p;

		}

		return null;

	}

	public HashSet<Portal> getPortalsToWarp( Warp w )
	{

		HashSet<Portal> list = new HashSet<Portal>();
		for (Portal p : portals)
			if (p.getWarp().equals(w))
				list.add(p);

		return list;

	}

	public void addPortal( Portal p )
	{

		if (portals == null)
			portals = new HashSet<Portal>();
		portals.add(p);

	}

	public void removePortal( String name )
	{

		for (Portal p : portals)
		{

			if (p.getName().equalsIgnoreCase(name))
			{

				portals.remove(p);
				return;

			}

		}

	}

	public void removePortal( Portal p )
	{

		portals.remove(p);

	}

	@SuppressWarnings("unchecked")
	private void importData()
	{

		if (file.getData().get(section_portals) != null)
		{

			portals = (HashSet<Portal>) file.getData().get(section_portals);

		}
		else
			portals = new HashSet<Portal>();

	}

	public void saveData()
	{

		file.getData().set(section_portals, portals);
		file.saveToDisk();

	}

}
