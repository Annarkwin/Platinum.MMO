package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerMOTD implements Listener
{

	// Send login message
	@EventHandler(ignoreCancelled = false)
	public void sendMOTDonJoin( PlayerJoinEvent e )
	{

		e.getPlayer().sendMessage("�2 - Welcome to the Casual Cult Server! -");
		e.getPlayer().sendMessage("");
		e.getPlayer().sendMessage("�6 - This is a survival server and cheating is not allowed.");
		e.getPlayer().sendMessage("�6 - I'm fixing some bugs and updating the plugin for 1.17 features");

	}

}
