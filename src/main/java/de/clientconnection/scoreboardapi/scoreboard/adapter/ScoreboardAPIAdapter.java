package de.clientconnection.scoreboardapi.scoreboard.adapter;

import org.bukkit.entity.Player;

import de.clientconnection.scoreboardapi.ScoreboardAPI;
import de.clientconnection.scoreboardapi.scoreboard.ScoreboardGroup;

/**
* @Author ClientConnection
* @Project scoreboardapi
* @Package de.clientconnection.scoreboardapi.scoreboard.adapter
* @Date 17.08.2019
* @Time 12:35:06
*/
public class ScoreboardAPIAdapter implements ScoreboardGroupPlayerAdapter {

	@Override
	public ScoreboardGroup getGroupOfPlayer(Player player) {
		return player.isOp() ? ScoreboardAPI.getAPI().getScoreboardGroup("admin") : ScoreboardAPI.getAPI().getScoreboardGroup("spieler");
	}
}
