package sortiedechien.fr.search;

/**
 * Created by Nico on 09/12/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import sortiedechien.fr.comments.CommentAdapter;
import sortiedechien.fr.data.Commentaire;
import sortiedechien.fr.sortiedechien.R;

public class DetailSearchActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();

    public DetailSearchActivity(){

        Commentaire commentaire = new Commentaire(R.drawable.feuilles, "jean", "5 min", 3, "aha");
        commentaires.add(commentaire);
    }

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

        CheckBox clos = (CheckBox) findViewById(R.id.clos);
        clos.setChecked(intent.getBooleanExtra("clos",false));

        RatingBar popularite = (RatingBar) findViewById(R.id.popularite);
        popularite.setRating(intent.getIntExtra("popularite",0));

        listView = (ListView) findViewById(R.id.listeComs);

        CommentAdapter adapter = new CommentAdapter(this,R.layout.row_comment_layout, commentaires);
        Log.e("list", listView.toString());
        listView.setAdapter(adapter);



    }


}
