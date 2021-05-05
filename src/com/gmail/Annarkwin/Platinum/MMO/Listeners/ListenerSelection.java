package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;

public class ListenerSelection implements Listener
{

	Material m;

	public ListenerSelection( Material item )
	{

		m = item;

	}

	// Listen to right click events and set user's point selection
	@EventHandler(ignoreCancelled = false)
	public void selectEvent( PlayerInteractEvent e )
	{

		Player p = e.getPlayer();

		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand() == EquipmentSlot.HAND
				&& p.getInventory().getItemInMainHand().getType() == m)
		{

			Selection sel = SelectionManager.getSelection(p.getUniqueId());
			sel.addPoint(e.getClickedBlock().getLocation());

			if (sel.isInitialized())
			{

				p.sendMessage("§2[Info]:§f Start point selected");

			}
			else
			{

				int width, length;
				Location dim = sel.getArea().getMaximumPoint().clone().subtract(sel.getArea().getMinimumPoint());
				width = dim.getBlockX() + 1;
				length = dim.getBlockZ() + 1;

				p.sendMessage("§2[Info]:§f End point selected : " + width + "X, " + length + "Z");

			}

		}

	}

}
