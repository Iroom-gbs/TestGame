package com.dayo.testgame;

import com.dayo.simplegameapi.data.GameManager;
import com.dayo.simplegameapi.data.RoomInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestGame extends JavaPlugin {

    public static TestGame instance;
    @Override
    public void onEnable() {
        instance = this;
        GameManager.Companion.registerGame(new G(), 1);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("j")) {
            GameManager.Companion.joinPlayer(((Player)sender).getUniqueId(), new RoomInfo(0, 0));
        }
        else if(command.getName().equals("l")) {
            GameManager.Companion.leftPlayer(((Player)sender).getUniqueId());
        }
        return true;
    }
}
