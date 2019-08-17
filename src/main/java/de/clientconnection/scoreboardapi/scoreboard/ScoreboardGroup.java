package de.clientconnection.scoreboardapi.scoreboard;

import org.bukkit.entity.Player;

import de.clientconnection.scoreboardapi.ScoreboardAPI;

/**
* @Author ClientConnection
* @Project scoreboardapi
* @Package de.clientconnection.scoreboardapi.scoreboard
* @Date 17.08.2019
* @Time 12:15:06
*/
public class ScoreboardGroup {

	private final String name, tabTeamName, tabTeamPrefix, playerListName, chatName, chatColor;
	
	public ScoreboardGroup(final String name, final String tabTeamName, final String tabTeamPrefix, final String playerListName, final String chatName, final String chatColor) {
		this.name = name;
		this.tabTeamName = tabTeamName;
		this.tabTeamPrefix = tabTeamPrefix;
		this.playerListName = playerListName;
		this.chatName = chatName;
		this.chatColor = chatColor;
	}

	public static ScoreboardGroup getGroupOfPlayer(final Player player) {
		return ScoreboardAPI.getAPI().getAdapter().getGroupOfPlayer(player);
	}
	
	public String getName() {
		return name;
	}

	public String getTabTeamName() {
		return tabTeamName;
	}
	
	public String getTabTeamPrefix() {
		return tabTeamPrefix;
	}

	public String getPlayerListPrefix() {
		return playerListName;
	}

	public String getChatPrefix() {
		return chatName;
	}

	public String getChatColor() {
		return chatColor;
	}
}
