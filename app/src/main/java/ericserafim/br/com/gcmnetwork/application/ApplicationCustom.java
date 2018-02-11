package ericserafim.br.com.gcmnetwork.application;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by erics on 27/01/2017.
 */

public class ApplicationCustom extends Application {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("ERIC", "onConfigurationChanged");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ERIC", "onCreate");
    }

    @Override
    public void onLowMemory() {
        Log.i("ERIC", "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        Log.i("ERIC", "onTerminate");
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.i("ERIC", "onTrimMemory");
        super.onTrimMemory(level);
    }


}
