package com.gmail.Annarkwin.Platinum.MMO;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.Cube;

@SerializableAs("Quarry")
public class Quarry implements ConfigurationSerializable {

	private Cube area = null;					// Region area
	private String name = "Broken Quarry";				// Region name
	private Location warp = null;				// Warp to zone location
	private boolean enabled = true;
	private String hook = "";
	private long cooldown = 0;
	private long lastfill = Instant.now().getEpochSecond();
	private String material = Material.AIR.toString();
	private int data = 0;

	public Quarry(Cube area, String name, Location warp, Material m, byte dta){
		this.area = area;
		this.name = name;
		this.warp = warp;
		material = m.toString();
		data = dta;
	}

	public Quarry(Map<String, Object> o){
		//Get fields of object and retrieve them from map
		for(Field f : this.getClass().getDeclaredFields()){
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
		for(Field f : this.getClass().getDeclaredFields()){
			try {
				s.put(f.getName(), f.get(this));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public Cube getArea() {
		return this.area;
	}

	public Location getWarpLocation() {
		return warp;
	}

	public void setWarpLocation(Location l) {
		warp = l;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean toggleEnabled() {
		return enabled = !enabled;
	}

	public String getHook()	{
		return hook;
	}

	public void setHook(String h){
		hook = h;
	}
	
	public void setBlock(Material type, byte dta) {
		material = type.toString();
		data = dta;
	}
	
	public Material getBlockType() {
		return Material.getMaterial(material);
	}
	
	public void refill(){
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (area.containsPoint(p.getLocation())) p.teleport(warp);
		}
		
		setArea(area, Material.getMaterial(material),(byte) data);
		
		lastfill = Instant.now().getEpochSecond();
	}
	
	public void setCooldownSeconds(long cd){
		cooldown = cd;
		lastfill = Instant.now().getEpochSecond();
	}
	
	public long getCooldownSeconds(){
		return cooldown;
	}
	
	public long getCooldownRemainder(){
		return (cooldown + lastfill - (Instant.now().getEpochSecond()));
	}
	
	public boolean isCooldownFinished(){
		if (cooldown == 0) return false;
		return (Instant.now().getEpochSecond() - lastfill > cooldown);
	}
	
	public long getLastRefill(){
		return lastfill;
	}
	
	private void setArea(Cube sel, Material type, byte data) {
		Location min = sel.getMinimumPoint();
		Location max = sel.getMaximumPoint();
		World world = min.getWorld();
		
		for (int x = min.getBlockX(); x <= max.getBlockX(); x ++) {
			for (int y = min.getBlockY(); y <= max.getBlockY(); y ++) {
				for (int z = min.getBlockZ(); z <= max.getBlockZ(); z ++) {
					world.getBlockAt(x, y, z).setType(type);
				}
			}
		}
	}

}
