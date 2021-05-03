package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;
import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;

public class QuarryNew implements Subcommand {

	private String description = "Create a new quarry";
	private MainCommand main;
	private String name = "new";
	private String permission = "platinum.quarry.new";
	private boolean playeronly = true;
	private String usage = "/quarry new <name>";

	public QuarryNew(MainCommand maincommand) {
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
		Selection sel = SelectionManager.getSelection(p.getUniqueId());
		ItemStack i = p.getInventory().getItemInMainHand();
		if (!sel.isComplete()) {
			p.sendMessage("§4[Error]:§f No selection");
			return;
		}
		if (MMO.quarry_manager.isOverlappingQuarry(sel.getArea())) {
			p.sendMessage("§4[Error]:§f Overlapping quarries");
			return;
		}
		if (!(args.length > 1)) {
			p.sendMessage("§4[Error]:§f Enter a name");
			return;
		}
		if (MMO.quarry_manager.getQuarry(args[1]) != null) {
			p.sendMessage("§4[Error]:§f That name is in use");
			return;
		}
		if (!p.getInventory().getItemInMainHand().getType().isBlock()) {
			p.sendMessage("§4[Error]:§f Hold a block");
			return;
		}
		MMO.quarry_manager.addQuarry(new Quarry(sel.getArea(), args[1], p.getLocation(), i.getType(), i.getData().getData()));
		p.sendMessage("§2[Info]:§f Quarry created");
	}
}
