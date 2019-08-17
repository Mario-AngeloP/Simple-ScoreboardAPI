package de.clientconnection.scoreboardapi.scoreboard.creation;

import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

/**
* @Author ClientConnection
* @Project minehype.core
* @Package de.clientconnection.minehype.core.scoreboard
* @Date 12.08.2019
* @Time 00:40:23
*/
public interface ScoreboardCreation {

	/**
	 * updates the Scoreboard
	 * @param scoreboard
	 * @param objective
	 */
	public abstract void updateScoreboard(final Scoreboard scoreboard, final Objective objective);
	
	/**
	 * just set the Scoreboard once!
	 * @param scoreboard
	 * @param objective Don't forget to set the Displayname
	 */
	public abstract void setScoreboard(final Scoreboard scoreboard, final Objective objective);
}
