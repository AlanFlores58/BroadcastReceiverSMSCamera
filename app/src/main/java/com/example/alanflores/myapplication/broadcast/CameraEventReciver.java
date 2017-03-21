package com.example.alanflores.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by alan.flores on 1/18/17.
 */

public class CameraEventReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("New Photo Clicked", ">");
        Cursor cursor = context.getContentResolver().query(
                intent.getData(), null, null, null, null);
        cursor.moveToFirst();
        String image_path = cursor
                .getString(cursor.getColumnIndex("_data"));
        Toast.makeText(context, "New Photo is Saved as : " + image_path,
                Toast.LENGTH_LONG).show();
    }
}
