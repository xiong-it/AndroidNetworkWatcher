package tech.michaelx.networkwatcher.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 网络状态广播接收者
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkStateWatcher.getDefault().post();
    }
}
