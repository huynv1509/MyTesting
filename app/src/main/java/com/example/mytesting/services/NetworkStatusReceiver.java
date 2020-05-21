package com.example.mytesting.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.NonNull;

public class NetworkStatusReceiver extends BroadcastReceiver {

    public ConnectivityReceiverListener connectivityReceiverListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        isConnectedOrConnecting(context);
    }

    private void isConnectedOrConnecting(@NonNull Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (context instanceof ConnectivityReceiverListener && connMgr != null) {
            connectivityReceiverListener = (ConnectivityReceiverListener) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connMgr.getNetworkCapabilities(connMgr.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        connectivityReceiverListener.onNetworkConnectionChanged(true);
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        connectivityReceiverListener.onNetworkConnectionChanged(true);
                    }  else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                        connectivityReceiverListener.onNetworkConnectionChanged(true);
                    } else {
                        connectivityReceiverListener.onNetworkConnectionChanged(false);
                    }
                } else {
                    connectivityReceiverListener.onNetworkConnectionChanged(false);
                }
            } else {
                @SuppressWarnings("deprecation")
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                //noinspection deprecation
                if (networkInfo != null && networkInfo.isConnected()) {
                    connectivityReceiverListener.onNetworkConnectionChanged(true);
                } else {
                    connectivityReceiverListener.onNetworkConnectionChanged(false);
                }
            }
        }
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isDataNetworkAvailable);
    }
}
