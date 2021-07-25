package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryDisable extends PlatinumCommand
{

	public QuarryDisable( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Quarry qarg;

		if (args.length < 2)
		{

			p.sendMessage("�4[Error]:�f Enter the name of the quarry");
			return true;

		}

		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null)
		{

			p.sendMessage("�4[Error]:�f No quarry with that name found");
			return true;
		}

		if (!qarg.isEnabled())
		{

			p.sendMessage("�4[Error]:�f That quarry is already disabled");
			return true;

		}

		p.sendMessage("�2[Info]:�f Quarry usability has been set to " + qarg.toggleEnabled());

		return true;
	}

}
