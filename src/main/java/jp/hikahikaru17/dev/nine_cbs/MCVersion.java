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
	public boolean isLater(int subversion) {
		return Bukkit.getVersion().contains("1."+subversion);
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
