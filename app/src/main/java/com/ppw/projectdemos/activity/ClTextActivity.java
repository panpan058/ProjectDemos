package com.ppw.projectdemos.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ppw.projectdemos.R;

import androidx.appcompat.app.AppCompatActivity;

public class ClTextActivity extends AppCompatActivity {
    private TextView mTextView;
    private int count;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl_text);
        mTextView = findViewById(R.id.tv_content);
    }

    public void addText (View view) {
        count++;
        mTextView.append("  text" + count);
    }
}
