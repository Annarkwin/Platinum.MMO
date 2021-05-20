package com.gmail.Annarkwin.Platinum.MMO.Commands.Sudo;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class SudoRun implements Subcommand
{

	private String description = "Make a player send a command";
	private MainCommand main;
	private String name = "run";
	private String permission = "platinum.sudo.run";
	private boolean playeronly = true;
	private String usage = "/sudo run <player> <command string>";

	public SudoRun( MainCommand maincommand )
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

		if (args.length > 1)
		{

			Player p = Bukkit.getPlayer(args[1]);

			if (p != null)
			{

				if (args.length > 2)
				{

					String command = "";

					for (int i = 2; i < args.length; i++)
					{

						command = command + args[i] + " ";

					}

					p.performCommand(command);
					sender.sendMessage("§2[Info]:§f You made " + p.getName() + " run /" + command);
					p.sendMessage("§2[Info]:§f Someone made you run /" + command);

				}
				else
				{

					sender.sendMessage("§4[Error]:§f You must provide the command for the player to send");

				}

			}
			else
			{

				sender.sendMessage("§4[Error]:§f The player name provided is invalid");

			}

		}
		else
		{

			sender.sendMessage("§4[Error]:§f You must enter a player to send the command as");

		}

	}

}
