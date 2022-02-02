package com.dayo.testgame;

import com.dayo.simplegameapi.api.Game;
import com.dayo.simplegameapi.data.RoomInfo;
import com.dayo.simplegameapi.event.PlayerFailEvent;

import java.util.List;
import java.util.UUID;

public class G extends Game {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getPlayerCount() {
        return 1;
    }

    @Override
    public String getName() {
        return "asdf";
    }

    @Override
    public void onGameStart(RoomInfo roomInfo, List<UUID> list) {
        TestGame.instance.getServer().getPlayer(list.get(0)).sendMessage("Hello, world!");
        new Thread(() -> {
            if(TestGame.stop) {
                TestGame.stop = false;
                onPlayerFailed(roomInfo, list.get(0));
            }
        }).start();
    }

    @Override
    public void onPlayerFailed(RoomInfo room, UUID player) {
        TestGame.instance.getServer().getPlayer(player).sendMessage("You Failed");
        super.onPlayerFailed(room, player);
    }

    @Override
    public void finish(RoomInfo room) {
        TestGame.instance.getServer().broadcastMessage("Finish game");
        super.finish(room);
    }
}
