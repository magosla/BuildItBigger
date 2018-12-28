package com.ibioja.magosla.builditbigger.jokeviewlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_BUNDLE = "joke_bundle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.joke_activity_title);

        Bundle extras = getIntent().getExtras();
        String joke = "";
        if(extras != null){
            joke = extras.getString(JOKE_BUNDLE, "");
        }

        ((TextView) findViewById(R.id.joke_tv)).setText(joke);


    }

}
