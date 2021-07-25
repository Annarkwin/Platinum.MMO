package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionInfo extends PlatinumCommand
{

	public RegionInfo( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Region r = MMO.region_manager.getRegion(p.getLocation());

		if (r == null)
		{

			p.sendMessage("§4[Error]:§f You are not standing in a region");

		}
		else
		{

			p.sendMessage("§2[Region Info]: §f" + r.getID());
			p.sendMessage(" §2Name: §f" + r.getName());
			p.sendMessage(" §2Owner: §f" + Bukkit.getOfflinePlayer(r.getOwner()).getName());
			p.sendMessage(" §2Animals: §f" + r.isAnimalSpawning());
			p.sendMessage(" §2Monsters: §f" + r.isMonsterSpawning());
			p.sendMessage(" §2Locked: §f" + r.isLocked());
			p.sendMessage(" §2Public: §f" + r.isPublic());
			p.sendMessage(" §2PVP: §f" + r.isPVP());

		}
		return true;

	}

}
