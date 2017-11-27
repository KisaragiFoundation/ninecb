/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hikahikaru17.dev.nine_cbs;

/**
 *
 * @author Obsidian550D
 */
class api { /*
	static final ExternalPlugin EP = new ExternalPlugin();
	static final Logger LOG = getLogger();

	/*
		@author
	*/
	/*
	public boolean isPlayer(CommandSender s){
		return (s instanceof Player);
	}

	public boolean canBuild(Location l, Player p) {
		CommandSender sender = p;
		if (EP.getWorldGuard() != null) {
			if (EP.getWorldGuard().canBuild(p, p.getLocation()) == false) {
				return false;
			}
		}

		l.setY(l.getY()-1);
		if (DEBUG) {
			LOG.info(String.format("%s",p.getLocation().getBlockY()));
		}
		if (EP.getWorldGuard() == null) {
			nine_cbs.sendmes(p,EP.notEnabledPL("WorldGuard"));
			return true;
		} else if (EP.getWorldGuard().canBuild(p, l) != true) {
			sender.sendMessage(nine_cbs.CLAIMED);
			return true;
		}
		//CHECK SPAWN PROTECTION
		int sprad = getServer().getSpawnRadius();
		double px = l.getX();
		double py = l.getY();
		double pz = l.getZ();
		Location spawnCenter = l.getWorld().getSpawnLocation();
		double sx = spawnCenter.getX();
		double sz = spawnCenter.getZ();
		BlockVector min = new BlockVector(sx-sprad,0,sz-sprad);
		BlockVector max = new BlockVector(sx+sprad,255,sz+sprad);
		if (min.getX() > px || max.getX() < px || min.getZ() > pz || min.getZ() < pz) { //ABLE
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param args a
	 * @param toofew a
	 * @param toomany a
	 * @param sender a
	 * @param command a
	 */
	/*protected void setCB(String args[], int toofew, int toomany, CommandSender sender, String command) {
		boolean isPlayer = isPlayer(sender);
		Player player = null;
		if (isPlayer) {
			player = (Player)sender;
		}
		if (args.length < toofew) { // 0 < 1
			nine_cbs.sendmes(sender,nine_cbs.TOO_FEW_ARGS);
			return;
		}

		if (args.length > toomany) { // 4 > 3
			nine_cbs.sendmes(sender,nine_cbs.TOO_MANY_ARGS);
		}
		if (isPlayer) {
			if (player == null) {
				nine_cbs.sendmes(sender,"NULL: player");
			} else {
				LOG.info("[SETCB@API] "+command);
				changeCB(player.getLocation(), command,player.getName());
			}
		} else {
			nine_cbs.sendmes(sender,nine_cbs.MUST_BE_PLAYER);
		}
	}*/

	/**
	* pがlにあるCBをcで置き換えたことにする
	* @param l CBの座標
	* @param c コマンド
	* @param p プレイヤーの名前
	*/

	/*protected void changeCB(Location l, String c, String p){
		l.setY(l.getY()-1);
		LOG.info(String.format("%f",l.getY()));
		if (getCB(l) != null) {
			LOG.info("[CHANGECB@API] "+c);
			setCommand(l,c);
			this.update(l);
			log(l,"change",c,p);
		} else {
			LOG.warning("CANCELED OPRATION.");
		}
	}*/

	/**
	@param l CBの座標
	@param act 行動
	@param c コマンド
	@param p プレイヤーの名前
	*/

	/*
	protected void log(Location l, String act, String c, String p) {
		try {
			final String BASE_PATH = "plugins/ninecb/blocklog/";
			final String FILE_NAME = String.format("%d_%d_%d.yml",l.getBlockX(), l.getBlockY(), l.getBlockZ());
			File newdir = new File(BASE_PATH);
			if (newdir.exists()) {
				// Nothing
			} else {
				newdir.mkdir();
			}
			File f = new File(BASE_PATH + FILE_NAME);
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
					int i;
					for (i=0;i <= 999999;i ++) {
						if (yamlFile.get(String.format("log%d",i)) == null) {break;}
					}
					HashMap<String,String> properties = new HashMap<>();
					Long ms = System.currentTimeMillis();

					properties.put("time",ms.toString());
					properties.put("action",act);
					properties.put("after", c);
					properties.put("player",p);
					yamlFile.set(String.format("log%d",i),properties);
			try {
				LOG.log(Level.INFO,"TRYING SAVE TO " + BASE_PATH + "{0}", FILE_NAME);
				yamlFile.save(BASE_PATH + FILE_NAME);
				LOG.info("SUCCESS SAVE!");
			} catch (IOException e) {
				LOG.warning("FAILED SAVING FILE!");
			}

		} catch (RuntimeException e) {
			LOG.warning("ERROR OCUSED : RUNTIME EXEPTION");
		}
	} */

	/*protected boolean isCB(Location l) {
		return l.getBlock().getType() == Material.COMMAND;
	} */

	/*private CommandBlock getCB(Location l) {
		if (!isCB(l)) {
			return null;
		}
		return (CommandBlock)l.getBlock().getState();
	} */

	/*private void update (Location l) {
		if (!isCB(l)) {
			return;
		}
		getCB(l).update(true);
	} */

	/* protected boolean setCommand(Location l,String c) {
		if (getCB(l) == null) {
			return false;
		}
		LOG.info("[SETCOMMAND@API]"+c);
		getCB(l).setCommand(c);
		return true;
	} */

	/* protected String getCommand(Location l) {
		if (getCB(l) == null) {
			return "";
		}
		return getCB(l).getCommand();
	} */
}

