package com.dayo.testgame

import me.ddayo.coroutine.Coroutine
import me.ddayo.coroutine.functions.WaitNextTick
import me.ddayo.simplegameapi.api.Game
import me.ddayo.simplegameapi.data.GameManager
import me.ddayo.simplegameapi.data.RoomInfo
import me.ddayo.simplegameapi.util.CoroutineUtil
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask
import java.util.*

class G : Game {
    private lateinit var task: BukkitTask
    override val playerCount: Int
        get() = 1
    override val name: String
        get() = "asdf"
    override val maxPlayerCount: Int
        get() = 2

    lateinit var p: UUID

    constructor(a: Int): super() {
        println(a)
    }

    constructor(): super()

    override fun onGameStart(players: List<UUID>) {
        //TestGame.instance!!.server.getPlayer(players[0])!!.sendMessage("Hello, world!")
        players.forEach { TestGame.instance!!.server.getPlayer(it)!!.sendMessage(room.rid.toString()) }
        println("t")
        println(GameManager.getPlaying(players[0])?.rid)
        println(players[0])
        p = players[0]
        Coroutine.startCoroutine(sequence {
            while(true) {
                if (TestGame.stop) {
                    TestGame.stop = false
                    TestGame.instance!!.server.getPlayer(p)!!.sendMessage("Stop game!")
                    playerFailed(p)
                    return@sequence
                }
                yield(WaitNextTick())
            }
        })
    }

    override fun playerFailed(player: UUID) {
        TestGame.instance!!.server.getPlayer(player)!!.sendMessage("You Failed")
        super.playerFailed(player)
    }

    override fun finish() {
        TestGame.instance!!.server.broadcastMessage("Finish game")
        super.finish()
        CoroutineUtil.invokeMain {
            println(GameManager.getRoomStatus(room))
        }
    }
}