package sortiedechien.fr.search.basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.search.AdvancedSearchActivity;
import sortiedechien.fr.search.ResultSearchActivity;
import sortiedechien.fr.sortiedechien.R;

public class OnRechercheClickListener implements View.OnClickListener {
    private Activity context;

    private String nom;
    private int distance;
    private String type;

    public OnRechercheClickListener(Activity context){
        this.context = context;
    }

    private void updateThis() {
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
            if(parc.getLibelle().toLowerCase().contains( nom.toLowerCase())){
                // ajouter les libelles souhaités
                parcs.add(parc.getLibelle());
            }
        }
        Intent intent = new Intent(context, ResultSearchActivity.class);
        intent.putExtra("searchPts", "-1");
        intent.putExtra("lesparcs", parcs.toArray(new String[parcs.size()]));
        context.startActivity(intent);
    }


}
