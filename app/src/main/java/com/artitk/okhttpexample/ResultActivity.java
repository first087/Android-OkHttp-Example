package com.artitk.okhttpexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

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

        radioHeader.setOnCheckedChangeListener(this);
        radioBody.setOnCheckedChangeListener(this);

        switch (menuIndex) {
            case 0: callSyncGet();      break;  // Synchronous Get
            case 1: callASyncGet();     break;  // Asynchronous Get
            // TODO : Add other case
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) return;

        String result = null;

        int viewId = buttonView.getId();
        switch (viewId) {
            case R.id.radioHeader:
                result = "Result from Header";  // TODO : Implement get store result
                break;
            case R.id.radioBody:
                result = "Result from Body";    // TODO : Implement get store result
                break;
        }

        textResult.setText(result);
    }

    private void callSyncGet() {
        // TODO : Implement Sync Get
    }

    private void callASyncGet() {
        // TODO : Implement Async Get
    }

    private void resetView() {
        radioHeader.setEnabled(false);
        radioHeader.setChecked(true);
        radioBody.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        textResult.setVisibility(View.GONE);
        textResult.setText("");
    }

    private void showView(String result) {
        radioHeader.setEnabled(true);
        radioBody.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        textResult.setVisibility(View.VISIBLE);
        textResult.setText(result);
    }

}
