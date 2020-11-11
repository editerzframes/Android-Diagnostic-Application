package com.example.diagnostictool;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.widget.TextView;

class MyPhoneStateListener extends PhoneStateListener {

    public int signalStrengthValue;
    TextView txtSignalStr;

    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        if (signalStrength.isGsm()) {
            if (signalStrength.getGsmSignalStrength() != 99)
                signalStrengthValue = signalStrength.getGsmSignalStrength() * 2 - 113;
            else
                signalStrengthValue = signalStrength.getGsmSignalStrength();
        } else {
            signalStrengthValue = signalStrength.getCdmaDbm();
        }
        txtSignalStr.setText("Signal Strength : " + signalStrengthValue);
    }

}
