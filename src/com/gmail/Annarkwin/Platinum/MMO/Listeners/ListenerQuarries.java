package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.Annarkwin.Platinum.API.TickerEvent;
import com.gmail.Annarkwin.Platinum.API.TickerEvent.TickerEventType;
import com.gmail.Annarkwin.Platinum.API.Events.PlayerMineEvent;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class ListenerQuarries implements Listener {
	
	//Auto-fill quarries
	@EventHandler (ignoreCancelled = false)
	public void quarryFill(TickerEvent event){
		if (event.getType() != TickerEventType.QUARRY_FILL) return;
		for (Quarry q : MMO.quarry_manager.getQuarries()) {
			if (q.isCooldownFinished() && q.isEnabled()) q.refill();
		}
	}
	
	//Teleport players who log in inside quarries to the quarries' warp point
	@EventHandler (ignoreCancelled = false)
	public void quarryLogin(PlayerJoinEvent event){
		Quarry q = MMO.quarry_manager.getQuarry(event.getPlayer().getLocation());
		
		if (q != null) event.getPlayer().teleport(q.getWarpLocation());
	}
	
	@EventHandler (ignoreCancelled = false)
	public void quarryMine(PlayerMineEvent event) {
		Quarry q = MMO.quarry_manager.getQuarry(event.getBlock().getLocation());
		
		if (q != null && (q.getHook() == "" || event.getPlayer().hasPermission(q.getHook()))) event.setCancelled(false);
	}
}
