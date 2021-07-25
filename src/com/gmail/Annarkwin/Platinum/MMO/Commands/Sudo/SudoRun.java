package com.gmail.Annarkwin.Platinum.MMO.Commands.Sudo;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;

public class SudoRun extends PlatinumCommand
{

	public SudoRun( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
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
		
		return true;

	}

}
