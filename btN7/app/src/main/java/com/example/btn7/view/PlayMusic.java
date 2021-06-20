package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.btn7.R;
import com.example.btn7.model.AudioAdapter;
import com.example.btn7.model.ModelAudio;
import com.example.btn7.model.MusicModel;
import com.example.btn7.view.adapter.MusicAdapter;

import java.util.ArrayList;
import java.util.Random;

public class PlayMusic extends AppCompatActivity {

    private RecyclerView recyclerViewMusic;
    private ArrayList<ModelAudio> audioArrayList;
    private MediaPlayer mediaPlayer;
    private double current_pos, total_duration;
    private TextView current, total, audio_name;
    private ImageView prev, next, pause, repeat, shuffle;
    private SeekBar seekBar;
    private int audio_index = 0;
    public static final int PERMISSION_READ = 0;
    private AudioAdapter adapter;
    private Boolean isRepeat=false;
    private Boolean isShuffle=false;
    private int indexPlay=-1;
    private Boolean isSelected=false;
    private Random random=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initView();
        getAllMusic();
        initEvent();
    }

    private void initEvent() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                current_pos = seekBar.getProgress();
                mediaPlayer.seekTo((int) current_pos);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                audio_index++;
                if (audio_index < (audioArrayList.size())) {
                    playAudio(audio_index);
                } else {
                    audio_index = 0;
                    playAudio(audio_index);
                }

            }
        });

        if (!audioArrayList.isEmpty()) {
            playAudio(audio_index);
            prevAudio();
            nextAudio();
            setPause();
            repeatAudio();
            shuffleAudio();
        }

        adapter.setOnItemClickListener(new AudioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View v) {
                isSelected=true;
                playAudio(pos);
            }
        });

    }

    //play audio file
    public void playAudio(int pos) {
        if(isSelected){
            isSelected=false;
            indexPlay=pos;
        }
        else if(indexPlay>=0&&isRepeat){
            pos=indexPlay;
        }else if(isShuffle&&indexPlay>=0){
            pos=random.nextInt(audioArrayList.size()-1);
        }else{
            indexPlay=pos;
        }
        try {
            mediaPlayer.reset();
            //set file path
            mediaPlayer.setDataSource(this, audioArrayList.get(pos).getaudioUri());
            mediaPlayer.prepare();
            mediaPlayer.start();
            pause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
            audio_name.setText(audioArrayList.get(pos).getaudioTitle());
            audio_index = pos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAudioProgress();
    }

    //set audio progress
    public void setAudioProgress() {
        //get the audio duration
        current_pos = mediaPlayer.getCurrentPosition();
        total_duration = mediaPlayer.getDuration();

        //display the audio duration
        total.setText(timerConversion((long) total_duration));
        current.setText(timerConversion((long) current_pos));
        seekBar.setMax((int) total_duration);
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    current_pos = mediaPlayer.getCurrentPosition();
                    current.setText(timerConversion((long) current_pos));
                    seekBar.setProgress((int) current_pos);
                    handler.postDelayed(this, 1000);
                } catch (IllegalStateException ed) {
                    ed.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    //play previous audio
    public void prevAudio() {

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audio_index > 0) {
                    audio_index--;
                    indexPlay=audio_index;
                    playAudio(audio_index);
                } else {
                    audio_index = audioArrayList.size() - 1;
                    indexPlay=audio_index;
                    playAudio(audio_index);
                }
            }
        });
    }

    public void repeatAudio(){
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRepeat&&!isShuffle){
                    repeat.setImageResource(R.drawable.ic_baseline_repeat_24_black);
                }else{
                    repeat.setImageResource(R.drawable.ic_baseline_repeat_24);
                }
                shuffle.setImageResource(R.drawable.ic_baseline_shuffle_24_black);
                isRepeat=!isRepeat;
                isShuffle=false;
            }
        });
    }

    public void shuffleAudio(){
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShuffle&&!isRepeat){
                    shuffle.setImageResource(R.drawable.ic_baseline_shuffle_24_black);
                }else{
                    shuffle.setImageResource(R.drawable.ic_baseline_shuffle_24);
                }
                repeat.setImageResource(R.drawable.ic_baseline_repeat_24_black);
                isShuffle=!isShuffle;
                isRepeat=false;
            }
        });
    }

    //play next audio
    public void nextAudio() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audio_index < (audioArrayList.size() - 1)) {
                    audio_index++;
                    indexPlay=audio_index;
                    playAudio(audio_index);
                } else {
                    audio_index = 0;
                    indexPlay=audio_index;
                    playAudio(audio_index);
                }
            }
        });
    }

    //pause audio
    public void setPause() {
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    pause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                } else {
                    mediaPlayer.start();
                    pause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                }
            }
        });
    }

    //time conversion
    public String timerConversion(long value) {
        String audioTime;
        int dur = (int) value;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            audioTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            audioTime = String.format("%02d:%02d", mns, scs);
        }
        return audioTime;
    }

    private void getAllMusic() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        //looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {

                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                ModelAudio modelAudio = new ModelAudio();
                modelAudio.setaudioTitle(title);
                modelAudio.setaudioArtist(artist);
                modelAudio.setaudioUri(Uri.parse(url));
                modelAudio.setaudioDuration(duration);
                audioArrayList.add(modelAudio);

            } while (cursor.moveToNext());
        }

        adapter = new AudioAdapter(this, audioArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewMusic.setLayoutManager(linearLayoutManager);
        recyclerViewMusic.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMusic.setAdapter(adapter);
    }

    private void initView() {
        recyclerViewMusic = findViewById(R.id.recyclerViewMusic);
        audioArrayList = new ArrayList<>();

        current = (TextView) findViewById(R.id.current);
        total = (TextView) findViewById(R.id.total);
        audio_name = (TextView) findViewById(R.id.audio_name);
        prev = (ImageView) findViewById(R.id.prev);
        next = (ImageView) findViewById(R.id.next);
        pause = (ImageView) findViewById(R.id.pause);
        repeat = (ImageView) findViewById(R.id.repeat);
        shuffle = (ImageView) findViewById(R.id.shuffle);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        mediaPlayer = new MediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}