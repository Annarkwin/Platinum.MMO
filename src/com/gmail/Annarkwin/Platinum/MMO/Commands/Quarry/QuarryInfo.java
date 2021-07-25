package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryInfo extends PlatinumCommand
{

	public QuarryInfo( String name, String permission, boolean player, String description, String usage )
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

			p.sendMessage("§4[Error]:§f Enter the name of the quarry");
			return true;

		}

		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return true;

		}

		p.sendMessage("Quarry Info");
		p.sendMessage(" Quarry Name: " + qarg.getName());
		p.sendMessage(" Enabled: " + qarg.isEnabled());
		p.sendMessage(" Hook: " + qarg.getHook());
		p.sendMessage(" Cooldown (sec): " + qarg.getCooldownSeconds());
		p.sendMessage(" Cooldown Remaining (sec): " + (qarg.getCooldownRemainder() + 1));
		p.sendMessage(" Block: " + qarg.getBlockType());
		return true;

	}

}
