package com.example.btn7.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.example.btn7.R;

public class App16 extends AppCompatActivity implements View.OnClickListener {

    private static int REQUEST_CODE_CAMERA = 9999;
    private Button btnCamera, btnRecorder, btnPlayMusicOnline, btnPlayMusicOffLine, btnPlayViewOnline, btnPlayViewOffLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app16);
        initView();
        initEvent();
        //repeat - shuffle
    }

    private void initEvent() {
        btnCamera.setOnClickListener(this);
        btnRecorder.setOnClickListener(this);
        btnPlayMusicOffLine.setOnClickListener(this);
        btnPlayMusicOnline.setOnClickListener(this);
        btnPlayViewOffLine.setOnClickListener(this);
        btnPlayViewOnline.setOnClickListener(this);
    }

    private void initView() {
        btnCamera = findViewById(R.id.btnCamera);
        btnRecorder = findViewById(R.id.btnRecorder);
        btnPlayMusicOffLine = findViewById(R.id.btnPlayMusicLocal);
        btnPlayMusicOnline = findViewById(R.id.btnPlayMusicOnline);
        btnPlayViewOffLine = findViewById(R.id.btnPlayVideoLocal);
        btnPlayViewOnline = findViewById(R.id.btnPlayVideoOnline);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnCamera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA);
                break;
            case R.id.btnRecorder:
                intent = new Intent(App16.this, RecorderAudio.class);
                startActivity(intent);
                break;
            case R.id.btnPlayMusicLocal:
                intent = new Intent(App16.this, PlayMusic.class);
                startActivity(intent);
                break;
            case R.id.btnPlayVideoLocal:
                intent = new Intent(App16.this, PlayVideo.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap)data.getExtras().get("data") ;
            String uri = getImageUri(getApplicationContext(),bitmap).toString();
            Intent intent = new Intent(App16.this, Camera.class);
            intent.putExtra("image", uri);
            startActivity(intent);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        String path =
                MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage,
                        "Title", null);
        return Uri.parse(path);
    }
}