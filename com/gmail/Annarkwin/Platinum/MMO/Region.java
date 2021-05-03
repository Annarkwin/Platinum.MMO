 package com.gmail.Annarkwin.Platinum.MMO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.Cube;

@SerializableAs("Zone")
public class Region implements ConfigurationSerializable {
	private String name = "";
	private String id = "";
	private int layer = 0;
	private String owner = "Broken Zone";		// Owner : String - Player ID
	private ArrayList<String> allowed = new ArrayList<String>();		// String - Player ID
	private Location warp = null;				// Warp to zone location
	private Cube area = null;					// Region area
	private boolean islocked = true;
	private boolean ispublic = false;
	private boolean ispvp = false;
	private boolean ismonsterspawning = true;
	private boolean isanimalspawning = true;

	public Region(Cube area, Player owner){
		this.area = area;
		this.owner = owner.getUniqueId().toString();
		this.id = UUID.randomUUID().toString();
	}

	public Region(Map<String, Object> o){
		//Get fields of object and retrieve them from map
		for(Field f : getClass().getDeclaredFields()){
			try {
				if(o.get(f.getName()) != null)
					f.set(this, o.get(f.getName()));
			} catch (Exception e) {
				//Default
			}
		}
	}

	@Override
	public Map<String, Object> serialize() {
		//Get fields of object and map them with value
		HashMap<String, Object> s = new HashMap<String, Object>();
		for(Field f : getClass().getDeclaredFields()){
			try {
				s.put(f.getName(), f.get(this));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public Cube getArea() {
		return area;
	}

	public void setArea(Cube cube) {
		area = cube;
	}

	public UUID getOwner() {
		return UUID.fromString(owner);
	}

	public void setOwner(UUID id) {
		owner = id.toString();
	}
	
	public ArrayList<String> getAllowed() {
		return allowed;
	}
	
	public boolean addAllowed(UUID player) {
		return allowed.add(player.toString());
	}
	
	public boolean removeAllowed(UUID player) {
		return allowed.remove(player.toString());
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String arg) {
		name = arg;
	}
	
	public Location getWarp() {
		return warp;
	}
	
	public void setWarp(Location l) {
		warp = l;
	}
	
	public void setLocked(boolean arg) {
		islocked = arg;
	}
	
	public boolean isLocked() {
		return islocked;
	}
	
	public boolean isPublic() {
		return ispublic;
	}
	
	public void setPublic(boolean arg) {
		ispublic = arg;
	}
	
	public boolean isPVP() {
		return ispvp;
	}
	
	public void setPVP(boolean arg) {
		ispvp = arg;
	}
	
	public boolean isMonsterSpawning() {
		return ismonsterspawning;
	}
	
	public void setMonsterSpawning(boolean arg) {
		islocked = arg;
	}
	
	public boolean isAnimalSpawning() {
		return isanimalspawning;
	}
	
	public void setAnimalSpawning(boolean arg) {
		islocked = arg;
	}
	
	public boolean isOwner(UUID id) {
		return id.toString().equals(owner);
	}
	
	public boolean isAllowed(UUID id) {
		return isOwner(id) || allowed.contains(id.toString());
	}
	
	public int getLayer() {
		return layer;
	}
	
	public void setLayer(int arg) {
		layer = arg;
	}
	
	public UUID getID() {
		return UUID.fromString(id);
	}
	
}