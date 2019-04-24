package com.asterisk.nam.democontentprovider.view;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.asterisk.nam.democontentprovider.R;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int CHECK_PER_ID = 69;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (checkAndRequestPermissions()) {
            goApp();
        }
    }

    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int writePhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS");
            int callPhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.permission.READ_SMS");
            List<String> listPermissionsNeeded = new ArrayList();
            if (writePhoneStatePermissions != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add("android.permission.READ_CONTACTS");
            }
            if (callPhoneStatePermissions != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add("android.permission.READ_SMS");
            }
            if (listPermissionsNeeded.isEmpty()) {
                return true;
            }
            ActivityCompat.requestPermissions(this, (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), CHECK_PER_ID);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int permission = 0;
        if (requestCode == CHECK_PER_ID) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    permission++;
            }
            if (permission == grantResults.length)
                goApp();
            else {
                finish();
            }
        }
    }

    public void goApp() {
        ContactActivity.goToMain(getApplicationContext());
    }
}
