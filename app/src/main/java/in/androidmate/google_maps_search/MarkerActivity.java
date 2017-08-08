package in.androidmate.google_maps_search;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import in.androidmate.google_maps_search.helper.CurrentLocation;

public class MarkerActivity extends AppCompatActivity {

    TextView tvLat,tvLon;
    private static final int MY_REQUEST_CODE = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker);

        setupViews();
        checkPermissions();
    }

    private void checkPermissions() {

       boolean b = CurrentLocation.getLocationPermission(this);
        if(b){
            CurrentLocation.getCurrentLocation(this);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_REQUEST_CODE);
        }
    }

    private void setupViews() {

        tvLat = (TextView) findViewById(R.id.tvLat);
        tvLon = (TextView) findViewById(R.id.tvLon);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_REQUEST_CODE) {


                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   checkPermissions();
                } else {
                    Toast.makeText(this,"Location permission is required to get the current location ",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MarkerActivity.this,MainActivity.class));
                    finish();
                }

        }
    }

}
