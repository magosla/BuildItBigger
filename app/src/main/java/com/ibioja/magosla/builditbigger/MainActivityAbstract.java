package com.ibioja.magosla.builditbigger;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ibioja.magosla.builditbigger.databinding.ActivityMainBinding;


abstract class MainActivityAbstract extends AppCompatActivity implements EndpointAsyncTask.Callback {

    protected ActivityMainBinding mBinding;
    private Button mButton;
    private String mButtonText;
    private ProgressBar mProgressBar;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mButton = mBinding.jokeBtn;
        mProgressBar = mBinding.progressBar;
        mButtonText = mButton.getText().toString();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointAsyncTask(MainActivityAbstract.this).execute();
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
        if (!TextUtils.isEmpty(result)) {
            launchJokeActivity(result);
        } else {
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(this, R.string.msg_joke_fetch_error, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    abstract void launchJokeActivity(String joke);
}