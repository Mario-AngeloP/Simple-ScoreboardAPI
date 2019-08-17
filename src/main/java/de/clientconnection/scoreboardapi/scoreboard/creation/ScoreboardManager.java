package de.clientconnection.scoreboardapi.scoreboard.creation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.clientconnection.scoreboardapi.ScoreboardAPI;
import de.clientconnection.scoreboardapi.scoreboard.ScoreboardGroup;

/**
* @Author ClientConnection
* @Project minehype.core
* @Package de.clientconnection.minehype.core.scoreboard
* @Date 12.08.2019
* @Time 00:39:44
*/
public class ScoreboardManager {

	private final Player player;
	private boolean scoreboardSet = false;
	
	public ScoreboardManager(final Player player) {
		this.player = player;
	}
	
	/**
	 * Sets the Scoreboard of the Player, but use this method just once!
	 * @param scoreboardCreation
	 */
	public void setScoreboard() {
		final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		final Objective obj = scoreboard.getObjective("aaa") != null ? scoreboard.getObjective("aaa") : scoreboard.registerNewObjective("aaa", "bbb");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		for(ScoreboardGroup group : ScoreboardAPI.getAPI().getScoreboardGroupsAsCollection()){
			getGroupTeam(group, scoreboard);
		}
		setPrefix();
		ScoreboardAPI.getAPI().getScoreboardCreation().setScoreboard(scoreboard, obj);
		player.setScoreboard(scoreboard);
	}
	
	/**
	 * To update scoreboard use this method
	 * The ScoreboardCreation is just for checking the Creation
	 * @param scoreboardUpdate
	 */
	public void updateScoreboard() {
		final Scoreboard scoreboard = player.getScoreboard();
		final Objective obj = scoreboard.getObjective("aaa") != null ? scoreboard.getObjective("aaa") : scoreboard.registerNewObjective("aaa", "bbb");
		setPrefix();
		ScoreboardAPI.getAPI().getScoreboardCreation().updateScoreboard(scoreboard, obj);
	}
	
	public Team getTeam(final String name, final Scoreboard scoreboard) {
		Team team = scoreboard.getTeam(name);
		if(team == null) {
			team = scoreboard.registerNewTeam(name);
		}
		return team;
	}
	
	private Team getGroupTeam(final ScoreboardGroup group, final Scoreboard scoreboard) {
		Team team = scoreboard.getTeam(group.getTabTeamName());
		if(team == null) {
			team = scoreboard.registerNewTeam(group.getTabTeamName());
			team.setPrefix(group.getTabTeamPrefix());
		}
		return team;
	}
	
	private void setPrefix() {
		final ScoreboardGroup group = ScoreboardGroup.getGroupOfPlayer(player);
		for(Player all : Bukkit.getOnlinePlayers()) {
			final ScoreboardGroup targetGroup = ScoreboardGroup.getGroupOfPlayer(all);
			if(!all.equals(player)) {
				final Team team = getGroupTeam(group, all.getScoreboard());
				team.addEntry(player.getName());			
			}
			final Team team = getGroupTeam(targetGroup, player.getScoreboard());
			team.addEntry(all.getName());
		}
		player.setDisplayName(group.getPlayerListPrefix() + player.getName());
		player.setPlayerListName(group.getPlayerListPrefix() + player.getName());
	}
	
	public boolean isScoreboardSet() {
		return this.scoreboardSet;
	}
	
	public void setIsScoreboard(final boolean value) {
		this.scoreboardSet = value;
	}
}
