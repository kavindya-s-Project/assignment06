package com.example.assignment06;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button cameraBtn;
    private Object OutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        cameraBtn = findViewById(R.id.cameraBtn);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,100);

//                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//
//                File filePath = Environment.getExternalStorageDirectory();
//                File dir = new File(filePath.getAbsolutePath() + "/Demo/");
//                dir.mkdir();
//                File file = new File(dir,System.currentTimeMillis() + ".jpg");
//
//                try {
//                    OutputStream = new FileOutputStream(file);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, OutputStream);
//                try {
//                    OutputStream.flush();
//                }catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}