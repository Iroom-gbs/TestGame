package com.dayo.testgame

import com.dayo.simplegameapi.data.GameManager.Companion.registerGame
import com.dayo.simplegameapi.data.GameManager.Companion.joinPlayer
import com.dayo.simplegameapi.data.GameManager.Companion.leftPlayer
import com.dayo.simplegameapi.api.Game
import com.dayo.simplegameapi.data.RoomInfo
import java.util.UUID
import com.dayo.testgame.TestGame
import java.lang.Runnable
import org.bukkit.plugin.java.JavaPlugin
import com.dayo.testgame.G
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestGame : JavaPlugin() {
    override fun onEnable() {
        instance = this
        registerGame(G(), 1)
        // Plugin startup logic
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name == "j") {
            joinPlayer((sender as Player).uniqueId, RoomInfo(0, 0))
        } else if (command.name == "l") {
            leftPlayer((sender as Player).uniqueId)
        } else if (command.name == "s") {
            stop = true
        }
        return true
    }

    companion object {
        var instance: TestGame? = null
        var stop = false
    }
}