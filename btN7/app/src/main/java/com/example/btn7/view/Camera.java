package com.example.btn7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.btn7.R;

import java.io.IOException;

public class Camera extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        img=findViewById(R.id.img);
        String uri=getIntent().getStringExtra("image");
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        img.setImageBitmap(bitmap);
    }
}