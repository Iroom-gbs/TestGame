package com.dayo.testgame

import me.ddayo.simplegameapi.data.GameManager.Companion.registerGame
import me.ddayo.simplegameapi.data.GameManager.Companion.joinPlayer
import me.ddayo.simplegameapi.data.GameManager.Companion.leftPlayer
import me.ddayo.simplegameapi.api.Game
import me.ddayo.simplegameapi.data.RoomInfo
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
        registerGame(G(), 2)
        // Plugin startup logic
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        when (command.name) {
            "j" -> {
                joinPlayer((sender as Player).uniqueId, RoomInfo(0, args[0].toInt()))
            }
            "l" -> {
                leftPlayer((sender as Player).uniqueId)
            }
            "s" -> {
                sender.sendMessage("stop")
                stop = true
            }
        }
        return true
    }

    companion object {
        var instance: TestGame? = null
        var stop = false
    }
}