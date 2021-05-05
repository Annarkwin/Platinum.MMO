package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryInfo implements Subcommand
{

	private String description = "Show quarry info";
	private MainCommand main;
	private String name = "info";
	private String permission = "platinum.quarry.info";
	private boolean playeronly = true;
	private String usage = "/quarry info <quarry>";

	public QuarryInfo( MainCommand maincommand )
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
		Quarry qarg;

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]:§f Enter the name of the quarry");
			return;

		}

		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null)
		{

			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return;

		}

		p.sendMessage("Quarry Info");
		p.sendMessage(" Quarry Name: " + qarg.getName());
		p.sendMessage(" Enabled: " + qarg.isEnabled());
		p.sendMessage(" Hook: " + qarg.getHook());
		p.sendMessage(" Cooldown (sec): " + qarg.getCooldownSeconds());
		p.sendMessage(" Cooldown Remaining (sec): " + (qarg.getCooldownRemainder() + 1));
		p.sendMessage(" Block: " + qarg.getBlockType());

	}

}
