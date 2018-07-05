package ch.hevs.fbonvin.disasterassistance;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import ch.hevs.fbonvin.disasterassistance.R;
import ch.hevs.fbonvin.disasterassistance.utils.AlertDialogBuilder;
import ch.hevs.fbonvin.disasterassistance.utils.MandatoryPermissionsHandling;
import ch.hevs.fbonvin.disasterassistance.views.FragMap;
import ch.hevs.fbonvin.disasterassistance.views.FragMessages;
import ch.hevs.fbonvin.disasterassistance.views.FragSettings;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_MANDATORY_PERMISSIONS = 1;
    private static final String[] MANDATORY_PERMISSION =
            new String[] {
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            };


    private BottomNavigationView.OnNavigationItemSelectedListener mNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_messages:
                            selectedFragment = new FragMessages();
                            break;
                        case R.id.nav_map:
                            selectedFragment = new FragMap();
                            break;
                        case R.id.nav_settings:
                            selectedFragment = new FragSettings();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(mNavListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FragMessages()).commit();


        MandatoryPermissionsHandling.checkPermission(this, CODE_MANDATORY_PERMISSIONS, MANDATORY_PERMISSION);
    }


    /**
     * Handle the mandatory permissions, if the access is not granted, the application restart
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Handling of mandatory permissions of the application, the app do not work without them
        if (requestCode == CODE_MANDATORY_PERMISSIONS) {
            for (int grantResult : grantResults){
                if (grantResult == PackageManager.PERMISSION_DENIED){

                    //Create a dialog that will display an alert dialog promoting the user to accept
                    AlertDialogBuilder.showAlertDialogPositive(
                            MainActivity.this,
                            "Mandatory permission",
                            "This permission is mandatory",

                            "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MandatoryPermissionsHandling.checkPermission(
                                            MainActivity.this,
                                            CODE_MANDATORY_PERMISSIONS, MANDATORY_PERMISSION);
                                }
                    });
                }
            }
        }
    }
}
