package com.smu.cs.fookey;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_search, btn_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_history = (Button) findViewById(R.id.btn_history);
        btn_search.setOnClickListener(this);
        btn_history.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_search:
                IntentHandler.mainToSearch(this);
                break;
            case R.id.btn_history:
                IntentHandler.mainToHistory(this);
                break;
            default:
        }
    }
}
