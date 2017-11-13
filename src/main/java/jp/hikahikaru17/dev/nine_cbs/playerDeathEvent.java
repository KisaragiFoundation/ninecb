/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hikahikaru17.dev.nine_cbs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.util.HashMap;
/**
 *
 * @author Obsidian550D
 */
public class playerDeathEvent implements Listener {
	@EventHandler
	public void OnDeath(PlayerDeathEvent e) {
		nine_cbs.deathLocation.put(e.getEntity().getName(), e.getEntity().getLocation());
	}
}
