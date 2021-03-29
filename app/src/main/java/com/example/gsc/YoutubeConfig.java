package com.example.gsc;

public class YoutubeConfig {
    public YoutubeConfig() {
    }

    private static final String API_KEY = BuildConfig.MY_API_KEY;

    public static String getApiKey() {
        return API_KEY;
    }
}
