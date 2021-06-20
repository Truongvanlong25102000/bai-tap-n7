package com.example.btn7.model;

import android.net.Uri;

import java.io.Serializable;

public class ModelAudio implements Serializable {
    String audioTitle;
    String audioDuration;
    String audioArtist;
    Uri audioUri;

    public ModelAudio() {
    }

    public ModelAudio(String audioTitle, String audioDuration, String audioArtist, Uri audioUri) {
        this.audioTitle = audioTitle;
        this.audioDuration = audioDuration;
        this.audioArtist = audioArtist;
        this.audioUri = audioUri;
    }

    public String getaudioTitle() {
        return audioTitle;
    }

    public void setaudioTitle(String audioTitle) {
        this.audioTitle = audioTitle;
    }

    public String getaudioDuration() {
        return audioDuration;
    }

    public void setaudioDuration(String audioDuration) {
        this.audioDuration = audioDuration;
    }

    public String getaudioArtist() {
        return audioArtist;
    }

    public void setaudioArtist(String audioArtist) {
        this.audioArtist = audioArtist;
    }

    public Uri getaudioUri() {
        return audioUri;
    }

    public void setaudioUri(Uri audioUri) {
        this.audioUri = audioUri;
    }

}
