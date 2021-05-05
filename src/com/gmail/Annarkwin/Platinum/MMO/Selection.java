package com.gmail.Annarkwin.Platinum.MMO;

import org.bukkit.Location;

import com.gmail.Annarkwin.Platinum.API.Cube;

public class Selection
{

	private Location initial = null;
	private Cube selection = null;

	public Selection()
	{

	}

	// Returns true if this addition completes the selection
	public boolean addPoint( Location loc )
	{

		if (isInitialized() && initial.getWorld() == loc.getWorld())
		{

			selection = new Cube(initial.clone(), loc);
			initial = null;
			return true;

		}
		else
		{

			initial = loc;
			selection = null;
			return false;

		}

	}

	public Cube getArea()
	{

		return selection;

	}

	public boolean isInitialized()
	{

		return (initial != null);

	}

	public boolean isComplete()
	{

		return (selection != null);

	}

	public void clear()
	{

		initial = null;
		selection = null;

	}

}
