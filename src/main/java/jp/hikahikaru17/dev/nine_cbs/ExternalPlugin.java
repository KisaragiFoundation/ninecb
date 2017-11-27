/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hikahikaru17.dev.nine_cbs;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author Obsidian550D
 */
public class ExternalPlugin {
	private static final PluginManager PM = getServer().getPluginManager();
	public CoreProtectAPI getCoreProtect() {
		Plugin plugin = PM.getPlugin("CoreProtect");

		// Check that CoreProtect is loaded
		if (plugin == null || !(plugin instanceof CoreProtect)) {
			return null;
		}

		// Check that the API is enabled
		CoreProtectAPI CoreProtect = ((CoreProtect)plugin).getAPI();
		if (CoreProtect.isEnabled()==false){
			return null;
		}
		// Check that a compatible version of the API is loaded
		if (CoreProtect.APIVersion() < 4){
			return null;
		}

		return CoreProtect;
	}

	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = PM.getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}

	public String notEnabledPL(String plname) {
		return String.format("%s は有効化されていないようです。\n管理者へお問い合わせください。",plname);
	}

	public static class PluginName {
		public static final String ESSENTIALS = "Essentials";
		public static final String WORLDEDIT = "WorldEdit";
		public static final String COREPROTECT = "CoreProtect";
		public static final String ICJUKEBOX = "icJukeBox";
		public static final String CRACKSHOT = "CrackShot";
		public static final String MYMENU = "MyMenu";
	}

	public PluginName NEW() {
		return new PluginName();
	}

}
