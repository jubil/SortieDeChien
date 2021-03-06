package sortiedechien.fr.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.map.MapsActivity;
import sortiedechien.fr.sortiedechien.R;

/**
 * Created by Faseldi on 10/12/2016.
 */

public class SearchListResultAdapter {
    private ArrayAdapter adapter;
    private List<Parc> parcsFiltres;
    private Context context;
    private String filtre;
    private String[] libelles;
    public SearchListResultAdapter(Activity context, String filter, String[] libelles){
        this.context = context;
        this.filtre = filter;
        this.libelles = libelles;
        parcsFiltres = new ArrayList<>();
        populate();
        adapter = new AdapterSearch(context, R.id.resultList, parcsFiltres, context.getLayoutInflater());
    }
    public static String toFiltre(boolean ... booleen){
        String res = "";
        for(boolean b : booleen){
            res += b ? "1":"0";
        }
        return res;
    }

    public ArrayAdapter getAdapter(){
        return adapter;
    }

    private void populate(){
        ParcDao parcDao = new ParcDao(context);
        try {
            parcDao.open();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        List<String> libs = Arrays.asList(libelles);
        for( Parc parc : parcDao.selectAll()){
            String filter = toFiltre(parc.isPoint_eau(), parc.isAcces_handicape(), parc.isChien_interdit(), parc.isSanitaire(), parc.isJeux(), parc.isParc_clos());
            if(filtre.equals("-1")){
                if(libs.contains(parc.getLibelle())){
                    parcsFiltres.add(parc);
                }
                continue;
            }
            if(isFilterOk(filtre, filter)){
                parcsFiltres.add(parc);
            }
        }
    }
    private boolean isFilterOk(String filter, String filtre){
        char[] chars = filter.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == '1' && chars[i] != filtre.charAt(i)){
                return false;
            }
        }
        return true;
    }
    private class AdapterSearch extends ArrayAdapter {
        private LayoutInflater inflater;
        private List<Parc> data;
        private AdapterSearch(Context context, int ressource, List<Parc> data, LayoutInflater layoutInflater){
            super(context, ressource, data);
            this.inflater = layoutInflater;
            this.data = data;
        }
        @Override
        public Object getItem(int pos){
            return data.get(pos);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent){
            if(view == null){
                view = inflater.inflate(R.layout.row_result_layout, null);
            }
            LatLng myLocation = MapsActivity.getMyLocation(context);
            Location myLoc = toLocation(myLocation.latitude, myLocation.longitude);
            Location dataLocation = toLocation(Double.valueOf(data.get(position).getPosition_x()),Double.valueOf(data.get(position).getPosition_y()));
            Float distance = myLoc.distanceTo(dataLocation);
            ((TextView) view.findViewById(R.id.distancetext)).setText(toDistanceUnit(distance.intValue()));
            ((TextView)view.findViewById(R.id.textresult)).setText(data.get(position).getLibelle());
            view.findViewById(R.id.detailsbutton).setOnClickListener(new OnDetailsClickListener(context, data.get(position)));
            view.setOnClickListener(new OnItemCickListener(context, data.get(position)));
            return view;
        }
    }
    public static String toDistanceUnit(int metters){
        return metters > 1000 ? metters/1000+" km" : metters+" m";
    }
    public static Location toLocation(double latitude, double longitude){
        Location myLoc = new Location(LocationManager.GPS_PROVIDER);
        myLoc.setLatitude(latitude);
        myLoc.setLongitude(longitude);
        return myLoc;
    }
    private class OnDetailsClickListener implements View.OnClickListener{
        private Parc parc;
        private Context context;
        private OnDetailsClickListener(Context context, Parc parc){
            this.parc = parc;
            this.context = context;
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailSearchActivity.class);
            intent.putExtra("nom_parc", parc.getLibelle());
            intent.putExtra("taille", parc.getSurface());
            intent.putExtra("eau", parc.isPoint_eau());
            intent.putExtra("autorise", !parc.isChien_interdit());
            intent.putExtra("sanitaire", parc.isSanitaire());
            intent.putExtra("jeux", parc.isJeux());
            intent.putExtra("clos", parc.isParc_clos());
            intent.putExtra("popularite", 0);
            intent.putExtra("searchPts", toFiltre(parc.isPoint_eau(), parc.isAcces_handicape(), parc.isChien_interdit(), parc.isSanitaire(), parc.isJeux(), parc.isParc_clos()));
            context.startActivity(intent);
        }
    }
    private class OnItemCickListener implements View.OnClickListener{
        private Context context;
        private Parc parc;
        private OnItemCickListener(Context context, Parc parc){
            this.context = context;
            this.parc = parc;
        }
        @Override
        public void onClick(View view) {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+parc.getPosition_x()+","+parc.getPosition_y());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(mapIntent);
            }
        }
    }
}
