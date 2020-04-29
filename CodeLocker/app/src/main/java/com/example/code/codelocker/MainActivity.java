package com.example.code.codelocker;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    DevicePolicyManager devicePolicyManager;
    ComponentName demoDeviceAdmin;

    private PendingIntent pendingIntent;
    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Device Policy Manager service and our receiver class
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        demoDeviceAdmin = new ComponentName(this, DemoDeviceAdminReceiver.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void enable(View view){
        Toast.makeText(this.getApplicationContext(), "Make CodeLocker as Admin!", Toast.LENGTH_SHORT).show();
        Intent callGPSSettingIntent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivity(callGPSSettingIntent);
    }

    public void startLocking(View view){
        Toast.makeText(this, "Starting HHMM time lock", Toast.LENGTH_SHORT).show();
        stopAllLocks();
        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, TimeSetter.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }
    public void startLocking2(View view){
        Toast.makeText(this, "Starting HHMMMMHH time lock", Toast.LENGTH_SHORT).show();
        stopAllLocks();
        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, TimeSetter2.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }
    public void startLocking3(View view){
        Toast.makeText(this, "Starting MMDD date lock", Toast.LENGTH_SHORT).show();
        stopAllLocks();
        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, DateSetter.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }
    public void startLocking4(View view){
        Toast.makeText(this, "Starting MMDDDDMM date lock", Toast.LENGTH_SHORT).show();
        stopAllLocks();
        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(this, DateSetter2.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }
    public void stopLocking1(){
        Intent alarmIntent = new Intent(this, TimeSetter.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }
    public void stopLocking2(){
        Intent alarmIntent = new Intent(this, TimeSetter2.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }
    public void stopLocking3(){
        Intent alarmIntent = new Intent(this, DateSetter.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }
    public void stopLocking4(){
        Intent alarmIntent = new Intent(this, DateSetter2.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }
    public void stopLocking(View view){
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        demoDeviceAdmin = new ComponentName(this, DemoDeviceAdminReceiver.class);

        devicePolicyManager.setPasswordQuality(
                demoDeviceAdmin,DevicePolicyManager.PASSWORD_QUALITY_UNSPECIFIED);
        devicePolicyManager.setPasswordMinimumLength(demoDeviceAdmin, 4);

        boolean result = devicePolicyManager.resetPassword("1234",
                DevicePolicyManager.RESET_PASSWORD_REQUIRE_ENTRY);

        Toast.makeText(this, "Stopped all Locks. Setting 1234 as password", Toast.LENGTH_SHORT).show();
        stopAllLocks();
    }

    public void stopAllLocks(){
        stopLocking1();
        stopLocking2();
        stopLocking3();
        stopLocking4();
    }
}
