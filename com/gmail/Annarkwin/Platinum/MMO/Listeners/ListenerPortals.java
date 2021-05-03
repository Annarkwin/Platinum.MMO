package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.Annarkwin.Platinum.API.TickerEvent;
import com.gmail.Annarkwin.Platinum.API.TickerEvent.TickerEventType;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class ListenerPortals implements Listener {
	
	//Teleport player when entering a portal
	@EventHandler (ignoreCancelled = false)
	public void portalEvent(TickerEvent event){
		if (event.getType() != TickerEventType.PORTAL) return;
		for (Player p : Bukkit.getOnlinePlayers()){
			Portal portal = MMO.portal_manager.getPortal(p.getLocation());
			if (portal == null) continue;
			if (!portal.isEnabled()) continue;
			if (!portal.getHook().equals("") && !p.hasPermission(portal.getHook())) continue;
				
			p.teleport(portal.getWarpLocation());
			p.sendMessage("§2[Info]:§f Teleported to " + portal.getWarp().getName());
		}
	}
}
