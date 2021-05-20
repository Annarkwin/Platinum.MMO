package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ListenerChat implements Listener
{

	// Listen to chat events and replace $code$ with §code
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void chatEvent( AsyncPlayerChatEvent e )
	{

		e.setMessage(e.getMessage().replaceAll("\\$(\\S)\\$", "§$1"));

	}

}
