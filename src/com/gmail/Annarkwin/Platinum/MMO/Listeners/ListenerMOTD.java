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

		e.getPlayer().sendMessage("§2 - Welcome to the Casual Cult Server! -");
		e.getPlayer().sendMessage("");
		e.getPlayer()
				.sendMessage("§6 - "
						+ "It didn't need to be said, but this is a survival server and cheating is not allowed. "
						+ "Flying, Speeding, X-ray, No-Clip, Freecam, Auras, etc are not allowed. "
						+ "Automation machines are ONLY allowed if they are not lag-inducing from periods of AFK.");

	}

}
