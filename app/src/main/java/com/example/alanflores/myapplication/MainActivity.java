package com.example.alanflores.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button buttonCamara;
    int IDRESURLT = 18;
    private ImageView imageView;

    private static final int RECEIVE_SMS = 100;
    private static final int BROADCAST_SMS = 200;
    private static final int READ_SMS = 300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_SMS,
                    android.Manifest.permission.BROADCAST_SMS,
                    android.Manifest.permission.RECEIVE_SMS}, READ_SMS);

        /*if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_SMS);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_SMS}, READ_SMS);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.BROADCAST_SMS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.BROADCAST_SMS}, BROADCAST_SMS);

        if (ActivityCompat.checkSelfPermission(MainActivity.this,android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.RECEIVE_SMS}, RECEIVE_SMS);*/

        buttonCamara = (Button)findViewById(R.id.button_foto);

        imageView = (ImageView)findViewById(R.id.image);

        buttonCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,IDRESURLT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(IDRESURLT == requestCode){
            if (resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RECEIVE_SMS: {
                Log.v("RECEIVE_SMS",Integer.toString(RECEIVE_SMS));
                break;
            }
            case BROADCAST_SMS: {
                Log.v("BROADCAST_SMS",Integer.toString(BROADCAST_SMS));
                break;
            }
            case READ_SMS: {
                Log.v("READ_SMS",Integer.toString(READ_SMS));
                break;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
