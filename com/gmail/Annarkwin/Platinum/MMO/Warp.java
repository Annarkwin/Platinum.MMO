package com.gmail.Annarkwin.Platinum.MMO;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

@SerializableAs("Warp")
public class Warp implements ConfigurationSerializable {

	private String name = "Broken warp";				// Region name
	private Location warp = null;				// Warp to zone location
	private String hook = "";
	private boolean enabled = false;

	public Warp(String name, Location warp){
		this.name = name;
		this.warp = warp;
	}

	public Warp(Map<String, Object> o){
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

	public Location getLocation() {
		return warp;
	}

	public void setLocation(Location l) {
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
}