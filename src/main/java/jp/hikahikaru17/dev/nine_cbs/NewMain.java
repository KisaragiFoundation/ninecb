/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hikahikaru17.dev.nine_cbs;
import static org.apache.logging.log4j.status.StatusLogger.getLogger;
import org.bukkit.ChatColor;
import java.util.logging.*;
/**
 *
 * @author Obsidian550D
 */
public class NewMain {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		if (nine_cbs.ChatColorBuilder("&00").equals(ChatColor.BLACK + "0")) {
			getLogger().debug("&00 == §00");
		}
	}
	
}
