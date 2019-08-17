package de.clientconnection.scoreboardtest;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.clientconnection.scoreboardapi.ScoreboardAPI;
import de.clientconnection.scoreboardapi.scoreboard.ScoreboardGroup;
import de.clientconnection.scoreboardapi.scoreboard.adapter.ScoreboardGroupPlayerAdapter;
import de.clientconnection.scoreboardapi.scoreboard.creation.ScoreboardCreation;
import net.md_5.bungee.api.ChatColor;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
* @Author ClientConnection
* @Project scoreboardtest
* @Package de.clientconnection.scoreboardtest
* @Date 17.08.2019
* @Time 13:12:17
*/
public class ScoreboardTest extends JavaPlugin {

	public void onEnable() {
		ScoreboardAPI.getAPI().setScoreboardCreation(new ScoreboardCreation() {
			@Override
			public void setScoreboard(Scoreboard sb, Objective obj) {
				obj.setDisplayName("§aScoreboardT");
				final Team team = sb.registerNewTeam("sb01");
				team.setPrefix("§aCoins: ");
				team.setSuffix("§e0");
				team.addEntry(ChatColor.DARK_AQUA.toString());
				obj.getScore("§a§6").setScore(2);
				obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(1);
				obj.getScore("§4§1").setScore(0);
			}
			
			@Override
			public void updateScoreboard(Scoreboard sb, Objective obj) {
				final Team team = sb.getTeam("sb01");
				final Random rdm = new Random();
				team.setSuffix(rdm.nextInt(10) + "");
				team.addEntry(ChatColor.DARK_AQUA.toString());
				obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(1);
			}
		});
		ScoreboardAPI.getAPI().setAdapter(new ScoreboardGroupPlayerAdapter() {
			@Override
			public ScoreboardGroup getGroupOfPlayer(Player p) {
				return PermissionsEx.getUser(p).inGroup("admin") ? ScoreboardAPI.getAPI().getScoreboardGroup("admin") : ScoreboardAPI.getAPI().getScoreboardGroup("spieler");
			}
		});
		ScoreboardAPI.getAPI().getScoreboardGroups().clear();
		final ScoreboardGroup admin = new ScoreboardGroup("admin", "000", "§cAdmin §8| §c", "§cAdministrator §8| §c", "§cAdministrator §8| §c", "§c");
		final ScoreboardGroup player = new ScoreboardGroup("spieler", "999", "§7", "§7Spieler §8| §7", "§7Spieler §8| §7", "§7");
		ScoreboardAPI.getAPI().getScoreboardGroups().put("admin", admin);
		ScoreboardAPI.getAPI().getScoreboardGroups().put("spieler", player);
		ScoreboardAPI.getAPI().startUpdatingScoreboard();
	}
}
