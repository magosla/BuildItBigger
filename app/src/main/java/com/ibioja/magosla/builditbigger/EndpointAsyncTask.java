package com.ibioja.magosla.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.ibioja.magosla.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

import javax.annotation.Nullable;

class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    private JokeApi jokeApiService = null;
    private WeakReference<Callback> mCallback;

    public interface Callback{
        void onLoading();
        void onFinished(String result);
    }

    EndpointsAsyncTask(@Nullable Callback callback){
        if(callback != null) {
            mCallback = new WeakReference<>(callback);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mCallback != null){
            Callback cb = mCallback.get();
            if (cb != null){
                cb.onLoading();
            }
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if (jokeApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    //.setRootUrl("http://localhost:8888/_ah/api/")
                    .setRootUrl("https://builditbigger-226123.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            jokeApiService = builder.build();
        }

        try {
            if(params.length == 0 || params[0] == null){
                // jokeApiService.getJoke()
                return jokeApiService.getJoke().execute().getData();
            }else{
                return jokeApiService.setJoke(params[0]).execute().getData();
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(mCallback != null){
            Callback cb = mCallback.get();
            if (cb != null){
                cb.onFinished(result);
            }
        }
    }
}