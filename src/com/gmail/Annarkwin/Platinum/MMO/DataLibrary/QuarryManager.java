package com.gmail.Annarkwin.Platinum.MMO.DataLibrary;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.Cube;
import com.gmail.Annarkwin.Platinum.API.FileStorage;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryManager
{

	private FileStorage file;
	private String section_quarries = "quarries";

	private HashSet<Quarry> quarries;

	public QuarryManager( JavaPlugin plugin, String filename )
	{

		file = new FileStorage(plugin, filename);
		importData();

	}

	public Collection<Quarry> getQuarries()
	{

		return quarries;

	}

	public boolean isOverlappingQuarry( Cube c )
	{

		for (Quarry q : getQuarries())
		{

			if (q.getArea().intersectsCube(c))
				return true;

		}

		return false;

	}

	public Quarry getQuarry( String name )
	{

		for (Quarry q : quarries)
		{

			if (q.getName().equalsIgnoreCase(name))
				return q;

		}

		return null;

	}

	public Quarry getQuarry( Location loc )
	{

		for (Quarry q : quarries)
		{

			if (q.getArea().containsPoint(loc))
				return q;

		}

		return null;

	}

	public void addQuarry( Quarry q )
	{

		if (quarries == null)
			quarries = new HashSet<Quarry>();
		quarries.add(q);

	}

	public void removeQuarry( String name )
	{

		for (Quarry q : quarries)
		{

			if (q.getName().equalsIgnoreCase(name))
			{

				quarries.remove(q);
				return;

			}

		}

	}

	public void removeQuarry( Quarry q )
	{

		quarries.remove(q);

	}

	@SuppressWarnings("unchecked")
	private void importData()
	{

		if (file.getData().get(section_quarries) != null)
		{

			quarries = (HashSet<Quarry>) file.getData().get(section_quarries);

		}
		else
			quarries = new HashSet<Quarry>();

	}

	public void saveData()
	{

		file.getData().set(section_quarries, quarries);
		file.saveToDisk();

	}

}
