package sortiedechien.fr.search;

/**
 * Created by Nico on 09/12/2016.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import sortiedechien.fr.sortiedechien.R;

public class DetailSearchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parc);
        TextView nom_parc = (TextView) findViewById(R.id.nom_parc);
        EditText taille = (EditText) findViewById(R.id.taille_value);
        CheckBox eau = (CheckBox) findViewById(R.id.point_eau);
        CheckBox autorise = (CheckBox) findViewById(R.id.chien_auto);
        CheckBox sanitaire = (CheckBox) findViewById(R.id.sanitaire);
        CheckBox jeux = (CheckBox) findViewById(R.id.jeux);
        CheckBox clos = (CheckBox) findViewById(R.id.parcclos);
        RatingBar popularite = (RatingBar) findViewById(R.id.popularité);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, OnSearchClickListener.class);
            startActivity(intent);
        }
        return true;
    }


}
