package com.gmail.Annarkwin.Platinum.MMO.Listeners;

import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.gmail.Annarkwin.Platinum.API.Crops;
import com.gmail.Annarkwin.Platinum.API.InventoryManager;
import com.gmail.Annarkwin.Platinum.API.Events.EntityTrampleEvent;
import com.gmail.Annarkwin.Platinum.API.Events.PlayerHarvestEvent;
import com.gmail.Annarkwin.Platinum.API.Events.PlayerTrampleEvent;

public class ListenerCrops implements Listener {

	//Prevent crop trampling by players
	@EventHandler (ignoreCancelled = false)
	public void playerTrample(PlayerTrampleEvent e) {
		e.setCancelled(true);
	}

	//Prevent crop trampling by non-players
	@EventHandler (ignoreCancelled = false)
	public void entityTrample(EntityTrampleEvent e) {
		e.setCancelled(true);
	}

	//Auto-plant crops if the seed is held being held
	@EventHandler (ignoreCancelled = true, priority = EventPriority.HIGH)
	public void playerHarvest(PlayerHarvestEvent e) {
		if (e.getBlock().getBlockData() instanceof Ageable) {
			Ageable block = (Ageable) e.getBlock().getBlockData();
			if (block.getAge() == block.getMaximumAge()) {
				if (InventoryManager.isHolding(e.getPlayer(), Crops.getSeed(e.getBlock()))) {
					ItemStack i = InventoryManager.getHeldItem(e.getPlayer(), Crops.getSeed(e.getBlock()));
					
					i.setAmount(i.getAmount() - 1);
					e.getBlock().breakNaturally(e.getPlayer().getInventory().getItemInMainHand());
					e.getBlock().setType(e.getBlockType());
					e.setCancelled(true);
				}
			}
		}
	}
}
