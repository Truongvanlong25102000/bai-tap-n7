package com.example.btn7.model;

public class MusicModel {
    private String name;
    private int playBackTime;

    public MusicModel(String name, int playBackTime) {
        this.name = name;
        this.playBackTime = playBackTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayBackTime() {
        return playBackTime;
    }

    public void setPlayBackTime(int playBackTime) {
        this.playBackTime = playBackTime;
    }
}
