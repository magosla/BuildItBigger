package com.ibioja.magosla.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


abstract class MainActivityAbstract  extends AppCompatActivity implements EndpointsAsyncTask.Callback {

    private Button mButton;
    private String mButtonText;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.joke_btn);
        mProgressBar = findViewById(R.id.progress_bar);
        mButtonText = mButton.getText().toString();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(MainActivityAbstract.this).execute();
            }
        });
    }

    @Override
    public void onLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mButton.setEnabled(false);
        mButton.setText(R.string.msg_loading);
    }

    @Override
    public void onFinished(String result) {
        mProgressBar.setVisibility(View.GONE);
        mButton.setText(mButtonText);
        mButton.setEnabled(true);

        launchJokeActivity(result);
    }

    abstract void launchJokeActivity(String joke);
}