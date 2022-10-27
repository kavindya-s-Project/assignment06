package com.example.assignment06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button cameraBtn;
    private Object OutputStream;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        cameraBtn = findViewById(R.id.cameraBtn);

        cameraBtn = (Button) findViewById(R.id.cameraBtn);
        imageView = (ImageView) findViewById(R.id.imageView);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imagePath = createImage();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Image captured successfully", Toast.LENGTH_SHORT)
                        .show();
                imageView.setImageURI(imageUri);
            }
        }
    }

    private Uri createImage() {
        Uri uri = null;
        ContentResolver resolver = getContentResolver();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);

        } else {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        String imageName = String.valueOf(System.currentTimeMillis());
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, imageName + ".jpg");
        contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + "My Images/");
        Uri finalUri = resolver.insert(uri, contentValues);
        imageUri = finalUri;
        return finalUri;
    }


}