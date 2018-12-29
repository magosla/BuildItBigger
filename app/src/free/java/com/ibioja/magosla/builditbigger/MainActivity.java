package com.ibioja.magosla.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.ibioja.magosla.builditbigger.jokeviewlib.JokeActivity;


public class MainActivity extends MainActivityAbstract {

    private InterstitialAd mInterstitialAd;
    private String jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, "ca-app-pub-0305345416733679~7461531783");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mBinding.bannerView.adView.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (!jokeText.isEmpty()) {
                    jokeActivity();
                }
            }
        });
    }

    @Override
    void launchJokeActivity(String joke) {
        jokeText = joke;
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
            jokeActivity();
        }
    }

    private void jokeActivity() {
        Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_BUNDLE, jokeText);
        startActivity(intent);
    }
}
