package com.pop.pricecutz.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Pop H2 on 12/19/2016.
 * Pop Inc
 * Lagos Nigeria
 */

public class PCAutenticatorService extends Service {

    // Instance field that stores the authenticator object
    private PCAutenticator mPCAutenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mPCAutenticator = new PCAutenticator(this);
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mPCAutenticator.getIBinder();
    }
}