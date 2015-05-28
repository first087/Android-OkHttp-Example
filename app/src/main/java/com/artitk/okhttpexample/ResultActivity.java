package com.artitk.okhttpexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private RadioButton radioHeader;
    private RadioButton radioBody;
    private ProgressBar progressBar;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int menuIndex = intent.getIntExtra("menu index", 0);
        String menuTitle = getResources().getStringArray(R.array.menu_list)[menuIndex];

        radioHeader = (RadioButton) findViewById(R.id.radioHeader);
        radioBody   = (RadioButton) findViewById(R.id.radioBody);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textResult  = (TextView)    findViewById(R.id.textResult);

        setTitle(menuTitle);

        switch (menuIndex) {
            case 0: callSyncGet();      break;  // Synchronous Get
            case 1: callASyncGet();     break;  // Asynchronous Get
            // TODO : Add other case
        }
    }

    private void callSyncGet() {
        // TODO : Implement Sync Get
    }

    private void callASyncGet() {
        // TODO : Implement Async Get
    }

}
