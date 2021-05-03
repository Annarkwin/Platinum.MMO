package com.gmail.Annarkwin.Platinum.MMO.DataLibrary;

import java.util.HashMap;
import java.util.UUID;

import com.gmail.Annarkwin.Platinum.MMO.Selection;

public abstract class SelectionManager {
	
	private static HashMap<String, Selection> selections = new HashMap<String, Selection>(); 
	
	public static Selection getSelection(UUID player) {
		Selection s = selections.get(player.toString());
		
		if (s == null) {
			s = new Selection();
			selections.put(player.toString(), s);
		}
		
		return s;
	}
}
