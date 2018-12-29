package com.ibioja.magosla.builditbigger.backend;

import com.ibioja.magosla.builditbigger.jokelib.Joke;

public final class JokeBean {
    private Joke myData;
    //private String myData;
   /*
    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }

    */

    @SuppressWarnings("unused")
    public String getData() {
        return myData.getJoke();
    }

    public void setData(Joke data) {
        myData = data;
    }

}
