package com.dayo.testgame

import com.dayo.simplegameapi.api.Game
import com.dayo.simplegameapi.data.RoomInfo
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask
import java.util.*

class G : Game() {
    lateinit var task: BukkitTask
    override val playerCount: Int
        get() = 1
    override val name: String
        get() = "asdf"

    override fun onGameStart(room: RoomInfo, players: List<UUID>) {
        TestGame.instance!!.server.getPlayer(players[0])!!.sendMessage("Hello, world!")

        task = Bukkit.getScheduler().runTaskTimer(TestGame.instance!!, Runnable {
             if(TestGame.stop) {
                 TestGame.stop = false
                 task.cancel()
                 TestGame.instance!!.server.getPlayer(players[0])!!.sendMessage("Stop game!")
                 playerFailed(room, players[0])
             }
        }, 1L, 1L)
    }

    override fun playerFailed(room: RoomInfo, player: UUID) {
        TestGame.instance!!.server.getPlayer(player)!!.sendMessage("You Failed")
        super.playerFailed(room, player)
    }

    override fun finish(room: RoomInfo) {
        TestGame.instance!!.server.broadcastMessage("Finish game")
        super.finish(room)
    }
}