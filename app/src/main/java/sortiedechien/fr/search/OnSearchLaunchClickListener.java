package sortiedechien.fr.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.sortiedechien.R;
import sortiedechien.fr.sqlite.DbHandler;

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
        adapter = new AdapterSearch(context, R.id.resultList, parcsFiltres, context.getLayoutInflater());
        this.context = context;
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
        ParcDao parcDao = new ParcDao(context == null ? view.getContext() : context);
        parcDao.open();
        search(parcDao.selectAll());
        parcDao.close();
    }

    private static class AdapterSearch extends ArrayAdapter {
        private LayoutInflater inflater;
        private List<?> data;
        private AdapterSearch(Context context, int ressource,  List<?> data, LayoutInflater layoutInflater){
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
                view = inflater.inflate(R.layout.row_result_layout, parent);
            }
            return view;
        }
    }
}
