package com.ibioja.magosla.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ibioja.magosla.builditbigger.jokeviewlib.JokeActivity;

abstract class MainActivityAbstract  extends AppCompatActivity implements EndpointsAsyncTask.Callback {

    private Button mButton;
    private String mButtonText;
    private ProgressBar mProgressBar;
    private boolean mShowInterstitialAds;

    protected void showInterstitialAds(){
        mShowInterstitialAds = true;
    }

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

        if(!mShowInterstitialAds) {
            launchJokeActivity(result);
        }
    }

    abstract void launchJokeActivity(String joke);
}