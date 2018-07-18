package com.fb.inkredibles.myapplication;


import android.app.Application;

import com.parse.Parse;

public class ParseApp extends Application {

    //first entry point of our application
    @Override
    public void onCreate() {
        super.onCreate();

        //need to register our subclass
        //custom parse model that we need to implement
        //ParseObject.registerSubclass(Post.class);

        //set up parse...clientKey that is masterkey
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("wema")
                .clientKey("RAKAttackSNK")
                .server("http://wema-rak.herokuapp.com/parse")
                .build();

        //intialize the parse
        Parse.initialize(configuration);
    }
}

