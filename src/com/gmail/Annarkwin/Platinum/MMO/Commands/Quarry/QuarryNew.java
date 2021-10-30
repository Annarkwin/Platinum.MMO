package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;
import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;

public class QuarryNew extends PlatinumCommand
{

	public QuarryNew( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Selection sel = SelectionManager.getSelection(p.getUniqueId());
		ItemStack i = p.getInventory().getItemInMainHand();

		if (!sel.isComplete())
		{

			p.sendMessage("§4[Error]:§f No selection");
			return true;

		}

		if (MMO.quarry_manager.isOverlappingQuarry(sel.getArea()))
		{

			p.sendMessage("§4[Error]:§f Overlapping quarries");
			return true;

		}

		if (!(args.length > 1))
		{

			p.sendMessage("§4[Error]:§f Enter a name");
			return true;

		}

		if (MMO.quarry_manager.getQuarry(args[1]) != null)
		{

			p.sendMessage("§4[Error]:§f That name is in use");
			return true;

		}

		if (!p.getInventory().getItemInMainHand().getType().isBlock())
		{

			p.sendMessage("§4[Error]:§f Hold a block");
			return true;

		}

		MMO.quarry_manager
				.addQuarry(new Quarry(sel.getArea(), args[1], p.getLocation(), i.getType(), i.getData().getData()));
		p.sendMessage("§2[Info]:§f Quarry created");

		return true;

	}

}
