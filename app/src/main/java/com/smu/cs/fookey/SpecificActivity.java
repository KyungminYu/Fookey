package com.smu.cs.fookey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SpecificActivity extends AppCompatActivity {
    private FoodData data;
    private int position;
    private DataBaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        Toast.makeText(this, position + "번째", Toast.LENGTH_SHORT).show();
        dbHandler = DataBaseHandler.getInstance(this);
        /*
        position 으로 데이터 꺼내기
         */
    }
}
