package me.sellect.healthbar;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public final class HealthBar extends JavaPlugin {

    private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        // UpdateChecker
        new UpdateChecker(this, 85218).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                getLogger().info("There is not a new update available.");
            }else{
                getLogger().info("There is a new update available.");
            }
        });

        // Metrics
        Metrics metrics = new Metrics(this, 9217);

        scoreboard = getServer().getScoreboardManager().getMainScoreboard();
        registerHealthBar();
    }

    public void registerHealthBar() {
        if (scoreboard.getObjective("health") != null) {
            scoreboard.getObjective("health").unregister();
        }
        Objective objective = scoreboard.registerNewObjective("health", "health");
        objective.setDisplayName(ChatColor.RED + "❤");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }
}
