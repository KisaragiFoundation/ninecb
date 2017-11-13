/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hikahikaru17.dev.nine_cbs;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Material;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import com.sk89q.worldguard.bukkit.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockVector;
import net.coreprotect.*;
import org.bukkit.Bukkit;
/*
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
*/
/**
 *
 * @author Obsidian550D
 */

public class nine_cbs extends JavaPlugin implements CommandExecutor{
	public static HashMap<String,Location> deathLocation = new HashMap<>();
	private final static String prefix = ChatColor.DARK_AQUA + "" + "[Nine_CB] " + ChatColor.RESET;
	private final static String MUST_BE_PLAYER = "Canceled (MUST BE PLAYER)";
	private final static String TOO_FEW_ARGS = "引数が少なすぎます";
	private final static String TOO_MANY_ARGS = "引数が多すぎます";
	private final static String COMMAND_TRIGER = "コマンドブロックの上に立って実行するとコマンドが入ります";
	private final static String RANGE10 = ChatColor.GRAY +"(CBから半径10m以内のプレイヤー全員へ送信)"+ChatColor.RESET;
	private final static String DEFAULT_SELECTER = "@p[r=10]";
	private final static String ALL_SELECTER = "@a[r=10]";
	private final static String VERSION = "1.9";
	private final static String TRIGGER = String.format("%s===%s %s %s===\n", ChatColor.AQUA, ChatColor.LIGHT_PURPLE, COMMAND_TRIGER, ChatColor.AQUA);
	private final static String CLAIMED = "保護されています！";
	private final static int CBHELP_MAXPAGE = 3;
	public final static boolean DEBUG = true;
	@Override
	public void onEnable() {
		getLogger().info("test enable");
		// コマンドを実行するプラグインをこれにするという設定
		getCommand("cbhelp").setExecutor(this);
		getCommand("cbwarp").setExecutor(this);
		getCommand("cbgm").setExecutor(this);
		getCommand("cbtp").setExecutor(this);
		getCommand("cbgive").setExecutor(this);
		getCommand("cbtell").setExecutor(this);
		getCommand("cbsound").setExecutor(this);
		getCommand("cbtitle").setExecutor(this);
		getCommand("cbsubtitle").setExecutor(this);
		getCommand("cbeffect").setExecutor(this);
		getCommand("cbxp").setExecutor(this);
		getCommand("cbspeed").setExecutor(this);
		getCommand("cbmenu").setExecutor(this);
		getCommand("cbgod").setExecutor(this);
		getCommand("cbmenu").setExecutor(this);
		getCommand("cbgod").setExecutor(this);
		getCommand("cbtpt").setExecutor(this);
		getCommand("cbmusic").setExecutor(this);
		getCommand("cbshot").setExecutor(this);
		getCommand("cbtell-a").setExecutor(this);
		getCommand("cbtitle-a").setExecutor(this);
		getCommand("cbsubtitle-a").setExecutor(this);
		getCommand("ninecb").setExecutor(this);
		getCommand("cmb").setExecutor(this);
		getCommand("cmd").setExecutor(this);
		getCommand("uncmb").setExecutor(this);
		getCommand("cbactionbar").setExecutor(this);
		getCommand("cbactionbar-a").setExecutor(this);
		getCommand("cbback").setExecutor(this);
		//this.getServer().getPluginManager().registerEvents(new MyListenerClass(), this));
	}

	@Override
	public void onDisable() {
		getLogger().info("test disable");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String cmdname;
		String pa;
		Player player = null;
		boolean isPlayer;
		if (sender instanceof Player) {
			pa = sender.getName();
			player = (Player)sender;
			isPlayer = true;
		} else {
			pa = "#";
			isPlayer = false;
		}
		cmdname = cmd.getName().toLowerCase();
		switch (cmdname) {
			case "ninecb":
				break;
		}
		if (cmdname.equalsIgnoreCase("ninecb")){
			String sendmes = "";
			if (args.length < 1) {
				sendmes(sender,"引数を入力してください！");
				return true;
			}
			switch (args[0].toLowerCase()) {
				case "log":
					if (player == null) {
						sender.sendMessage(MUST_BE_PLAYER);
						return true;
					}
					final Location locate = player.getLocation();
					final String BASE_PATH = "plugins/ninecb/blocklog/";
					final String FILE_NAME = String.format("%d_%d_%d.yml",locate.getBlockX(),(int)(locate.getY()-1),(int)locate.getZ());
					getLogger().info(FILE_NAME);
					final File f = new File(BASE_PATH + FILE_NAME);
					YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
					String m = "";
					double timesago;
					int i;
					for (i=0;i <= 999999;i ++) {
						if (yamlFile.get(String.format("log%d",i)) == null) {break;}
					}
					m += "----"+ " Nine_cb/LOGS " + "----\n";
					Long nowTime = System.currentTimeMillis();
					for (int j=i-1;j >= 0;j -= 1) {
						Long beforeTime = Long.parseLong(yamlFile.getString(String.format("log%d.time", j)));
						getLogger().info(String.format("[N,B,N-B]%d - %d = %d",nowTime,beforeTime,nowTime-beforeTime));
						timesago = (double)((nowTime - beforeTime) / (1000*60*60)); //時間単位
						getLogger().info(yamlFile.getString(String.format("log%d.time", j)));
						String l = "";
						l += String.format("%.2f",((nowTime-beforeTime)/(60*60*1000d)));
						getLogger().info(l);
						m += l; // time
						m += "/h ago : ";
						m += yamlFile.getString(String.format("log%d.player", j)); // user
						m += " ";
						m += yamlFile.getString(String.format("log%d.action", j)); // action
						m += " ";
						m += yamlFile.getString(String.format("log%d.after",j)); //command
						m += "\n";
					}
					//logging(player.getLocation(),"test","minecraft:tell @p this is testing (really test!!!!!!!)",player.getName());
					getLogger().info(m);
					sender.sendMessage(m);
					return true;
				case "help":
					sendmes(sender,"Type /cbhelp.");
					return true;
				case "about":
					sendmes = "";
					sendmes += "9CB (SALVAGED)\n";
					sendmes += "BY: lolita_is_god\n";
					sendmes += "Discord: @srf78#4256\n";
					sender.sendMessage(sendmes);
					return true;
				case "update":
					sender.sendMessage("");
					return true;
				case "test":
					if (args.length < 2) {
						sendmes(sender,"テストする項目を入力してください！");
						return true;	
				}	
					switch(args[1].toLowerCase()) {
						/*case "colorbuilder":
							if ((ChatColor.RESET+"a").equals(ChatColorBuilder("&ra"))) {
								sender.sendMessage(prefix + "成功");
								return true;
							} else {
								sender.sendMessage(prefix + "失敗");
								return true;
							}
							//break; */
						default:
							sendmes(sender,"不明なテスト");
							return true;
							//break;

					}
				case "version":
					sendmes(sender,"バージョン: " + VERSION);
					return true;
				case "change":
					break;
				default:
					sendmes(sender,"不明なサブコマンドです。");
					return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbhelp")) {
			if (args.length == 0) {
				String args2[] = new String[1];
				args2[0] = "1";
				args = args2;
			}
			String helpmes = "";
			String lineend = ChatColor.RESET + "\n";
			String linebegin = ChatColor.YELLOW + "";
			switch (args[0]) {
				case "1":
					helpmes += TRIGGER;
					helpmes += String.format("%s/cbwarp <ワープ名>%s", linebegin, lineend);
					helpmes += String.format("%s/cbgm <0|1|2|3>%s", linebegin, lineend);
					helpmes += String.format("%s/cbtp <x> <y> <z>%s", linebegin, lineend);
					helpmes += String.format("%s/cbgive <アイテムID> [量] [データ値] [データタグ]%s", linebegin, lineend);
					helpmes += String.format("%s/cbtell <メッセージ>%s", linebegin, lineend);
					helpmes += String.format("%s/cbsound <音> [x] [y] [z] [音量] [ピッチ] [最小音量]%s", linebegin, lineend);
					helpmes += String.format("%s/cbtitle <メッセージ>%s", linebegin, lineend);
					helpmes += String.format("%s/cbsubtitle <メッセージ>%s", linebegin, lineend);
					//
					helpmes += RETACS(1);
					break;
				case "2":
					helpmes += TRIGGER;
					helpmes += String.format("%s/cbeffect <効果> [秒数] [強さ] [パーティクル表示(true/false)]%s", linebegin, lineend);
					helpmes += String.format("%s/cbxp <量|L <レベル>>%s", linebegin, lineend);
					helpmes += String.format("%s/cbspeed <walk|fly> <0~10>%s", linebegin, lineend);
					helpmes += String.format("%s/cbmenu <メニュー名>%s", linebegin, lineend); //mmopen $1 @p[r=10]
					helpmes += String.format("%s/cbgod <enable|disable>%s", linebegin, lineend);
					helpmes += String.format("%s/cbfly <enable|disable>%s", linebegin, lineend);
					helpmes += String.format("%s/cbtpt <enable|disable>%s", linebegin, lineend);
					helpmes += String.format("%s/cbmusic <曲名>%s", linebegin, lineend);
					//
					helpmes += RETACS(2);
					break;
				case "3":
					helpmes += TRIGGER;
					helpmes += String.format("%s/cbshot <名前>%s", linebegin, lineend);
					helpmes += String.format("%s/cbtell-a <メッセージ>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbtitle-a <walk|fly> <0~10>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbsubtitle-a <メニュー名>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbback <enable|disable>%s", linebegin, lineend);
					//
					helpmes += RETACS(3);
					break;
				default:
					sender.sendMessage(prefix + "ページは1～3までしかありません！");
					return true;
			}
			sender.sendMessage(helpmes);
			return true;
		} else if (cmdname.equalsIgnoreCase("cbwarp")) {
			setCB(args, 0, args.length, sender, String.format("warp %s",args[0]));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbgm")) {
			if (args.length == 0) {
				sendmes(sender,TOO_FEW_ARGS);
				return true;
			}
			switch (args[0]) { // 1.12以降の対策
				case "0":
				case "s":
					args[0] = "survival";
					break;
				case "1":
				case "c":
					args[0] = "creative";
					break;
				case "2":
				case "a":
					args[0] = "adventure";
					break;
				case "3":
				case "sp":
					args[0] = "spectator";
					break;
				default:
					sendmes(sender,"不明なゲームモードです。");
					return true;
			}
			
			if (DEBUG) {
				sender.sendMessage(prefix + "GAMEMODE: " + args[0]);
			}
			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
				} else {
					setCB(args, 1, args.length, sender, String.format("minecraft:gamemode %s %s",args[0],DEFAULT_SELECTER));
					return true;
				}
			} else {
				sendmes(sender,MUST_BE_PLAYER);
				return true;
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbtp")) {
			/*if (args.length < 3) {
				sendmes(sender,TOO_FEW_ARGS);
				return true;
			}
			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
				} else {
					changesCB(player.getLocation(), String.format("minecraft:tp %s %s %s %s",DEFAULT_SELECTER,args[0],args[1],args[2]),player.getName());
					return true;
				}
			} else {
				sendmes(sender,MUST_BE_PLAYER);
				return true;
			}
			*/
			setCB(args, 3, args.length, sender, String.format("minecraft:tp %s %s %s %s",DEFAULT_SELECTER,args[0],args[1],args[2]));
			return true;	
		} else if (cmdname.equalsIgnoreCase("cbgive")) {
			String setCommand = "minecraft:give "+DEFAULT_SELECTER;
			if (args.length < 1) {
				sendmes(sender,TOO_FEW_ARGS);
				return true;
			}
			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
					return true;
				} else {
					for (int i=0;i <= args.length-1;i++) {
						setCommand += (" " + args[i]);
					}
					setCB(args, 1, args.length, sender, setCommand);
					//changesCB(player.getLocation(), setCommand.trim(),player.getName());
					return true;
				}
			} else {
				sendmes(sender,MUST_BE_PLAYER);
				return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbtell")) {
			String tellMes = String.join(" ",args); // スペースで切れる対処
			String newTellMes = "";
			boolean begins = false;
			if (tellMes.contains("&")){  //装飾コードが含まれていたなら書き換え
				newTellMes = ChatColor.translateAlternateColorCodes('&',tellMes);
				args[0] = newTellMes;
			}
			setCB(args,1,3,sender,String.format("/minecraft:tell %s %s",DEFAULT_SELECTER,(newTellMes==null ? tellMes : newTellMes)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbsound")) {
			String setCommand = "minecraft:playsound $1 $2 $3"; // $1 = src/ $2=master/ $3 = DEFAULT_SELECTER
			if (args.length < 1) {
				sendmes(sender,TOO_FEW_ARGS);
				return true;
			}
			
			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
					return true;
				} else {
					if (DEBUG) sendmes(sender,String.format("SOUND: %s",args[0]));
					for (int i=1;i <= args.length-1;i++) {
						//TODO: Later 1.8 and before 1.7.10
						setCommand += (" " + args[i]);

					}
					getLogger().info(setCommand);
					setCommand = setCommand.replace("$1",args[0]);
					setCommand = setCommand.replace("$2","master");
					setCommand = setCommand.replace("$3",DEFAULT_SELECTER);
					setCB(args, 1, args.length, sender, setCommand);
					return true;
				}
			} else {
				sendmes(sender,MUST_BE_PLAYER);
				return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbtitle")) {
			setCB(args,0,2,sender,String.format("/minecraft:title %s title %s",DEFAULT_SELECTER,String.join(" ", args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbsubtitle")) {
			setCB(args,0,2,sender,String.format("/minecraft:title %s subtitle %s",DEFAULT_SELECTER,String.join(" ", args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbeffect")) {
			String setCommand = "minecraft:effect "+DEFAULT_SELECTER;
			if (args.length < 1) {
				sendmes(sender,TOO_FEW_ARGS);
				return true;
			}
			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
					return true;
				} else {
					for (int i=0;i <= args.length-1;i++) {
						setCommand += (" " + args[i]);
					}
					setCB(args,0,3,sender,setCommand);
					//changesCB(player.getLocation(), setCommand.trim(),player.getName());
					return true;
				}
			} else {
				sendmes(sender,MUST_BE_PLAYER);
				return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbxp")) {
			setCB(args,0,2,sender,String.format("/minecraft:xp %s %s",args[0],DEFAULT_SELECTER));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbspeed")) {
			setCB(args,0,3,sender,String.format("/speed %s %s",args[0],args[1]));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbmenu")) {
			if (getServer().getPluginManager().isPluginEnabled("mymenu")) {
				setCB(args,0,2,sender,String.format("/mmopen %s %s",args[0],DEFAULT_SELECTER));
			} else {
				sendmes(sender, notEnabledPL("mymenu"));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbgod")) {
			if (enabled("essentials")) {
				setCB(args,0,2,sender,String.format("/god %s",args[0]));
			} else {
				sendmes(sender,notEnabledPL("essentials"));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbfly")) {
			if (enabled("essentials")) {
				setCB(args,0,2,sender,String.format("/fly %s",args[0]));
			} else {
				sendmes(sender,notEnabledPL("essentials"));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbtpt")) {
			setCB(args,0,2,sender,String.format("/tpt %s",args[0]));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbmusic")) {
			setCB(args,0,2,sender,String.format("/music play %s %s",args[0],DEFAULT_SELECTER));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbshot")) {
			setCB(args,0,2,sender,String.format("/shot give %s %s",DEFAULT_SELECTER,args[0]));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbtell-a")) {
			setCB(args,0,2,sender,String.format("/minecraft:tell %s %s",ALL_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbtitle-a")) {
			setCB(args,0,2,sender,String.format("/minecraft:title %s title %s",ALL_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbsubtitle-a")) {
			setCB(args,0,2,sender,String.format("/minecraft:title %s subtitle %s",DEFAULT_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cmd")) {
			sendmes(sender,"もしかして: cmb");
			return true;
		} else if (cmdname.equalsIgnoreCase("cmb")) {
			if (player==null) {
				sendmes(sender,MUST_BE_PLAYER);
				return true;
			}
			//WORLD GUARD CHECK
			if (DEBUG) {
				getLogger().info(String.format("%3.2f,%d",player.getLocation().getY(),player.getLocation().getBlockY()));
			}			
			Location loc = player.getLocation();
			loc.setY(loc.getY()-1);
			if (DEBUG) {
				getLogger().info(String.format("%s",player.getLocation().getBlockY()));
			}
			if (getWorldGuard() == null) {
				sendmes(sender,notEnabledPL("WorldGuard"));
				return true;
			} else if (getWorldGuard().canBuild(player, player.getLocation()) != true) {
				sender.sendMessage(CLAIMED);
				return true;
			}
			//CHECK SPAWN PROTECTION
			int sprad = getServer().getSpawnRadius();
			double px = loc.getX();
			double py = loc.getY();
			double pz = loc.getZ();
			Location spawnCenter = loc.getWorld().getSpawnLocation();
			double sx = spawnCenter.getX();
			double sz = spawnCenter.getZ();
			BlockVector min = new BlockVector(sx-sprad,0,sz-sprad);
			BlockVector max = new BlockVector(sx+sprad,255,sz+sprad);
			if (min.getX() > px || max.getX() < px || min.getZ() > pz || min.getZ() < pz) { //ABLE
				player.getLocation().getBlock().setType(Material.COMMAND);
				getCoreProtect().logPlacement(player.getName(), player.getLocation(), Material.COMMAND, (byte)0); //CORE PROTECT
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("uncmd")) {
			sendmes(sender,"もしかして: cmb");
			return true;
		} else if (cmdname.equalsIgnoreCase("uncmb")) {
			getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:tp "+ pa +"~ ~-1 ~");
			if (player==null) {
				sendmes(sender,MUST_BE_PLAYER);
				return true;
			}
			//WORLD GUARD CHECK
			if (getWorldGuard() == null) {
				sendmes(sender,notEnabledPL("WorldGuard"));
				return true;
			}
			
			if (getWorldGuard().canBuild(player, player.getLocation()) != true) {
				sender.sendMessage(CLAIMED);
				return true;
			}
			//CHECK SPAWN PROTECTION
			int sprad = getServer().getSpawnRadius();
			double px = player.getLocation().getX();
			double py = player.getLocation().getY();
			double pz = player.getLocation().getZ();
			BlockVector min = new BlockVector(px-sprad,py,pz-sprad);
			BlockVector max = new BlockVector(px+sprad,py,pz+sprad);
			if (player.getLocation().getBlock().getType() != Material.COMMAND) {
				sender.sendMessage("コマンドブロックではありません");
				return true;
			}
			if (min.getX() > px || max.getX() < px || min.getZ() > pz || min.getZ() < pz) { //ABLE
				player.getLocation().getBlock().setType(Material.AIR);
				getCoreProtect().logRemoval(player.getName(), player.getLocation(), Material.COMMAND, (byte)0); 			//CORE PROTECT
			}
			return true;



		} else if (cmdname.equalsIgnoreCase("cbwarp")) {
			if (enabled("essentials")) {
				setCB(args,0,2,sender,String.format("/warp %s %s",args[0],DEFAULT_SELECTER));
			} else {
				sendmes(sender,notEnabledPL("essentials"));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbactionbar")) {
			setCB(args,0,2,sender,String.format("minecraft:title %s actionbar %s",DEFAULT_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbactionbar-a")) {
			setCB(args,0,2,sender,String.format("minecraft:title %s actionbar %s",ALL_SELECTER,String.join(" ",args)));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbback")) {
			setCB(args,0,2,sender,"/nine_cbs:back");
			return true;	
		} else if (cmdname.equalsIgnoreCase("back")) {
			executeCommand(String.format("minecraft:tp %s %d %d %d",pa,deathLocation.get(pa).getBlockX(),deathLocation.get(pa).getBlockY(),deathLocation.get(pa).getBlockZ()));
			return true;
		}
		/**/
		return false;//該当コマンドなし
	}

	/**
	 *
	 * @param args
	 */

	private void setCB(String args[], int toofew, int toomany, CommandSender sender, String command){
		boolean isPlayer = sender instanceof Player;
		Player player = null;
		if (isPlayer) {
			player = (Player)sender;
		}
		if (args.length < toofew) {
			sendmes(sender,TOO_FEW_ARGS);
			return;
		}

		if (args.length > toomany) {
			sendmes(sender,TOO_MANY_ARGS);
		}
		if (isPlayer) {
			if (player == null) {
				sendmes(sender,"NULL: player");
			} else {
				changesCB(player.getLocation(), command,player.getName());
				logging(player.getLocation(), "".equals(getsCB(player.getLocation())) ? "input" : "change", command, player.getName());
				return;
			}
		} else {
			sendmes(sender,MUST_BE_PLAYER);
			return;
		}
	}
	
	private String getsCB(Location blockLocate) {
		if (blockLocate.getBlock().getType() != Material.COMMAND) {
			return "";
		}
		CommandBlock cb = (CommandBlock)blockLocate.getBlock().getState();
		return cb.getCommand();
	}

	private static String RETACS(int page){
		return ChatColor.AQUA+"=== "+ChatColor.DARK_GREEN+ChatColor.BOLD+"cbhelp "+ChatColor.GOLD+"-- "+ChatColor.GREEN+ChatColor.BOLD+"ページ "+ChatColor.RED+String.format("%d",page)+ChatColor.GOLD+"/"+ChatColor.RED+String.format("%d ",CBHELP_MAXPAGE)+ChatColor.DARK_AQUA+"("+ChatColor.RESET+ChatColor.UNDERLINE+"/cbhelp 2"+ChatColor.RESET+" で次のページへ"+ChatColor.DARK_AQUA+") "+ChatColor.AQUA+" ===";
	}

	private void changesCB(Location blockLocate, String command, String playerName) {
		blockLocate.setY(blockLocate.getY()-1);
		if (blockLocate.getBlock().getType() == Material.COMMAND) {
			CommandBlock cb = (CommandBlock)blockLocate.getBlock().getState();
			cb.setCommand(command); // コマンドをセット
			cb.update(true); // 反映させる
			logging(blockLocate,"change",command,playerName);
		}
	}
	
	private void logging(Location blockLocate, String action, String desc, String player) {
		try {
		/*
		case "log":
		if (player != null) {
		logging(player.getLocation(),"test","minecraft:tell @p this is testing (really test!!!!!!!)");	<--- ERROR					
		}
		sendmes(sender,"There is no logs.");
		return true;
		*/
			final String BASE_PATH = "plugins/ninecb/blocklog/";
			final String FILE_NAME = String.format("%d_%d_%d.yml",blockLocate.getBlockX(),(int)(blockLocate.getY()-1),(int)blockLocate.getZ());
			File newdir = new File(BASE_PATH);
			if (newdir.exists()) {
				NOP();
			} else {
				newdir.mkdir();
			}
			File f = new File(BASE_PATH + FILE_NAME);
			YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
					int i;
					//if (yamlFile.get("log0") == null) {yamlFile.createSection("log0");}
					for (i=0;i <= 999999;i ++) {
						if (yamlFile.get(String.format("log%d",i)) == null) {break;}
					}
					HashMap<String,String> properties = new HashMap<>();
					Long ms = System.currentTimeMillis();
					
					properties.put("time",ms.toString());
					properties.put("action",action);
					properties.put("after", desc);
					properties.put("player",player);

					yamlFile.set(String.format("log%d",i),properties);
			try {
				getLogger().log(Level.INFO,"TRYING SAVE TO " + BASE_PATH + "{0}", FILE_NAME);
				yamlFile.save(BASE_PATH + FILE_NAME);
			} catch (IOException e) {
				getLogger().warning("FAILED SAVING FILE");
			}
			
		} catch (RuntimeException e) {
			getLogger().warning("ERROR OCUSED : RUNTIME EXEPTION");
			e.printStackTrace();
		}
	}
	private boolean enabled(String plname) {
		return getServer().getPluginManager().getPlugin("essentials") != null;
	}
	private void sendmes(CommandSender sender, String mes) {
		sender.sendMessage(prefix + mes);
	}
	private String notEnabledPL(String plname) {
		return String.format("%s は有効化されていないようです。\n管理者へお問い合わせください。",plname);
	}
	private void executeCommand(String cmd) {
		getServer().dispatchCommand(getServer().getConsoleSender(), cmd);
	}
	/*
	 * @deprecated you can use ChatColor.translateAlternateColorCodes('&',mes)
	 */


	private WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null; // Maybe you want throw an exception instead
		}

		return (WorldGuardPlugin) plugin;
	}
	
	private CoreProtectAPI getCoreProtect() {
		Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");

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
	private static String ChatColorBuilder(String mes){ //mes is like "&8&m cake is a lie"
		String ret = "";
		return ChatColor.translateAlternateColorCodes('&',mes);
	} // end



	private void NOP() {
		// No Operation
	}
	

	/*********************************************************************************************
	 *      文字列の指定された位置から、指定された文字数分の文字列を返します。
	 *
	 * @param   stTarget    取り出す元になる文字列。
	 * @param   iStart      取り出しを開始する位置。
	 * @param   iLength     取り出す文字数。
	 * @return	      指定された位置から指定された文字数分の文字列。
	 *		      文字数を超えた場合は、指定された位置以降のすべての文字列が返されます。
	 *********************************************************************************************/
	public static final String mid(final String stTarget, final int iStart, final int iLength) {
		if (iStart + iLength < stTarget.length()) { // スタート位置+長さ < 文字列の長さ
			return "";
		}
		return stTarget.substring(iStart, iStart + iLength);
	}
	
	/*public static final void sendunavaiable(CommandSender sendto) {
		sendto.SendMessage(prefix + " 未実装");
	}*/
}

