package com.rafi.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ACO1u2E99a1ZrtmnVTvrT6aMlwce21Ajrdw1s5j1")
                // if defined
                .clientKey("UalGLNdyGnxHN3d7sUInZox3m09e0Qc0cc0yFXja")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
