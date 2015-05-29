package com.artitk.okhttpexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private RadioButton radioHeader;
    private RadioButton radioBody;
    private ProgressBar progressBar;
    private TextView textResult;

    private static final String TEST_URL = "http://graph.facebook.com/zuck";

    private Response response;

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
        if (response == null) return;

        String result = "";

        int viewId = buttonView.getId();
        switch (viewId) {
            case R.id.radioHeader:
                Headers headers = response.headers();
                for (String header : headers.names()) {
                    result += "name : " + header + "\n\tvalue :" + headers.get(header) + "\n";
                }
                break;
            case R.id.radioBody:
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    result = "Error !\n\n" + e.getMessage();
                }
                break;
        }

        textResult.setText(result);
    }

    private void callSyncGet() {
        // TODO : Implement Sync Get
    }

    private void callASyncGet() {
        resetView();

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(TEST_URL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                textResult.setText("Error! " + e.getMessage());
                showView();
            }

            @Override
            public void onResponse(Response response) {
                ResultActivity.this.response = response;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showView();
                    }
                });
            }
        });
    }

    private void resetView() {
        radioHeader.setEnabled(false);
        radioHeader.setChecked(false);
        radioBody.setEnabled(false);
        radioBody.setChecked(false);
        progressBar.setVisibility(View.VISIBLE);
        textResult.setVisibility(View.GONE);
        textResult.setText("");
    }

    private void showView() {
        radioHeader.setEnabled(true);
        radioBody.setEnabled(true);
        progressBar.setVisibility(View.GONE);
        textResult.setVisibility(View.VISIBLE);
        radioHeader.setChecked(true);
    }

}
