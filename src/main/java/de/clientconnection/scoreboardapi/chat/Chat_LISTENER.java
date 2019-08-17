package de.clientconnection.scoreboardapi.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.clientconnection.scoreboardapi.ScoreboardAPI;
import de.clientconnection.scoreboardapi.scoreboard.ScoreboardGroup;

/**
* @Author ClientConnection
* @Project scoreboardapi
* @Package de.clientconnection.scoreboardapi.chat
* @Date 17.08.2019
* @Time 13:06:47
*/
public class Chat_LISTENER implements Listener {

	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		if(ScoreboardAPI.getAPI().isScoreboardUpdateRunning()) {
			ScoreboardAPI.getAPI().getScoreboardManagerOf(e.getPlayer()).setScoreboard();
			ScoreboardAPI.getAPI().getScoreboardManagerOf(e.getPlayer()).setIsScoreboard(true);
		}
	}
	
	@EventHandler
	public void onQuit(final PlayerQuitEvent e) {
		ScoreboardAPI.getAPI().removeScoreboardManagerOf(e.getPlayer());
	}
	
	@EventHandler
	public void onChat(final AsyncPlayerChatEvent e) {
		final Player player = e.getPlayer();
		final ScoreboardGroup group = ScoreboardGroup.getGroupOfPlayer(player);
		final String message = group.getChatColor() + e.getMessage().replaceAll("%", "%%");
		final String format = group.getChatPrefix() + player.getName();
		e.setFormat(format + " §8> " + message);
	}
}
