package sortiedechien.fr.search.basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.map.MapsActivity;
import sortiedechien.fr.search.AdvancedSearchActivity;
import sortiedechien.fr.search.ResultSearchActivity;
import sortiedechien.fr.sortiedechien.R;

public class OnRechercheClickListener implements View.OnClickListener {
    private Activity context;

    private String nom;
    private int distance;
    private int type;


    public OnRechercheClickListener(Activity context){
        this.context = context;
    }

    private void updateThis() {

        Spinner spinnerType = (Spinner) context.findViewById(R.id.spinner);
        type = spinnerType.getSelectedItemPosition();

        EditText ETNom = (EditText) context.findViewById(R.id.editTextNomParc);
        nom = ETNom.getText().toString();

        EditText ETDistance = (EditText) context.findViewById(R.id.editTextDistance);
        if(ETDistance.getText().toString().equals("")){
            distance = 10000; //Mettre le rayon de recherche à 10km si rien n'est précisé dans le champs
        }else {
            distance = Integer.valueOf(ETDistance.getText().toString());
        }
    }

    @Override
    public void onClick(View view) {
        updateThis();
        Log.v("Recherche", nom + " " + distance + " " + type);
        ParcDao parcDao = new ParcDao(context);
        try{
            parcDao.open();
        }catch (IOException e){
            Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show();
            return;
        }
        List<String> parcs = new ArrayList<>();
        for(Parc parc : parcDao.selectAll()){
            /*if(parc.getLibelle().toLowerCase().contains( nom.toLowerCase())){
                // ajouter les libelles souhaités
                //parcs.add(parc.getLibelle());
            }*/
            LatLng myLocation = MapsActivity.getMyLocation(context);
            Location myLoc = toLocation(myLocation.latitude, myLocation.longitude);
            Location dataLocation = toLocation(Double.valueOf(parc.getPosition_x()),Double.valueOf(parc.getPosition_y()));
            Float distanceParc = myLoc.distanceTo(dataLocation);

            //Log.v("DEBUG", ""+distanceParc);


                if(distanceParc < distance && parc.getLibelle().toLowerCase().contains( nom.toLowerCase())){
                    if((type == 0 && !parc.isChien_interdit())
                            || (type == 1 && parc.getSurface()<20000 && !parc.isChien_interdit())
                            || (type == 2 && parc.getSurface()>100000 && !parc.isChien_interdit())
                            || (type == 3 && parc.isChien_interdit())
                            ){
                        parcs.add(parc.getLibelle());
                    }
                }





        }
        Intent intent = new Intent(context, ResultSearchActivity.class);
        intent.putExtra("searchPts", "-1");
        intent.putExtra("lesparcs", parcs.toArray(new String[parcs.size()]));
        context.startActivity(intent);
    }

    private Location toLocation(double latitude, double longitude){
        Location myLoc = new Location(LocationManager.GPS_PROVIDER);
        myLoc.setLatitude(latitude);
        myLoc.setLongitude(longitude);
        return myLoc;
    }

}
