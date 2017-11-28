/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hikahikaru17.dev.nine_cbs;

import org.bukkit.Bukkit;

/**
 *
 * @author Obsidian550D
 */
public class MCVersion {
	double newest = 12.2;
	public boolean isLater(int subversion, double build) {
		if (Bukkit.getVersion().contains("1."+subversion+"."+build)) {
			return true;
		}

		for (int i=subversion;i>=(int)newest;i++) {
			for (double j=0;j>=(newest-(int)newest);j++) {
				if (Bukkit.getVersion().contains("1."+i+"."+j)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isLater(int subversion) {
		if (Bukkit.getVersion().contains("1."+subversion)) {
			return true;
		}

		for (int i=subversion;i>=(int)newest;i++) {
			if (Bukkit.getVersion().contains("1."+i+".")) {
				return true;
			}
		}
		return false;
	}


	public boolean MC1_8(){
		return Bukkit.getVersion().contains("1.8");
	}

	public boolean MC1_9(){
		return Bukkit.getVersion().contains("1.9");
	}

	public boolean MC1_10(){
		return Bukkit.getVersion().contains("1.10");
	}

	public boolean MC1_11(){
		return Bukkit.getVersion().contains("1.11");
	}

	public boolean MC1_12(){
		return Bukkit.getVersion().contains("1.12");
	}
}
