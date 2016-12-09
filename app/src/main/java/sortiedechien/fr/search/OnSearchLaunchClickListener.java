package sortiedechien.fr.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import sortiedechien.fr.data.Parc;
import sortiedechien.fr.sortiedechien.R;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class OnSearchLaunchClickListener implements View.OnClickListener{
    private Context context;
    private boolean pointdeau, acceshand, autorise, sanitaires, jeux, parcclos;
    private ArrayAdapter adapter;
    private List<Parc> parcsFiltres;
    public OnSearchLaunchClickListener(Activity context){
        pointdeau = ((CheckBox) context.findViewById(R.id.pointdeau)).isChecked();
        acceshand = ((CheckBox) context.findViewById(R.id.acceshand)).isChecked();
        autorise = ((CheckBox) context.findViewById(R.id.autorise)).isChecked();
        sanitaires = ((CheckBox) context.findViewById(R.id.sanitaires)).isChecked();
        jeux = ((CheckBox) context.findViewById(R.id.jeux)).isChecked();
        parcclos = ((CheckBox) context.findViewById(R.id.parcclos)).isChecked();
        parcsFiltres = new ArrayList<>();
        adapter = new AdapterSearch(context, R.id.resultList, parcsFiltres);
    }
    private void search(List<Parc> parcs){
        for(Parc parc : parcs){
            if( pointdeau == parc.isPoint_eau() && acceshand == parc.isAcces_handicape()
                    && autorise && !parc.isChien_interdit() && sanitaires == parc.isSanitaire()
                    && jeux && parc.isJeux() && parcclos == parc.isParc_clos()){
                parcsFiltres.add(parc);
            }
        }
        Intent intent = new Intent(context, ResultSearchActivity.class);
        context.startActivity(intent);
    }
    public ArrayAdapter getAdapter(){
        return adapter;
    }
    @Override
    public void onClick(View view) {
        search(null);
    }
    private static class AdapterSearch extends ArrayAdapter {
        private AdapterSearch(Context context, int ressource,  List<?> data){
            super(context, ressource, data);
        }
    }
}
