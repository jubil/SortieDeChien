package sortiedechien.fr.map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        MainActivity.changeActionBar(getSupportActionBar(), this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ParcDao parcDao = new ParcDao(this);
        try {
            parcDao.open();
            List<Parc> parcs = parcDao.selectAll();
            for (Parc p : parcs) {
                double lat = Double.valueOf(p.getPosition_x());
                double lng = Double.valueOf(p.getPosition_y());
                LatLng parcLocation = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(parcLocation).title(p.getLibelle()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatLng myLocation = getMyLocation(this);
        MarkerOptions marker = new MarkerOptions()
                .position(myLocation)
                .title( getResources().getString(R.string.myloc))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14));
    }

    /**
     * @param context
     * @return the users locatin or the iut's location if the user won't use it's location
     */
    public static LatLng getMyLocation(Context context){
        LatLng myLocation;
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            myLocation = new LatLng(47.2237205, -1.5449378); // iut
        }else{
            LocationManager locationManager = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
            myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        }
        return myLocation;
    }
}
