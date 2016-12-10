package sortiedechien.fr.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.sortiedechien.R;

/**
 * Created by Faseldi on 10/12/2016.
 */

public class SearchListResultAdapter {
    private ArrayAdapter adapter;
    private List<Parc> parcsFiltres;
    private Context context;
    private String filtre;
    public SearchListResultAdapter(Activity context, String filter){
        this.context = context;
        this.filtre = filter;
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
        }
        for( Parc parc : parcDao.selectAll()){
            if(toFiltre(parc.isPoint_eau(), parc.isAcces_handicape(), parc.isChien_interdit(), parc.isSanitaire(), parc.isJeux(), parc.isParc_clos()).equals(filtre)){
                parcsFiltres.add(parc);
            }
        }
        parcDao.close();
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
            ((TextView)view.findViewById(R.id.textresult)).setText(data.get(position).getLibelle());
            return view;
        }
    }
}
