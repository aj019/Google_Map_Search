package in.androidmate.google_maps_search;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PlacesActivity extends AppCompatActivity {

    private String TAG = "Places";
    private TextView tvCity,tvCountry,tvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        tvCity = (TextView) findViewById(R.id.tvCity);
        tvState = (TextView) findViewById(R.id.tvState);
        tvCountry = (TextView) findViewById(R.id.tvCountry);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                Log.i(TAG, "Place: " + place.getAddress());
                Log.i(TAG, "Place: " + place.getLatLng());
                Log.i(TAG, "Place: " + place.getId());
                Log.i(TAG, "Place: " + place.getLocale());
                Log.i(TAG, "Place: " + place.getPhoneNumber());
                Geocoder mGeocoder = new Geocoder(PlacesActivity.this, Locale.getDefault());
                LatLng latLng = place.getLatLng();

                try {
                    List<Address> addresses = mGeocoder.getFromLocation(latLng.latitude,latLng.longitude, 1);
                    if (addresses != null && addresses.size() > 0) {
                        tvCity.setText(addresses.get(0).getAddressLine(0));
                        tvState.setText(addresses.get(0).getLocality());
                        tvCountry.setText(addresses.get(0).getAddressLine(2));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }




            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }
}
