package msp.mobile.cilenelibrary_alpha;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

import static msp.mobile.utils.BeaconUtils.ALTBEACON;
import static msp.mobile.utils.BeaconUtils.EDDYSTONE_TLM;
import static msp.mobile.utils.BeaconUtils.EDDYSTONE_UID;
import static msp.mobile.utils.BeaconUtils.EDDYSTONE_URL;
import static msp.mobile.utils.BeaconUtils.IBEACON;
import static org.altbeacon.beacon.AltBeaconParser.TAG;

public class CileneBeaconReader {
    private Activity activity;
    private BluetoothAdapter bluetoothAdapter;
    private BeaconConsumer beaconConsumer;
    private BeaconManager beaconManager;
    private Region region;
    private String beaconStatus;

    public CileneBeaconReader(Activity activity) {
        super();
        this.activity = activity;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.activity.requestPermissions(new String[]{
                    this.requestPermission(Manifest.permission.BLUETOOTH),
                    this.requestPermission(Manifest.permission.BLUETOOTH_ADMIN),
                    this.requestPermission(Manifest.permission.ACCESS_FINE_LOCATION),
                    this.requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION),
                    this.requestPermission(Manifest.permission.INTERNET)
            }, 1);
        }
    }

    private String requestPermission(String permissionId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(permissionId) != PackageManager.PERMISSION_GRANTED) {
                return permissionId;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void requestBluetooth() {
        if (bluetoothAdapter != null) {
            if (!this.isBluetoothEnabled()) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(intent, 1);
            }
        }
    }

    private boolean isBluetoothEnabled() {
        return bluetoothAdapter.isEnabled();
    }

    /*
    public void requestBeaconService() {
        region = new Region("myMonitoringUniqueId", null, null, null);
        beaconManager = BeaconManager.getInstanceForApplication(activity.getApplicationContext());
        beaconSetup(beaconManager);
        beaconConsumer = new BeaconConsumer() {
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

            @Override
            public Context getApplicationContext() {
                return activity.getApplicationContext();
            }

            @Override
            public void unbindService(ServiceConnection serviceConnection) {

            }

            @Override
            public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
                return true;
            }
        };
        //beaconManager.bind(beaconConsumer);
    }

    public String getBeaconStatus() {
        return beaconStatus;
    }

    private void beaconSetup(BeaconManager beaconManager) {
        beaconManager.getBeaconParsers().clear();
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(ALTBEACON));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(EDDYSTONE_TLM));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(EDDYSTONE_UID));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(EDDYSTONE_URL));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(IBEACON));
    }
    */
}
