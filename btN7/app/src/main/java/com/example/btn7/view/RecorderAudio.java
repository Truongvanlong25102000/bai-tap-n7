package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btn7.ItemMusicHandle;
import com.example.btn7.R;
import com.example.btn7.model.MusicModel;
import com.example.btn7.view.adapter.MusicAdapter;

import java.util.ArrayList;
import java.util.UUID;
/** https://learntodroid.com/how-to-record-audio-in-android-programmatically/ */
public class RecorderAudio extends AppCompatActivity implements View.OnClickListener, ItemMusicHandle {

    ImageView imgMic, imgStop;
    TextView txtTime;
    MediaRecorder mediaRecorder;
    String mFileName;
    ArrayList<MusicModel> musicModels = new ArrayList<>();
    MusicAdapter musicAdapter;
    RecyclerView recyclerView;
    MediaPlayer mediaPlayer;
    ItemMusicHandle itemMusicHandle=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder_audio);
        initView();
        initEvent();
    }

    private void initEvent() {
        imgMic.setOnClickListener(this);
        imgStop.setOnClickListener(this);
    }

    private void initView() {
        imgMic = findViewById(R.id.imgMic);
        imgStop = findViewById(R.id.imgStop);
        txtTime = findViewById(R.id.txtTime);
        txtTime.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.musics);
        musicAdapter = new MusicAdapter(getApplicationContext(), musicModels,itemMusicHandle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(musicAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMic:
                imgMic.setVisibility(View.GONE);
                imgStop.setVisibility(View.VISIBLE);
                startRecorder();
                break;
            case R.id.imgStop:
                Log.d("", "");
                imgMic.setVisibility(View.VISIBLE);
                imgStop.setVisibility(View.GONE);
                stopRecorder();
                break;
        }
    }

    private void stopRecorder() {
        try {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(RecorderAudio.this, mFileName, Toast.LENGTH_SHORT).show();
        musicModels.add(new MusicModel(mFileName, 0));
        musicAdapter.notifyDataSetChanged();
    }

    private void startRecorder() {
        String uuid = UUID.randomUUID().toString();
        mFileName = getExternalCacheDir().getAbsolutePath() + "/" + uuid + ".3gp";
//        mFileName += "/AudioRecording.mp3";
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(mFileName);
        try {
            // below mwthod will prepare
            // our audio recorder class
            mediaRecorder.prepare();

            // start method will start
            // the audio recording.
            mediaRecorder.start();
        } catch (Exception e) {
            Log.e("TAG", "prepare() failed");
        }

    }

    @Override
    public void onSelected(int positionItem) {
        mediaPlayer=new MediaPlayer();
        try {
            // below method is used to set the
            // data source which will be our file name
            mediaPlayer.setDataSource(musicModels.get(positionItem).getName());

            // below method will prepare our media player
            mediaPlayer.prepare();

            // below method will start our media player.
            mediaPlayer.start();
        } catch (Exception e) {
            Log.e("TAG", "prepare() failed");
        }
    }
}