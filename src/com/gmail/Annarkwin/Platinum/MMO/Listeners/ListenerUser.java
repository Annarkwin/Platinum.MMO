package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ListenerUser implements Listener
{

	HashMap<UUID, Integer> movementcounter = new HashMap<UUID, Integer>();

	// TODO
	@EventHandler(ignoreCancelled = false)
	public void userUpdateEvent( PlayerLoginEvent e )
	{

	}

	// Check afk status of player on player move
	@EventHandler(ignoreCancelled = false)
	public void playerMoveEvent( PlayerMoveEvent e )
	{

		Player p = e.getPlayer();

		if (p.getPlayerListName() != null && p.getPlayerListName().contains("§4-AFK- §7"))
		{

			if (movementcounter.get(p.getUniqueId()) != null && movementcounter.get(p.getUniqueId()) > 10)
			{

				movementcounter.put(p.getUniqueId(), 0);
				p.setPlayerListName(p.getPlayerListName().replace("§4-AFK- §7", ""));
				p.sendMessage("§2[Info]:§f You are no longer AFK");
				p.setSleepingIgnored(false);

			}
			else
			{

				Integer count = movementcounter.get(p.getUniqueId());

				if (count == null)
				{

					movementcounter.put(p.getUniqueId(), 1);

				}
				else
				{

					movementcounter.put(p.getUniqueId(), ++count);

				}

			}

		}

	}

}
