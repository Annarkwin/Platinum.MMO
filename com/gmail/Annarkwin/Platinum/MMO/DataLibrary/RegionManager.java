package com.gmail.Annarkwin.Platinum.MMO.DataLibrary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.Annarkwin.Platinum.API.Cube;
import com.gmail.Annarkwin.Platinum.API.FileStorage;
import com.gmail.Annarkwin.Platinum.MMO.Region;
import com.gmail.Annarkwin.Platinum.MMO.Exceptions.RegionAreaSplitException;
import com.gmail.Annarkwin.Platinum.MMO.Exceptions.RegionIntersectException;
import com.gmail.Annarkwin.Platinum.MMO.Exceptions.UnallowedToParentRegionException;

public class RegionManager {

	private FileStorage file;
	private String section_regions = "regions";

	private HashSet<Region> regions;
	
	public RegionManager(JavaPlugin plugin, String filename) {
		file = new FileStorage(plugin, filename);
		importData();
	}
	
	public Region addRegion(Region r, UUID player, int layer) throws RegionAreaSplitException, RegionIntersectException, UnallowedToParentRegionException {
		//No Intersecting region on same layer
		//No Intersecting region on higher layer
		//Region completely contained within region on lower layer
		//Player has permissions to region on lower layer
		regions.add(r);
		return r;
	}
	
	public Region removeRegion(Region r){
		// TODO 
		
		//Player has permissions to lower region, removed region, and all higher regions
		//Remove all higher regions
		return r;
	}
	
	public Region removeRegion(Region r, int layer){
		// TODO 
		return r;
	}
	
	//Return the highest region at location
	public Region getRegion(Location location) {
		// TODO 
		return null;
	}

	//Return the region at location for the specified layer
	public Region getRegion(Location location, int layer) {
		// TODO 
		return null;
	}

	//Return all regions
	public HashSet<Region> getRegions() {
		return regions;
	}

	//Return all regions owned by given player
	public ArrayList<Region> getRegions(UUID player) {
		// TODO 
		return null;
	}

	//Return all regions in all layers at location
	public ArrayList<Region> getRegions(Location location) {
		// TODO 
		return null;
	}
	
	//Return all regions in specified layer
	public HashSet<Region> getRegions(int layer){
		
		return null;
	}

	public boolean isIntersectingRegion(Cube c, int layer){
		for (Region r : getRegions()){
			if (r.getLayer() == layer && r.getArea().intersectsCube(c)) return true;
		}
		return false;
	}

	public Region getIntersectingRegion(Cube c, int layer){
		for (Region r : getRegions()){
			if (r.getLayer() == layer && r.getArea().intersectsCube(c)) return r;
		}
		return null;
	}
		
	@SuppressWarnings("unchecked")
	private void importData() {
		if (file.getData().get(section_regions) != null) {
			regions = (HashSet<Region>) file.getData().get(section_regions);
		}
	}

	//Save file data
	public void saveData() {
		file.getData().set(section_regions, regions);
		file.saveToDisk();
	}
	
}
