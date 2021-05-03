package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryGo implements Subcommand {

	private String description = "Warp to a quarry";
	private MainCommand main;
	private String name = "go";
	private String permission = "platinum.quarry.go";
	private boolean playeronly = true;
	private String usage = "/quarry go <quarry>";

	public QuarryGo(MainCommand maincommand) {
		main = maincommand;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public MainCommand getMainCommand() {
		return main;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPermission() {
		return permission;
	}

	@Override
	public String getUsage() {
		return usage;
	}

	@Override
	public boolean isPlayerOnly() {
		return playeronly;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		Quarry qarg;
		if (args.length < 2) {
			p.sendMessage("�4[Error]:�f Enter the name of the quarry");
			return;
		}
		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null) {
			p.sendMessage("�4[Error]:�f No quarry with that name found");
			return;
		}
		if (!qarg.getHook().equalsIgnoreCase("") && !p.hasPermission(qarg.getHook())) {
			p.sendMessage("�4[Error]:�f You aren't allowed to that quarry");
			return;
		}
		p.teleport(qarg.getWarpLocation());
		p.sendMessage("�2[Info]:�f You have been warped");
	}
}
