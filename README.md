# Simple-ScoreboardAPI
this is an example Plugin, how to use the ScoreboardAPI.


## Code Example
Use for example: 
```java
  public void initScoreboard() {
    ScoreboardAPI.getAPI().setScoreboardCreation(new ScoreboardCreation() {
    //SET SCOREBOARD
			@Override
			public void setScoreboard(Scoreboard sb, Objective obj, Player player) {
				obj.setDisplayName("§aScoreboardT");
        
				final Team team = sb.registerNewTeam("coins");//Creating a Team to update the scoreboard without flacking 
				team.setPrefix("§aCoins: ");//The prefix wont be change
				team.setSuffix("§e0");//The coins value
				team.addEntry(ChatColor.DARK_AQUA.toString());
				obj.getScore("§a§6").setScore(2);//Farbcodes gets translated to spacer
				obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(1);//Sets the value for Coins
				obj.getScore("§4§1").setScore(0);//Farbcodes gets also translated to spacer 
        
        //At least you dont need to set the Scoreboard, its already done by the api
			}
		//UPDATE SCOREBOARD
			@Override
			public void updateScoreboard(Scoreboard sb, Objective obj, Player player) {
				final Team team = sb.getTeam("coins");//Gets the team, checking for null is not needed 
				final Random rdm = new Random();
				team.setSuffix(rdm.nextInt(10) + ""); //Sets a random Coins value (at that point, you can choose ur own value)
				team.addEntry(ChatColor.DARK_AQUA.toString());//Sets the identity color of the coins team 
				obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(1);//Sets the Scorevalue of the coinsteam in scoreboard
			}
		});
    //The Adapter is for checking the Group of a Player
    //At that Example its used with the API of PermissionsEX
    ScoreboardAPI.getAPI().setAdapter(new ScoreboardGroupPlayerAdapter() {
			@Override
			public ScoreboardGroup getGroupOfPlayer(Player p) {
				return PermissionsEx.getUser(p).inGroup("admin") ? 
        ScoreboardAPI.getAPI().getScoreboardGroup("admin") : 
        ScoreboardAPI.getAPI().getScoreboardGroup("spieler");
			}
		});
    
    //Managing the Groups
    ScoreboardAPI.getAPI().getScoreboardGroups().clear();//Clearing all Groups
    //Creating the Admin Group
		final ScoreboardGroup admin = 
    new ScoreboardGroup("admin", "000", "§cAdmin §8| §c", "§cAdministrator §8| §c",   "§cAdministrator §8| §c", "§c");
		
    //Creating the Player Group
    final ScoreboardGroup player = 
    new ScoreboardGroup("spieler", "999", "§7", "§7Spieler §8| §7", "§7Spieler §8| §7", "§7");
    //Adding Groups to internal Map, just use the addGroup method, cuz of the writing of groups
    ScoreboardAPI.getAPI().addGroup(admin);
		ScoreboardAPI.getAPI().addGroup(player);
    //At least for setting scoreboards, use:
		ScoreboardAPI.getAPI().startUpdatingScoreboard();
  }
```
