package com.example.code.codelocker;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Code on 3/30/15.
 */
public class TimeSetter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DevicePolicyManager devicePolicyManager;
        ComponentName demoDeviceAdmin;


        devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        demoDeviceAdmin = new ComponentName(context, DemoDeviceAdminReceiver.class);

        devicePolicyManager.setPasswordQuality(
                demoDeviceAdmin,DevicePolicyManager.PASSWORD_QUALITY_UNSPECIFIED);
        devicePolicyManager.setPasswordMinimumLength(demoDeviceAdmin, 4);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String test = sdf.format(cal.getTime());
        SimpleDateFormat sdf2 = new SimpleDateFormat("mmHH");
        String test2 = sdf2.format(cal.getTime());

        boolean result = devicePolicyManager.resetPassword(test,
                DevicePolicyManager.RESET_PASSWORD_REQUIRE_ENTRY);

        Toast.makeText(context, test, Toast.LENGTH_SHORT).show();
    }
}
