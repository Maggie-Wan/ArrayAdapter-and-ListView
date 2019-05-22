package com.example.student.AdapterAndListView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Sec extends AppCompatActivity {
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        et=(EditText)findViewById(R.id.et);
    }

    public void ok(View view) {
        String str=et.getText().toString();
        Intent in=new Intent();
        in.putExtra("userinput",str);
        setResult(RESULT_OK,in);
        finish();
    }
}
