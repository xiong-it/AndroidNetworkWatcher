package tech.michaelx.networkwatcher;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import tech.michaelx.networkwatcher.annotation.OnNetworkTypeChangedTo;
import tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum;
import tech.michaelx.networkwatcher.lib.NetworkStateWatcher;

import static tech.michaelx.networkwatcher.annotation.constant.NetworkTypeEnum.NETWORK_WIFI;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        NetworkStateWatcher.getDefault().registerObserver(OtherActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        NetworkStateWatcher.getDefault().unRegisterObserver(this);
    }

    @OnNetworkTypeChangedTo(type = NETWORK_WIFI)
    void onWifiConnected() {
        Toast.makeText(  this, "NetworkStateChanged>>>WIFI", Toast.LENGTH_SHORT).show();
    }

    @OnNetworkTypeChangedTo(type = NetworkTypeEnum.NETWORK_MOBILE)
    void onMobileNetworkConnected() {
        Toast.makeText(this, "NetworkStateChanged>>>Mobile networks", Toast.LENGTH_SHORT).show();
    }
}
