package com.dayo.testgame

import com.dayo.simplegameapi.api.Game
import com.dayo.simplegameapi.data.RoomInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class G : Game() {
    override val id: Int
        get() = 0
    override val playerCount: Int
        get() = 1
    override val name: String
        get() = "asdf"

    override fun onGameStart(roomInfo: RoomInfo, list: List<UUID>) {
        TestGame.instance!!.server.getPlayer(list[0])!!.sendMessage("Hello, world!")
        CoroutineScope(Dispatchers.Default).launch {
            if (TestGame.stop) {
                TestGame.instance!!.server.getPlayer(list[0])!!.sendMessage("Wa")
                TestGame.stop = false
                CoroutineScope(Dispatchers.Main).launch {
                    onPlayerFailed(roomInfo, list[0])
                }
            }
        }
    }

    override fun onPlayerFailed(room: RoomInfo, player: UUID) {
        TestGame.instance!!.server.getPlayer(player)!!.sendMessage("You Failed")
        super.onPlayerFailed(room, player)
    }

    override fun finish(room: RoomInfo) {
        TestGame.instance!!.server.broadcastMessage("Finish game")
        super.finish(room)
    }
}