package com.ibioja.magosla.builditbigger;

import android.content.Intent;

import com.ibioja.magosla.builditbigger.jokeviewlib.JokeActivity;

public final class MainActivity extends MainActivityAbstract {

    @Override
    void launchJokeActivity(String joke) {
        Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_BUNDLE, joke);
        startActivity(intent);
    }
}
