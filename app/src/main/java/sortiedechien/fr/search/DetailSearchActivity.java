package sortiedechien.fr.search;

/**
 * Created by Nico on 09/12/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import sortiedechien.fr.sortiedechien.R;

public class DetailSearchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parc);
        Intent intent = getIntent();

        TextView nom_parc = (TextView) findViewById(R.id.nom_parc);
        nom_parc.setText(intent.getStringExtra("nom_parc"));

        TextView taille = (TextView) findViewById(R.id.taille_value);
        taille.setText(intent.getStringExtra("taille"));

        CheckBox eau = (CheckBox) findViewById(R.id.point_eau);
        eau.setChecked(intent.getBooleanExtra("eau",false));

        CheckBox autorise = (CheckBox) findViewById(R.id.chien_auto);
        autorise.setChecked(intent.getBooleanExtra("autorise",false));

        CheckBox sanitaire = (CheckBox) findViewById(R.id.sanitaire);
        sanitaire.setChecked(intent.getBooleanExtra("sanitaire",false));

        CheckBox jeux = (CheckBox) findViewById(R.id.jeux);
        jeux.setChecked(intent.getBooleanExtra("jeux",false));

        CheckBox clos = (CheckBox) findViewById(R.id.parcclos);
        clos.setChecked(intent.getBooleanExtra("clos",false));

        RatingBar popularite = (RatingBar) findViewById(R.id.popularite);
        popularite.setRating(intent.getIntExtra("popularite",0));
    }






}
