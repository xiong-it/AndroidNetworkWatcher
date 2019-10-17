package tech.michaelx.networkwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import tech.michaelx.networkwatcher.annotation.NetworkStateChanged;
import tech.michaelx.networkwatcher.lib.NetworkStateWatcher;

import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_2G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_3G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_4G;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_NO;
import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_WIFI;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        NetworkStateWatcher.getDefault().register(DemoActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        NetworkStateWatcher.getDefault().unRegisterObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkStateWatcher.getDefault().stopWatch();
    }

    /**
     * 网络发生变化
     *
     * @param type 网络类型
     */
    @NetworkStateChanged(notifyOnAppStart = false)
    void onNetworkStateChanged(int type) {
        switch (type) {
            case NETWORK_2G:
            case NETWORK_3G:
            case NETWORK_4G:
                Toast.makeText(this, "NetworkStateChanged>>>Mobile networks", Toast.LENGTH_SHORT).show();
                break;

            case NETWORK_WIFI:
                Toast.makeText(  this, "NetworkStateChanged>>>WIFI", Toast.LENGTH_SHORT).show();
                break;

            case NETWORK_NO:
                Toast.makeText(this, "NetworkStateChanged>>>无网络", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void jump(View view) {
        startActivity(new Intent(this, OtherActivity.class));
    }
}
