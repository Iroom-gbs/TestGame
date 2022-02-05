package com.dayo.testgame

import me.ddayo.simplegameapi.api.Game
import me.ddayo.simplegameapi.data.GameManager
import me.ddayo.simplegameapi.data.RoomInfo
import me.ddayo.simplegameapi.util.CoroutineUtil
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask
import java.util.*

class G : Game() {
    private lateinit var task: BukkitTask
    override val playerCount: Int
        get() = 1
    override val name: String
        get() = "asdf"
    override val maxPlayerCount: Int
        get() = 2
    lateinit var p: UUID
    override fun onGameStart(room: RoomInfo, players: List<UUID>) {
        //TestGame.instance!!.server.getPlayer(players[0])!!.sendMessage("Hello, world!")
        players.forEach { TestGame.instance!!.server.getPlayer(it)!!.sendMessage(room.rid.toString()) }
        println("t")
        println(GameManager.getPlaying(players[0])?.rid)
        println(players[0])
        p = players[0]
        task = Bukkit.getScheduler().runTaskTimer(TestGame.instance!!, Runnable {
             if(TestGame.stop) {
                 TestGame.stop = false
                 task.cancel()
                 TestGame.instance!!.server.getPlayer(p)!!.sendMessage("Stop game!")
                 playerFailed(room, p)
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
        CoroutineUtil.invokeMain {
            println(GameManager.getRoomStatus(room))
        }
    }
}