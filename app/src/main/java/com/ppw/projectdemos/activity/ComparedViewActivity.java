package com.ppw.projectdemos.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ppw.projectdemos.R;
import com.ppw.projectdemos.view.ComparedView;

import androidx.appcompat.app.AppCompatActivity;

public class ComparedViewActivity extends AppCompatActivity {
    private ComparedView mView;
    private EditText mEditText1;
    private EditText mEditText2;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compared_view);
        mView = findViewById(R.id.cv);
        mEditText1 = findViewById(R.id.edt1);
        mEditText2 = findViewById(R.id.edt2);
    }

    public void show (View view) {
        String edt1 = mEditText1.getText().toString();
        String edt2 = mEditText2.getText().toString();
        try {
            long score1 = Long.parseLong(edt1);
            long score2 = Long.parseLong(edt2);
            mView.setLeftRightScore(score1, score2);
        } catch (Exception e) {
            Toast.makeText(this, "输入数字转化错误,请重新输入", Toast.LENGTH_SHORT).show();
        }
    }
}
