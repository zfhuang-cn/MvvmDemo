package com.bird.demo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bird.android.annotation.PermissionNeed;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    @PermissionNeed(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private void test() {
        Log.d(TAG, "test() called");
    }
}