package com.ibioja.magosla.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.ibioja.magosla.builditbigger.jokelib.Joke;

import javax.inject.Named;


/**
 * An endpoint class we are exposing
 */
@SuppressWarnings("DefaultAnnotationParam")
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.magosla.ibioja.com",
                ownerName = "backend.builditbigger.magosla.ibioja.com",
                packagePath = ""
        )
)
public final class JokeEndpoint {

    private final Joke mJoke = new Joke();

    @ApiMethod(name = "getJoke")
    public JokeBean getJoke() {
        JokeBean response = new JokeBean();
        response.setData(mJoke);
        return response;
    }

    @ApiMethod(name = "setJoke")
    public JokeBean setJoke(@Named("joke") String joke) {
        mJoke.setJoke(joke);
        JokeBean response = new JokeBean();
        response.setData(mJoke);
        return response;
    }
}
