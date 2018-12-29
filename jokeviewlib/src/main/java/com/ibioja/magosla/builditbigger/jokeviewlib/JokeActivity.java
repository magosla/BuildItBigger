package com.ibioja.magosla.builditbigger.jokeviewlib;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ibioja.magosla.builditbigger.jokeviewlib.databinding.ActivityJokeBinding;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_BUNDLE = "joke_bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityJokeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_joke);
        binding.toolbar.setTitle(R.string.joke_activity_title);

        Bundle extras = getIntent().getExtras();
        String joke = "";
        if (extras != null) {
            joke = extras.getString(JOKE_BUNDLE, "");
        }

        binding.content.jokeTv.setText(joke);
    }

}
