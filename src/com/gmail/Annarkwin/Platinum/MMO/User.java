package com.gmail.Annarkwin.Platinum.MMO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("User")
public class User implements ConfigurationSerializable
{

	private String uuid = "Broken User";
	private String currentname = "Broken User";
	private ArrayList<String> aka = new ArrayList<String>();

	public User( UUID id, String name )
	{

		uuid = id.toString();
		currentname = name;

	}

	public User( Map<String, Object> o )
	{

		// Get fields of object and retrieve them from map
		for (Field f : getClass().getDeclaredFields())
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

		for (Field f : getClass().getDeclaredFields())
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

	public UUID getUUID()
	{

		return UUID.fromString(uuid);

	}

	public String getActualName()
	{

		return currentname;

	}

	public Collection<String> getAKANames()
	{

		return aka;

	}

	public void newActualName( String name )
	{

		aka.add(currentname);
		currentname = name;

	}

}