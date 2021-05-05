package com.gmail.Annarkwin.Platinum.MMO.DataLibrary;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.FileStorage;
import com.gmail.Annarkwin.Platinum.MMO.User;

@SerializableAs("User")
public class UserManager
{

	private FileStorage file;
	private String section_users = "users";

	private HashSet<User> users;

	public UserManager( JavaPlugin plugin, String filename )
	{

		file = new FileStorage(plugin, filename);
		importData();

	}

	public HashSet<User> getUsers()
	{

		return users;

	}

	public boolean isExistingUser( UUID player )
	{

		return getUser(player) != null;

	}

	public User getUser( UUID id )
	{

		for (User u : users)
		{

			if (u.getUUID().equals(id))
				return u;

		}

		return null;

	}

	public User getUserByActualName( String name )
	{

		for (User u : users)
		{

			if (u.getActualName().equals(name))
				return u;

		}

		return null;

	}

	public void addUser( User u )
	{

		if (users == null)
			users = new HashSet<User>();
		users.add(u);

	}

	@SuppressWarnings("unchecked")
	private void importData()
	{

		if (file.getData().get(section_users) != null)
		{

			users = (HashSet<User>) file.getData().get(section_users);

		}
		else
			users = new HashSet<User>();

	}

	public void saveData()
	{

		file.getData().set(section_users, users);
		file.saveToDisk();

	}

}
