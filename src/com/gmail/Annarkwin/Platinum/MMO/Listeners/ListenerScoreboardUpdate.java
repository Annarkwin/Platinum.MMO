package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

import com.gmail.Annarkwin.Platinum.API.ExperienceManager;
import com.gmail.Annarkwin.Platinum.API.TickerEvent;
import com.gmail.Annarkwin.Platinum.API.TickerEvent.TickerEventType;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;
import com.gmail.Annarkwin.Platinum.MMO.Region;

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
			Objective objective = scoreboard.getObjective("Information");
			Region r = MMO.region_manager.getRegion(p.getLocation());
			Quarry q = MMO.quarry_manager.getQuarry(p.getLocation());

			if (scoreboard == Bukkit.getScoreboardManager().getMainScoreboard())
			{

				scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
				p.setScoreboard(scoreboard);

			}

			for (String s : scoreboard.getEntries())
			{

				scoreboard.resetScores(s);

			}

			if (scoreboard.getObjective("Information") == null)
			{

				objective = scoreboard.registerNewObjective("Information", "", "Information", RenderType.HEARTS);
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);

			}

			// Show current exp
			objective.getScore("񖶆" + ExperienceManager.getTotalExperience(p) + " experience").setScore(0);

			// Show current zone owner
			if (r != null)
			{

				objective.getScore("�1Region: �2" + Bukkit.getOfflinePlayer(r.getOwner()).getName() + "'s")
						.setScore(0);

				if (r.isLocked())
					objective.getScore("�2Region is Locked").setScore(0);

				if (r.isPVP())
					objective.getScore("�3Region is �4PVP").setScore(0);

			}

			// Show current zone owner
			if (q != null)
				objective.getScore("�2Quarry: �2" + q.getName() + "").setScore(0);

		}

	}

}
