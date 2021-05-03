package com.gmail.Annarkwin.Platinum.MMO.DataLibrary;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.FileStorage;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpManager {

	private FileStorage file;
	private String section_warps = "warps";
	
	private HashSet<Warp> warps;
	
	public WarpManager(JavaPlugin plugin, String filename) {
		file = new FileStorage(plugin, filename);
		importData();
	}

	public Collection<Warp> getWarps(){
		return warps;
	}

	public Warp getWarp(String name){
		for (Warp p : warps){
			if(p.getName().equalsIgnoreCase(name)) return p;
		}
		return null;
	}

	public void addWarp(Warp p){
		if(warps == null) warps = new HashSet<Warp>();
		warps.add(p);
	}

	public void removeWarp(String name){
		for(Warp p : warps){
			if(p.getName().equalsIgnoreCase(name)){
				for (Portal port : MMO.portal_manager.getPortalsToWarp(p))
					MMO.portal_manager.removePortal(port);
				
				warps.remove(p);
				return;
			}
		}
	}

	public void removeWarp(Warp p){
		for (Portal port : MMO.portal_manager.getPortalsToWarp(p))
			MMO.portal_manager.removePortal(port);
		
		warps.remove(p);
	}
	
	@SuppressWarnings("unchecked")
	private void importData() {
		if (file.getData().get(section_warps) != null) {
			warps = (HashSet<Warp>) file.getData().get(section_warps);
		} else warps = new HashSet<Warp>();
	}

	public void saveData() {
		file.getData().set(section_warps, warps);
		file.saveToDisk();
	}
}
