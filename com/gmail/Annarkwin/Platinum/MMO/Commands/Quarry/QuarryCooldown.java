package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryCooldown implements Subcommand {

	private String description = "Set cooldown";
	private MainCommand main;
	private String name = "cooldown";
	private String permission = "platinum.quarry.cooldown";
	private boolean playeronly = true;
	private String usage = "/quarry cooldown <quarry> <seconds>";

	public QuarryCooldown(MainCommand maincommand) {
		main = maincommand;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public int getInt(String s) {
		return Integer.parseInt(s);
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

	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public boolean isPlayerOnly() {
		return playeronly;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		Quarry qarg;
		if (args.length < 3) {
			p.sendMessage("§4[Error]:§f Enter the name of the quarry then the time in minutes");
			return;
		}
		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null) {
			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return;
		}
		if (!isInt(args[2])) {
			p.sendMessage("§4[Error]:§f Invalid time");
			return;
		}
		qarg.setCooldownSeconds(getInt(args[2]));
		p.sendMessage("§2[Info]:§f Quarry timer set");
	}
}
