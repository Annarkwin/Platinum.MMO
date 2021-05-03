package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarrySetBlock implements Subcommand {

	private String description = "Set quarry block";
	private MainCommand main;
	private String name = "setblock";
	private String permission = "platinum.quarry.setblock";
	private boolean playeronly = true;
	private String usage = "/quarry setblock <quarry>";

	public QuarrySetBlock(MainCommand maincommand) {
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

	@SuppressWarnings("deprecation")
	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		Quarry qarg;
		ItemStack i = p.getInventory().getItemInMainHand();
		if (args.length < 2) {
			p.sendMessage("§4[Error]:§f Enter the name of the quarry");
			return;
		}
		if ((qarg = MMO.quarry_manager.getQuarry(args[1])) == null) {
			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return;
		}
		if (!p.getInventory().getItemInMainHand().getType().isBlock()) {
			p.sendMessage("§4[Error]:§f Hold a block");
			return;
		}
		qarg.setBlock(i.getType(), i.getData().getData());
		p.sendMessage("§2[Info]:§f Quarry has been set to your held block");
	}
}
