package sortiedechien.fr.search;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import sortiedechien.fr.dao.ArbreDao;
import sortiedechien.fr.data.Arbre;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.map.MapsActivity;
import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class TreesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trees_list);
        ListView list = (ListView) findViewById(R.id.tree_list);
        ArbreDao arbreDao = new ArbreDao(this);
        try{
            arbreDao.open();
            List<Arbre> arbres = arbreDao.selectAll();
            ArrayTreeAdapter arrayTreeAdapter = new ArrayTreeAdapter(this, R.id.tree_list, arbres, getLayoutInflater());
            list.setAdapter(arrayTreeAdapter);
        }catch (IOException e){
            Toast.makeText(this, getText(R.string.error), Toast.LENGTH_SHORT).show();
        }
        MainActivity.changeActionBar(getSupportActionBar(), this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, AdvancedSearchActivity.class);
            startActivity(intent);
        }
        return true;
    }
    private class ArrayTreeAdapter extends ArrayAdapter {
        private LayoutInflater inflater;
        private List<Arbre> data;
        private ArrayTreeAdapter(Context context, int ressource, List<Arbre> data, LayoutInflater layoutInflater){
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
                view = inflater.inflate(R.layout.row_result_tree_layout, null);
            }
            LatLng myLocation = MapsActivity.getMyLocation(view.getContext());
            Location myLoc = SearchListResultAdapter.toLocation(myLocation.latitude, myLocation.longitude);
            Arbre arbre = data.get(position);
            Location dataLocation = SearchListResultAdapter.toLocation(arbre.getLattitude(),arbre.getLongitude());
            Float distance = myLoc.distanceTo(dataLocation);
            ((TextView) view.findViewById(R.id.distancetexttree)).setText(SearchListResultAdapter.toDistanceUnit(distance.intValue()));
            ((TextView)view.findViewById(R.id.textresulttree)).setText(data.get(position).getAdresse());
            view.setOnClickListener(new OnItemCickListener(view.getContext(), data.get(position)));
            return view;
        }
    }
    private class OnItemCickListener implements View.OnClickListener{
        private Context context;
        private Arbre arbre;
        private OnItemCickListener(Context context, Arbre arbre){
            this.context = context;
            this.arbre = arbre;
        }
        @Override
        public void onClick(View view) {
            Uri gmmIntentUri = Uri.parse("google.navigation:q="+arbre.getLattitude()+","+arbre.getLongitude());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(mapIntent);
            }
        }
    }
}
