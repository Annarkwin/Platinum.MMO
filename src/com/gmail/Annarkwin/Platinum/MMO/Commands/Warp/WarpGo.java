package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpGo implements Subcommand
{

	private String description = "Teleport to a warp point";
	private MainCommand main;
	private String name = "go";
	private String permission = "platinum.warp.go";
	private boolean playeronly = true;
	private String usage = "/warp go <warp>";

	public WarpGo( MainCommand maincommand )
	{

		main = maincommand;

	}

	@Override
	public String getDescription()
	{

		return description;

	}

	@Override
	public MainCommand getMainCommand()
	{

		return main;

	}

	@Override
	public String getName()
	{

		return name;

	}

	@Override
	public String getPermission()
	{

		return permission;

	}

	@Override
	public String getUsage()
	{

		return usage;

	}

	@Override
	public boolean isPlayerOnly()
	{

		return playeronly;

	}

	@Override
	public void run( CommandSender sender, String[] args )
	{

		Player p = (Player) sender;
		Warp warg;

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the warp");
			return;

		}

		if ((warg = MMO.warp_manager.getWarp(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No warp with that name found");
			return;

		}

		if (!warg.getHook().equalsIgnoreCase("") && !p.hasPermission(warg.getHook()))
		{

			p.sendMessage("§4[Error]:§f You aren't allowed to that warp");
			return;

		}

		if (!warg.isEnabled() && !p.hasPermission("platinum.warp.go.admin"))
		{

			p.sendMessage("§4[Error]:§f That warp is disabled");
			return;

		}

		p.teleport(warg.getLocation());
		p.sendMessage("§2[Info]:§f You have warped");

	}

}
