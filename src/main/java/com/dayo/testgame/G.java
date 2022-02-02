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
    public int getMinimumPlayer() {
        return 1;
    }

    @Override
    public String getName() {
        return "asdf";
    }

    @Override
    public void onGameStart(RoomInfo roomInfo, List<UUID> list) {
        TestGame.instance.getServer().getPlayer(list.get(0)).sendMessage("Hello, world!");
    }
}
