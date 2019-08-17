package de.clientconnection.scoreboardapi.scoreboard.adapter;

import org.bukkit.entity.Player;

import de.clientconnection.scoreboardapi.scoreboard.ScoreboardGroup;

/**
* @Author ClientConnection
* @Project scoreboardapi
* @Package de.clientconnection.scoreboardapi.scoreboard
* @Date 17.08.2019
* @Time 12:29:26
*/
public interface ScoreboardGroupPlayerAdapter {

	/**
	 * an interface for the check 
	 * @param player Player
	 * @return ScoreboardGroup of the Player
	 */
	public abstract ScoreboardGroup getGroupOfPlayer(final Player player);
	
}
