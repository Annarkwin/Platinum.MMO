package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarrySetBlock extends PlatinumCommand
{

	public QuarrySetBlock( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Quarry qarg;
		ItemStack i = p.getInventory().getItemInMainHand();

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

		if (!p.getInventory().getItemInMainHand().getType().isBlock())
		{

			p.sendMessage("§4[Error]:§f Hold a block");
			return true;

		}

		qarg.setBlock(i.getType(), i.getData().getData());
		p.sendMessage("§2[Info]:§f Quarry has been set to your held block");
		return true;

	}

}
