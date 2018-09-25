package msp.cilenedemo.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

import msp.cilenedemo.R;
import msp.mobile.cilenelibrary_alpha.CileneBeaconReader;

public class ActivityMain extends AppCompatActivity implements BeaconConsumer {
    private static final String TAG = "ActivityMain";
    private AppCompatActivity appCompatActivity;
    private CileneBeaconReader cileneBeaconReader;
    private BeaconManager beaconManager;
    private Region region;
    private String beaconStatus;

    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;
    private TextView textView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_main);
        this.setup();
        this.draw();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setup() {
        appCompatActivity = ActivityMain.this;
        cileneBeaconReader = new CileneBeaconReader(appCompatActivity);
        cileneBeaconReader.requestPermissions();
        cileneBeaconReader.requestBluetooth();

        region = new Region("myMonitoringUniqueId", null, null, null);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.bind(this);

        drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        linearLayout = findViewById(R.id.activity_main_linear_layout);
        textView = findViewById(R.id.activity_main_text_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(appCompatActivity, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        navigationView = findViewById(R.id.activity_main_navigation_view);
    }

    @SuppressLint("SetTextI18n")
    private void draw() {
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            appCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);
            appCompatActivity.getSupportActionBar().setTitle("Cilene demo");
        }

        textView.setGravity(Gravity.START | Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setPadding(0, 20, 0, 0);
        textView.setTextColor(Color.GRAY);
        textView.setText(beaconStatus);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation transformation) {
                textView.setText(beaconStatus);
            }
        };
        animation.setRepeatCount(Animation.INFINITE);
        linearLayout.startAnimation(animation);
        linearLayout.setBackgroundColor(Color.WHITE);

        drawerLayout.setBackgroundColor(Color.WHITE);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        navigationView.setBackgroundColor(Color.WHITE);
        navigationView.inflateMenu(R.menu.activity_main_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.activity_main_menu_informacion:
                        ActivityMain.this.initActivityInformacionGeneral();
                        break;
                    case R.id.activity_main_menu_cupones:
                        ActivityMain.this.initActivityMisCupones();
                        break;
                    case R.id.activity_main_menu_about_msp:
                        ActivityMain.this.initActivityAboutMsp();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void initActivityInformacionGeneral() {
        Intent intent = new Intent(appCompatActivity, ActivityInformacionGeneral.class);
        appCompatActivity.startActivity(intent);
    }

    private void initActivityMisCupones() {
        Intent intent = new Intent(appCompatActivity, ActivityMisCupones.class);
        appCompatActivity.startActivity(intent);
    }

    private void initActivityAboutMsp() {
        Intent intent = new Intent(appCompatActivity, ActivityAboutMsp.class);
        appCompatActivity.startActivity(intent);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!.");
                beaconStatus = "I just saw an beacon for the first time!.";
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon.");
                beaconStatus = "I no longer see an beacon.";
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons. " + "Beacon status: " + + state);
                beaconStatus = "I have just switched from seeing/not seeing beacons." + "\n" + "Beacon status: " + state;
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(region);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
