/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hikahikaru17.dev.nine_cbs;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.util.BlockVector;

public class nine_cbs extends JavaPlugin implements CommandExecutor{
	public static HashMap<String,Location> deathLocation = new HashMap<>();
	private final static String prefix = ChatColor.DARK_AQUA + "" + "[Nine_CB] " + ChatColor.RESET;
	private final static String prefixError = ChatColor.RED + "" + "[Nine_CB] " + ChatColor.RESET;
	static final String MUST_BE_PLAYER = "プレイヤーから発動する必要があります。";
	static final String TOO_FEW_ARGS = "引数が少なすぎます";
	static final String TOO_MANY_ARGS = "引数が多すぎます";
	static final int RANGE = 10;
	private final static String COMMAND_TRIGER = "コマンドブロックの上に立って実行するとコマンドが入ります";
	private final static String RANGE10 = ChatColor.GRAY +String.format("(CBから半径%dm以内のプレイヤー全員へ送信)",RANGE)+ChatColor.RESET;
	private final static String DEFAULT_SELECTER = "@p[r=10]";
	private final static String ALL_SELECTER = "@a[r="+RANGE+"]";
	private final static String VERSION = "1.9.3";
	private final static String TRIGGER = String.format("%s===%s %s %s===\n", ChatColor.AQUA, ChatColor.LIGHT_PURPLE, COMMAND_TRIGER, ChatColor.AQUA);
	static final String CLAIMED = "保護されています！";
	static final String NOT_ABLE_MODIFY = "この座標を編集する権限がありません。";
	static final String UNKNOWN_GAMEMODE = "不明なゲームモードです。";
	static final String NOT_CB = "コマンドブロックではありません。";
	private final static int CBHELP_MAXPAGE = 3;
	public final static Boolean DEBUG = true;
	public static InternalAPI API;
	public static ExternalPlugin EP;
	public static Logger LOG;
	public static MCVersion VER;
	public static ExternalPlugin.PluginName PLUGINNAMES;

	public void nine_cbs() {
		EP = new ExternalPlugin();
		API = newAPI();
		LOG = getLogger();
		PLUGINNAMES = EP.NEW();
		VER = new MCVersion();
	}

	@Override
	public void onEnable() {
		this.nine_cbs();
		//LOG.log(Level.INFO, "DEBUG MODE: {0}", (Object)DEBUG);
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
		//getCommand("uncmd").setExecutor(this);
		getCommand("cbactionbar").setExecutor(this);
		getCommand("cbactionbar-a").setExecutor(this);
		getCommand("cbback").setExecutor(this);
		LOG.info("上位互換のFreeCommandBlockが利用できます。詳しくは[https://github.com/lolita-is-godddd/FreeCommandBlock]を参照してください。")
		//this.getServer().getPluginManager().registerEvents(new MyListenerClass(), this));
		//if getServer().getPluginManager.getPlugin("FreeCommandBlock") != null {disable this}
	}

	@Override
	public void onDisable() {
		super.onDisable(); //LOG.info("test disable");
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
			default:
				break;
		}
		if (cmdname.equalsIgnoreCase("ninecb")){
			String sendmes;
			if (args.length < 1) {
				errormes("引数を入力してください！",sender);
				return true;
			}
			switch (args[0].toLowerCase()) {
				case "log":
					if (args.length < 2) {
						showlog(sender);
						return true;
					} else {
						if (to_i(args[1]) < 1) {
							errormes("ログのページ数は1以上でなければいけません。",sender);
							return true;
						}
						showlog(sender,to_i(args[1]));
					}

					return true;
				case "help":
					TextComponent message = new TextComponent("Ping");
					message.setText("Check ping!");
					//message.addExtra(". " + ChatColor.GREEN + "Click to execute a command");
					//message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "ping"));
					//message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Run command: /ping").create()));
					if (player != null) {
						//player.sendMessage(message);
					}
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
			String unavaiable = "" + ChatColor.GRAY + ChatColor.STRIKETHROUGH;
			//String[] hmess = new String[100];

			switch (args[0]) {
				case "1":
					helpmes += TRIGGER;
					helpmes += String.format("%s/cbwarp <ワープ名>%s", (enabled(PLUGINNAMES.ESSENTIALS) ? linebegin : unavaiable), lineend);
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
					helpmes += String.format("%s/cbspeed <walk|fly> <0~10>%s", (enabled(PLUGINNAMES.ESSENTIALS) ? linebegin : unavaiable), lineend);
					helpmes += String.format("%s/cbmenu <メニュー名>%s", (enabled(PLUGINNAMES.MYMENU) ? linebegin : unavaiable), lineend); //mmopen $1 @p[r=10]
					helpmes += String.format("%s/cbgod <enable|disable>%s", (enabled(PLUGINNAMES.ESSENTIALS) ? linebegin : unavaiable), lineend);
					helpmes += String.format("%s/cbfly <enable|disable>%s", (enabled(PLUGINNAMES.ESSENTIALS) ? linebegin : unavaiable), lineend);
					helpmes += String.format("%s/cbtpt <enable|disable>%s", (enabled(PLUGINNAMES.ESSENTIALS) ? linebegin : unavaiable), lineend);
					helpmes += String.format("%s/cbmusic <曲名>%s", (enabled(PLUGINNAMES.ICJUKEBOX) ? linebegin : unavaiable), lineend);
					//
					helpmes += RETACS(2);
					break;
				case "3":
					helpmes += TRIGGER;
					helpmes += String.format("%s/cbshot <名前>%s", (enabled(PLUGINNAMES.CRACKSHOT) ? linebegin : unavaiable) , lineend);
					helpmes += String.format("%s/cbtell-a <メッセージ>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbtitle-a <walk|fly> <0~10>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbsubtitle-a <メニュー名>%s%s", linebegin, RANGE10, lineend);
					helpmes += String.format("%s/cbback <enable|disable>%s", /* linebegin */ unavaiable, lineend);
					helpmes += String.format("%s/cbactionbar <メッセージ>%s",  (VER.isLater(11) ? linebegin : unavaiable), lineend);
					helpmes += String.format("%s/cbactionbar-a <enable|disable>%s", (VER.isLater(11) ? linebegin : unavaiable), lineend);
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
			if (enabled(PLUGINNAMES.ESSENTIALS)) {
				setCB(args, 0, 1, sender, String.format("warp %s %s",args[0],DEFAULT_SELECTER));
			} else {
				errormes(notEnabledPL(PLUGINNAMES.ESSENTIALS),sender);
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbgm")) {
			if (args.length == 0) {
				errormes(TOO_FEW_ARGS,sender);
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
					errormes(UNKNOWN_GAMEMODE,sender);
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
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbtp")) {
			setCB(args, 3, 3, sender, String.format("minecraft:tp %s %s %s %s",DEFAULT_SELECTER,args[0],args[1],args[2]));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbgive")) {
			String setCommand = "minecraft:give "+DEFAULT_SELECTER;
			if (args.length < 1) {
				errormes(TOO_FEW_ARGS,sender);
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
					return true;
				}
			} else {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbtell")) {
			if (player != null && DEBUG) {
				LOG.info(String.format("%f",player.getLocation().getY()));
			}
			String tellMes = String.join(" ",args);
			String setCommand = "minecraft:tell $1 $2";
			if (DEBUG) {
				LOG.log(Level.INFO, "telling message : {0}---[EOF]", tellMes);
			}

			if ("".equals(tellMes) || tellMes == null) {
				errormes("メッセージを入力してください。",sender);
				return true;
			}

			if (tellMes.contains("&")){  //装飾コードが含まれていたなら書き換え
				if (DEBUG) {
					LOG.info("ChatColor was included.");
				}
				tellMes = ChatColor.translateAlternateColorCodes('&',tellMes);
			}
			LOG.info(tellMes);
			LOG.info(String.format("/minecraft:tell %s %s",DEFAULT_SELECTER,tellMes));
			setCommand = setCommand.replace("$1",DEFAULT_SELECTER);
			setCommand = setCommand.replace("$2",tellMes);
			Location lctn;
			if (player != null) {
				lctn = player.getLocation();
			} else {
				sender.sendMessage(MUST_BE_PLAYER);
				return true;
			}
			lctn.setY(lctn.getY()-1);
			setCB(args,1,args.length,sender,setCommand);
			return true;
		} else if (cmdname.equalsIgnoreCase("cbsound")) {
			String setCommand = "minecraft:playsound $1 $2 $3"; // $1 = src/ $2=master/ $3 = DEFAULT_SELECTER
			if (args.length < 1) {
				errormes(TOO_FEW_ARGS,sender);
				return true;
			}

			if (isPlayer) {
				if (player == null) {
					sendmes(sender,"NULL: player");
					return true;
				} else {
					if (DEBUG) {
						sendmes(sender,String.format("SOUND: %s",args[0]));
					}
					for (int i=1;i <= args.length-1;i++) {
						setCommand += (" " + args[i]);
					}
					LOG.info(setCommand);
					setCommand = setCommand.replace("$1",args[0]);
					setCommand = setCommand.replace("$2",VER.isLater(9) ? "master" : ""); // 1.8以前には存在しない！
					setCommand = setCommand.replace("$3",DEFAULT_SELECTER);
					setCB(args, 1, args.length, sender, setCommand);
					return true;
				}
			} else {
				errormes(MUST_BE_PLAYER,sender);
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
				errormes(TOO_FEW_ARGS,sender);
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
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
		} else if (cmdname.equalsIgnoreCase("cbxp")) {
			setCB(args,0,2,sender,String.format("/minecraft:xp %s %s",args[0],DEFAULT_SELECTER));
			return true;
		} else if (cmdname.equalsIgnoreCase("cbspeed")) {
			if (enabled(PLUGINNAMES.ESSENTIALS)) {
				setCB(args,0,2,sender,String.format("/speed %s %s",args[0],args[1]));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbmenu")) {
			if (enabled(PLUGINNAMES.MYMENU)) {
				setCB(args,0,2,sender,String.format("/mmopen %s %s",args[0],DEFAULT_SELECTER));
			} else {
				sendmes(sender, notEnabledPL("mymenu"));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbgod") || cmdname.equalsIgnoreCase("cbfly") || cmdname.equalsIgnoreCase("cbtpt")) {
			if (enabled(PLUGINNAMES.ESSENTIALS)) {
				if (args.length == 0) {
					errormes(TOO_FEW_ARGS,sender);
				}
				if ("enable".equals(args[0].toLowerCase()) || "disable".equals(args[0].toLowerCase())) {
					setCB(args,0,2,sender,String.format("/essentials:%s %s",cmdname.toLowerCase(),args[0]));
				} else {
					errormes("第一引数はenableかdisableにしてください。",sender);
				}
			} else {
				sendmes(sender,notEnabledPL(PLUGINNAMES.ESSENTIALS));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbmusic")) {
			if (enabled(PLUGINNAMES.ICJUKEBOX)) {
				setCB(args,0,2,sender,String.format("/music play %s %s",args[0],DEFAULT_SELECTER));
			} else {
				errormes(notEnabledPL(PLUGINNAMES.ICJUKEBOX),sender);
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbshot")) {
			if (enabled(PLUGINNAMES.CRACKSHOT)) {
				setCB(args,0,2,sender,String.format("/shot give %s %s",DEFAULT_SELECTER,args[0]));
			} else {
				errormes(notEnabledPL(PLUGINNAMES.CRACKSHOT),sender);
			}
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
			sendmes(sender,"/cmbと打ってください。");
			return true;
		} else if (cmdname.equalsIgnoreCase("cmb")) {
			if (player==null) {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
			Location loc = player.getLocation();
			loc.setY(loc.getBlockY()-1); // 足元
			if (DEBUG) {
				LOG.info(String.format("%3.2f,%d",loc.getY(),loc.getBlockY()));
			}
			if (API.canBuild(loc, player)) {
				if (API.isCB(loc)) {
					errormes("すでにコマンドブロックです！",sender);
				}
				loc.getBlock().setType(Material.COMMAND);
				API.log(loc,"place","",player.getName());
				EP.getCoreProtect().logPlacement(player.getName(), loc, Material.COMMAND, (byte)0); //CORE PROTECT
			} else {
				errormes(NOT_ABLE_MODIFY,sender);
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("uncmd")) {
			sendmes(sender,"/uncmbと打ってください。");
			return true;
		} else if (cmdname.equalsIgnoreCase("uncmb")) {
			if (player == null) {
				errormes(MUST_BE_PLAYER,sender);
				return true;
			}
			Location loc = player.getLocation();
			loc.setY(loc.getBlockY()-1); // 足元
			if (API.canBuild(loc, player)) {
				if (API.isCB(loc)) {
					loc.getBlock().setType(Material.AIR);
					API.log(loc,"remove","",player.getName());
					EP.getCoreProtect().logRemoval(player.getName(), loc, Material.COMMAND, (byte)0); //CORE PROTECT
				} else {
					errormes(NOT_CB,sender);
				}
			} else {
				errormes(NOT_ABLE_MODIFY,sender);
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbwarp")) {
			if (enabled(PLUGINNAMES.ESSENTIALS)) {
				setCB(args,0,2,sender,String.format("/warp %s %s",args[0],DEFAULT_SELECTER));
			} else {
				sendmes(sender,notEnabledPL(PLUGINNAMES.ESSENTIALS));
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbactionbar")) {
			if (VER.isLater(11)) {
				setCB(args,0,2,sender,String.format("minecraft:title %s actionbar %s",DEFAULT_SELECTER,String.join(" ",args)));
			} else {
				errormes(moreLater(11),sender);
			}

			return true;
		} else if (cmdname.equalsIgnoreCase("cbactionbar-a")) {
			if (VER.isLater(11)) {
				setCB(args,0,2,sender,String.format("minecraft:title %s actionbar %s",ALL_SELECTER,String.join(" ",args)));
			} else {
				errormes(moreLater(11),sender);
			}
			return true;
		} else if (cmdname.equalsIgnoreCase("cbback")) {
			sender.sendMessage("未サポート");//setCB(args,0,2,sender,"/nine_cbs:back");
			return true;
		} else if (cmdname.equalsIgnoreCase("back")) {
			sender.sendMessage("未サポート");
			return true;
		}
		/**/
		return false;//該当コマンドなし
	}

	private String moreLater(int i) {
		String s = "この機能は1.$1以上のバージョンで対応しています。";
		s = s.replace("$1", to_s(i));
		return s;
	}

	/**
	 *
	 * @param args
	 */
	protected static class InternalAPI {
		public void setCB(String args[], int toofew, int toomany, CommandSender sender, String command){
			//if (DEBUG) LOG.info(String.format("{\"few\":%d,\"many\":%d,\"command\":%s}",toofew,toomany,command));
			boolean isPlayer = isPlayer(sender);
			Player player = null;
			Location loc;
			if (isPlayer) {
				player = (Player)sender;
				loc = player.getLocation();
			} else {
				return;
			}
			if (args.length < toofew) { // 0 < 1
				sendmes(sender,nine_cbs.TOO_FEW_ARGS);
				LOG.info("SETCB@API : CANCELED");
				return;
			}
			if (args.length > toomany) { // 4 > 3
				sendmes(sender,nine_cbs.TOO_MANY_ARGS);
				LOG.info("SETCB@API : CANCELED");
			}
			if (isPlayer) {
				//sendmes(sender,"NULL: player");
				LOG.info("[SETCB@API] "+command);
				loc.setY(loc.getBlockY()-1);
				if (isCB(loc)) {
					CommandBlock cb = (CommandBlock)loc.getBlock().getState();
					cb.setCommand(command);
					cb.update();
					if (DEBUG && ! cb.getCommand().equals(command)) {
						LOG.warning("[SETCB@API] HASNOT SET YET");
					}
					log(loc,"change",command,player.getName()); //FIXME:記録されてない
					sendmes(sender,"ACTION IS DONE");
				} else {
					LOG.info(String.format("%d,%d,%d is not CB.",loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()));
					sendmes(sender,"ACTION IS NOT DONE");
				}
			} else {
				sendmes(sender,nine_cbs.MUST_BE_PLAYER);
			}
		}

		public boolean isPlayer(CommandSender cs) {
			return cs instanceof Player;
		}

		public boolean isCB(Location l) {
			return l.getBlock().getType() == Material.COMMAND;
		}

		public void log(Location l,String act,String c,String p) {
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
				LOG.log(Level.INFO,"[LOG@API] TRYING SAVE TO " + BASE_PATH + "{0}", FILE_NAME);
				yamlFile.save(BASE_PATH + FILE_NAME);
				LOG.info("[LOG@API] SUCCESS SAVE!");
			} catch (IOException e) {
				LOG.warning("[LOG@API] FAILED SAVING FILE!");
			}

		} catch (RuntimeException e) {
			LOG.warning("[LOG@API] ERROR OCUSED : RUNTIME EXEPTION");
		}

		}

		/**
		 * @deprecated
		 * @param l
		 * @param act
		 * @param c
		 * @param i
		 */
		public void log(Location l,String act,String c,UUID i) {
			log(l,act,c,i.toString());
		}

		public boolean canBuild(Location l,Player p) {
			CommandSender sender = p;
			// worldguard
			if (EP.getWorldGuard() != null && EP.getWorldGuard().canBuild(p, p.getLocation()) == false) {
				//sender.sendMessage(nine_cbs.CLAIMED);
				return false;
			}
			/*if (DEBUG) {
				LOG.info(String.format("%s",p.getLocation().getBlockY()));
			}*/
			//CHECK SPAWN PROTECTION
			int range = Bukkit.getServer().getSpawnRadius();
			double px = l.getX();
			double py = l.getY();
			double pz = l.getZ();
			Location spawnCenter = l.getWorld().getSpawnLocation();
			double sx = spawnCenter.getX();
			double sz = spawnCenter.getZ();
			if (DEBUG) {
				LOG.info(String.format("Spawn is %f,%f",sx,sz));
				LOG.info(String.format("Protection Range is %d",range));
			}

			BlockVector min = new BlockVector(sx-range,0,sz-range);
			BlockVector max = new BlockVector(sx+range,255,sz+range);
			if (p.isOp()) { // OPならスポーンプロテクションをスルー
				return true;
			} else {
				if (min.getX() > px && max.getX() < px && min.getZ() > pz && min.getZ() < pz) { //範囲外
					return true;
				} else {
					LOG.info(String.format("%f,%f,%fはスポーンプロテクションの中です",px,py,pz));
					return false;
				}
			}
		}
	} // CLASS END

	public static InternalAPI newAPI() {
		return new InternalAPI();
	}

	private static void setCB(String args[], int toofew, int toomany, CommandSender sender, String command){
		newAPI().setCB(args, toofew, toomany, sender, command);
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

	private void logging(Location blockLocate, String action, String desc, String player) {
		API.log(blockLocate, action, desc, player);
	}

	private boolean enabled(String plname) {
		return getServer().getPluginManager().getPlugin(plname) != null;
	}

	static void sendmes(CommandSender sender, String mes) {
		sender.sendMessage(prefix + mes);
	}

	private String notEnabledPL(String plname) {
		return String.format("%s は有効化されていないようです。\n管理者へお問い合わせください。",plname);
	}

	private void tellRaw(Player player, String str) {
		// nop
	}

	private void executeCommand(String cmd) {
		getServer().dispatchCommand(getServer().getConsoleSender(), cmd);
	}
	/** <!-- javadoc -->
	 * @param mes ストライク！！！！
	 * @deprecated you can use ChatColor.translateAlternateColorCodes('&',mes)
	 */

	private static String ChatColorBuilder(String mes){ //mes is like "&8&m cake is a lie"
		return ChatColor.translateAlternateColorCodes('&',mes);
	}
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

	/** public static final void sendunavaiable(CommandSender sendto) {
		sendto.SendMessage(prefix + " 未実装");
	}*/

	private static void errormes(String s, CommandSender cs) {
		cs.sendMessage(prefixError + s);
	}

	private void showlog(CommandSender sender) {
		showlog(sender,1);
	}

	private void showlog(CommandSender sender, int page) {
		if (page < 1) {
			throw new IllegalArgumentException();
		}
		if (!API.isPlayer(sender)) {
			sender.sendMessage(MUST_BE_PLAYER);
			return;
		}
		Player player = (Player)sender;
		final Location locate = player.getLocation();
		final String BASE_PATH = "plugins/ninecb/blocklog/";
		final String FILE_NAME = String.format("%d_%d_%d.yml",locate.getBlockX(), locate.getBlockY()-1, locate.getBlockZ());
		LOG.info(FILE_NAME);
		final File f = new File(BASE_PATH + FILE_NAME);
		YamlConfiguration yamlFile = YamlConfiguration.loadConfiguration(f);
		String m = "";
		double timesago;
		int i;
		int dispPerPage = 8;
		for (i=0;i <= 999999;i ++) {
			if (yamlFile.get(String.format("log%d",i)) == null) {break;}
		}

		if (i == 0) {
			player.sendMessage("ここにログはありません");
			return;
		}

		if (i < page*dispPerPage) {
			return;
		}
		m += "----"+ " Nine_cb/LOGS " + "----\n";
		Long nowTime = System.currentTimeMillis();
		//LOG.info(to_s(page*dispPerPage));
		for (int j=i-1-page*dispPerPage;j >= i-9-page*dispPerPage || j == 0;j -= 1) {
			Long beforeTime = Long.parseLong(yamlFile.getString(String.format("log%d.time", j)));
			//LOG.info(String.format("[N,B,N-B]%d - %d = %d",nowTime,beforeTime,nowTime-beforeTime));
			timesago = ((nowTime - beforeTime) / (1000*60*60)); //時間単位
			//LOG.info(yamlFile.getString(String.format("log%d.time", j)));
			String l = "";
			l += String.format("%.2f",((nowTime-beforeTime)/(60*60*1000d)));
			//LOG.info(l);
			m += l; // time
			m += "/h ago : ";
			m += yamlFile.getString(String.format("log%d.player", j)); // user
			m += " ";
			m += yamlFile.getString(String.format("log%d.action", j)); // action
			m += " ";
			m += yamlFile.getString(String.format("log%d.after",j)); //command
			m += "\n";
			m += ChatColor.RESET;

		}

		if (i > dispPerPage) {
			m += String.format("%d/%d Page",page,i/dispPerPage); // ページ数
		}
		LOG.info(m);
		sender.sendMessage(m);
	}
	private int to_i(boolean b) {
		return (b == true ? 1 : 0);
	}

	private int to_i(double d) {
		return 0;
	}

	private int to_i(byte b) {
		return b;
	}

	private int to_i(String s){
		return (int)(Long.parseLong(s));
	}

	private static String to_s(int i) {
		return String.format("%d", i);
	}
}

