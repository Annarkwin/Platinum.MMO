package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

import com.gmail.Annarkwin.Platinum.API.TickerEvent;
import com.gmail.Annarkwin.Platinum.API.TickerEvent.TickerEventType;

public class ListenerScoreboardUpdate implements Listener
{

	@EventHandler(ignoreCancelled = false)
	public void scoreBoardUpdate( TickerEvent e )
	{

		if (e.getType() != TickerEventType.SCOREBOARD_UPDATE)
			return;

		for (Player p : Bukkit.getOnlinePlayers())
		{

			// Make a new scoreboard for the player
			Scoreboard scoreboard = p.getScoreboard();
			if (scoreboard.getObjective("Information") != null)
				scoreboard.getObjective("Information").unregister();

			Objective objective = scoreboard.registerNewObjective("Information", "", "Information", RenderType.HEARTS);
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			objective.getScore("§0§fCurrent Biome: " + p.getWorld().getBiome(p.getLocation().getBlockX(),
					p.getLocation().getBlockY(), p.getLocation().getBlockZ())).setScore(0);
			objective.getScore("§0§fCoins: 4,433,812").setScore(0);
			objective.getScore("§0§fBounty: 2342 Coins").setScore(0);
			p.setScoreboard(scoreboard);

		}

	}

}
