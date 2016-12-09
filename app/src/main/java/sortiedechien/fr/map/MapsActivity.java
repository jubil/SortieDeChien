package sortiedechien.fr.map;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.List;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.search.AdvancedSearchActivity;
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

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#49D436")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
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
        parcDao.open();
        //List<Parc> parcs = parcDao.selectAll();
        List<Parc> parcs = Arrays.asList(new Parc[]{new Parc("lib test","47.2193934","-1.5776035", true, true, true, 100, true, true, true)});
        parcDao.close();
        for(Parc p : parcs){
            double lat = Double.valueOf(p.getPosition_x());
            double lng = Double.valueOf(p.getPosition_y());
            LatLng parcLocation = new LatLng(lat,lng);
            mMap.addMarker(new MarkerOptions().position(parcLocation).title(p.getLibelle()));
        }

        LatLng myLocation = new LatLng(47.2237205, -1.5449378);
        mMap.addMarker(new MarkerOptions().position(myLocation).title( getResources().getString(R.string.myloc)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
    }
}
