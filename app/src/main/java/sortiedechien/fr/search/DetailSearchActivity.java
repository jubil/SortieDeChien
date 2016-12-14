package sortiedechien.fr.search;

/**
 * Created by Nico on 09/12/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import sortiedechien.fr.comments.CommentAdapter;
import sortiedechien.fr.dao.CommentDao;
import sortiedechien.fr.data.Commentaire;
import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class DetailSearchActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayList<Commentaire> commentaires;
    private String filtre, libelle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parc);
        Intent intent = getIntent();
        filtre = intent.getStringExtra("searchPts");

        TextView nom_parc = (TextView) findViewById(R.id.nom_parc);
        libelle = intent.getStringExtra("nom_parc");
        nom_parc.setText(libelle);

        TextView taille = (TextView) findViewById(R.id.taille_value);
        taille.setText(intent.getStringExtra("taille"));

        CheckBox eau = (CheckBox) findViewById(R.id.point_eau);
        eau.setChecked(intent.getBooleanExtra("eau", false));

        CheckBox autorise = (CheckBox) findViewById(R.id.chien_auto);
        autorise.setChecked(intent.getBooleanExtra("autorise", false));

        CheckBox sanitaire = (CheckBox) findViewById(R.id.sanitaire);
        sanitaire.setChecked(intent.getBooleanExtra("sanitaire", false));

        CheckBox jeux = (CheckBox) findViewById(R.id.jeux);
        jeux.setChecked(intent.getBooleanExtra("jeux", false));

        CheckBox clos = (CheckBox) findViewById(R.id.clos);
        clos.setChecked(intent.getBooleanExtra("clos", false));

        RatingBar popularite = (RatingBar) findViewById(R.id.popularite);
        popularite.setRating(intent.getIntExtra("popularite", 0));

        listView = (ListView) findViewById(R.id.listeComs);

        findViewById(R.id.accessaddcomment).setOnClickListener( new OnClickAddComment() );

        CommentDao commentDao = new CommentDao(this);
        try{
            commentDao.open();
            commentaires = commentDao.select(libelle);
        }catch (IOException e){
            Toast.makeText(this, getText(R.string.error), Toast.LENGTH_SHORT).show();
            commentaires = new ArrayList<>();
        }

        if (!(commentaires.isEmpty())) {

            CommentAdapter adapter = new CommentAdapter(this, R.layout.row_comment_layout, commentaires);
            listView.setAdapter(adapter);

        } else {

            TextView noCommentView = (TextView) findViewById(R.id.noComment);
            noCommentView.setVisibility(View.VISIBLE);

        }

        MainActivity.changeActionBar(getSupportActionBar(), this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, ResultSearchActivity.class);
            intent.putExtra("searchPts", filtre);
            startActivity(intent);
        }
        return true;
    }

    private class OnClickAddComment implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), AddCommentActivity.class);
            intent.putExtra("libelle", libelle);
            intent.putExtra("searchPts", filtre);
            startActivity(intent);
        }
    }

}
