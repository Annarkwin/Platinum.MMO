package com.gmail.Annarkwin.Platinum.MMO;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import com.gmail.Annarkwin.Platinum.API.Cube;

@SerializableAs("Portal")
public class Portal implements ConfigurationSerializable
{

	private Cube area = null; // Region area
	private String name = "Broken Portal"; // Region name
	private String warpname = "Broken Warp"; // Warp id
	private boolean enabled = false;
	private String hook = "";

	public Portal( Cube area, String name, Warp warp )
	{

		this.area = area;
		this.name = name;
		this.warpname = warp.getName();

	}

	public Portal( Map<String, Object> o )
	{

		// Get fields of object and retrieve them from map
		for (Field f : this.getClass().getDeclaredFields())
		{

			try
			{

				if (o.get(f.getName()) != null)
					f.set(this, o.get(f.getName()));

			}
			catch (Exception e)
			{

				// Default
			}

		}

	}

	@Override
	public Map<String, Object> serialize()
	{

		// Get fields of object and map them with value
		HashMap<String, Object> s = new HashMap<String, Object>();

		for (Field f : this.getClass().getDeclaredFields())
		{

			try
			{

				s.put(f.getName(), f.get(this));

			}
			catch (Exception e)
			{

				e.printStackTrace();

			}

		}

		return s;

	}

	public Cube getArea()
	{

		return this.area;

	}

	public Warp getWarp()
	{

		return MMO.warp_manager.getWarp(warpname);

	}

	public Location getWarpLocation()
	{

		return MMO.warp_manager.getWarp(warpname).getLocation();

	}

	public void setWarpLocation( Warp warp )
	{

		warp.getName();

	}

	public String getName()
	{

		return name;

	}

	public boolean isEnabled()
	{

		return enabled;

	}

	public boolean toggleEnabled()
	{

		return enabled = !enabled;

	}

	public String getHook()
	{

		return hook;

	}

	public void setHook( String h )
	{

		hook = h;

	}

}