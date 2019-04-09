package com.ppw.projectdemos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ppw.projectdemos.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void comparedView (View view) {
        goActivity(ComparedViewActivity.class);
    }

    private void goActivity (Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
