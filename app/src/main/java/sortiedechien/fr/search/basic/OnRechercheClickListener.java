package sortiedechien.fr.search.basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import sortiedechien.fr.search.AdvancedSearchActivity;
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

    }


}
