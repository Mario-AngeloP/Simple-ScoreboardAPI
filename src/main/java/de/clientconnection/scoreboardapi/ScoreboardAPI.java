package de.clientconnection.scoreboardapi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import de.clientconnection.scoreboardapi.chat.Chat_LISTENER;
import de.clientconnection.scoreboardapi.scoreboard.ScoreboardGroup;
import de.clientconnection.scoreboardapi.scoreboard.adapter.ScoreboardAPIAdapter;
import de.clientconnection.scoreboardapi.scoreboard.adapter.ScoreboardGroupPlayerAdapter;
import de.clientconnection.scoreboardapi.scoreboard.creation.ScoreboardCreation;
import de.clientconnection.scoreboardapi.scoreboard.creation.ScoreboardManager;

/**
* @Author ClientConnection
* @Project scoreboardapi
* @Package de.clientconnection.scoreboardapi
* @Date 17.08.2019
* @Time 12:12:39
*/
public class ScoreboardAPI extends JavaPlugin {

	private static API api;
	
	public void onEnable() {
		api = new API(this);
		getServer().getPluginManager().registerEvents(new Chat_LISTENER(), this);
	}
	
	public static API getAPI() {
		return api;
	}
	
	public class API {
		
		private final Map<Player, ScoreboardManager> scoreboardManagers = new HashMap<Player, ScoreboardManager>();
		private Map<String, ScoreboardGroup> groups = new HashMap<String, ScoreboardGroup>();
		private ScoreboardGroupPlayerAdapter adapter;
		private ScoreboardCreation creation;
		private boolean isUpdateRunning;
		private final ScoreboardAPI instance;
		
		public API(final ScoreboardAPI instance) {
			this.instance = instance;
			this.adapter = new ScoreboardAPIAdapter();
			final ScoreboardGroup player = new ScoreboardGroup("Spieler", "999", "§7", "§7", "§7", "§f");
			final ScoreboardGroup admin = new ScoreboardGroup("Admin", "000", "§cAdmin §7| §c", "§cAdministrator §7| §c", "§cAdministrator §7| §c", "§c");
			this.groups.put("spieler", player);
			this.groups.put("groups", admin);
			this.creation = new ScoreboardCreation() {
				@Override
				public void updateScoreboard(Scoreboard scoreboard, Objective objective) {
					objective.setDisplayName("§7ScoreboardAPI");
					objective.getScore("§aYou have to").setScore(2);
					objective.getScore("§aoverride the").setScore(1);
					objective.getScore("§aAdapter").setScore(0);
				}
				@Override
				public void setScoreboard(Scoreboard scoreboard, Objective objective) {}
			};
		}
		
		public void startUpdatingScoreboard() {
			if(!isUpdateRunning) {
				this.isUpdateRunning = true;
				Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
					@Override
					public void run() {
						for(final Player all : Bukkit.getOnlinePlayers()) {
							final ScoreboardManager sb = getScoreboardManagerOf(all);
							if(!sb.isScoreboardSet()) {
								sb.setScoreboard();
								sb.setIsScoreboard(true);
							} else 
								sb.updateScoreboard();
						}
					}
				}, 0L, 20L);
			}
		}
		
		public boolean isScoreboardUpdateRunning() {
			return this.isUpdateRunning;
		}
		
		public void setScoreboardCreation(final ScoreboardCreation scoreboardCreation) {
			this.creation = scoreboardCreation;
		}
		
		/**
		 * Returns the ScoreboardCreation
		 */
		public ScoreboardCreation getScoreboardCreation() {
			return this.creation;
		}
		
		/**
		 * Sets the adapter for the check of player group
		 * @param adapter {@link ScoreboardAPIAdapter}
		 */
		public void setAdapter(final ScoreboardGroupPlayerAdapter adapter) {
			this.adapter = adapter;
		}
		
		/**
		 * Returns the adapter fpr the check of player group
		 * @return adapter {@link ScoreboardAPIAdapter}
		 */
		public ScoreboardGroupPlayerAdapter getAdapter() {
			return this.adapter;
		}
		
		/**
		 * Returns the map of the groups
		 * The map is editable
		 * @return adapter Map<String, ScoreboardGroup> groups
		 */
		public Map<String, ScoreboardGroup> getScoreboardGroups() {
			return this.groups;
		}
	
		/**
		 * Returns a given group of the internal map
		 * @param name
		 * @return
		 */
		public ScoreboardGroup getScoreboardGroup(String name) {
			name = name.toLowerCase();
			if(!this.groups.containsKey(name))
				new IllegalArgumentException("The ScoreboardGroup \"" + name + "\" does not exists!");
			else 
				return this.groups.get(name);
			return null;
		}
		
		/**
		 * Returns all groups as a collection
		 * @return
		 */
		public Collection<ScoreboardGroup> getScoreboardGroupsAsCollection() {
			return this.groups.values();
		}
		
		/**
		 * Returns the ScoreboardManager of a Player
		 * @param player
		 * @return
		 */
		public ScoreboardManager getScoreboardManagerOf(final Player player) {
			if(!this.scoreboardManagers.containsKey(player)) {
				final ScoreboardManager manager = new ScoreboardManager(player);
				this.scoreboardManagers.put(player, manager);
				return manager;
			}
			return this.scoreboardManagers.get(player);
		}
		
		public void removeScoreboardManagerOf(final Player player) {
			if(this.scoreboardManagers.containsKey(player)) this.scoreboardManagers.remove(player);
		}
	}
}
