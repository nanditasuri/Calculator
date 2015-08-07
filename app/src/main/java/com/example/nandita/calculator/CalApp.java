package com.example.nandita.calculator;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by nandita on 1/31/2015.
 */
public class CalApp extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"You have opened Calci App",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Toast.makeText(this,"You have closed Calci App",Toast.LENGTH_LONG).show();
    }
}
