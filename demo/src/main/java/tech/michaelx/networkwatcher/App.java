package tech.michaelx.networkwatcher;

import android.app.Application;

import tech.michaelx.networkwatcher.lib.NetworkStateWatcher;

/**
 * @since 2019-10-17
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkStateWatcher.getDefault().init(this);
    }
}
