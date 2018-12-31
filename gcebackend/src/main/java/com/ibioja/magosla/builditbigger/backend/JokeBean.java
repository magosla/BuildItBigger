package com.ibioja.magosla.builditbigger.backend;

import com.ibioja.magosla.builditbigger.jokelib.Joke;

public final class JokeBean {
    private Joke myData;

    @SuppressWarnings("unused")
    public String getData() {
        return myData.getJoke();
    }

    public void setData(Joke data) {
        myData = data;
    }

}
