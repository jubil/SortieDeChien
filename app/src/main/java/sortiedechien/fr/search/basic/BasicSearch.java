package sortiedechien.fr.search.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import sortiedechien.fr.search.AdvancedSearchActivity;
import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class BasicSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.v("DEBUG", "Ceci est un test");

        setContentView(R.layout.activity_basic_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.search);
        setSupportActionBar(toolbar);

        Button b = (Button) findViewById(R.id.buttonRechercheAvancee);
        b.setOnClickListener(new OnRechercheAvanceeClickListener());
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_parc, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button recherche = (Button) findViewById(R.id.buttonRecherche);
        recherche.setOnClickListener(new OnRechercheClickListener(this));

        Button rechercheAvancee = (Button) findViewById(R.id.buttonRechercheAvancee);
        rechercheAvancee.setOnClickListener(new OnRechercheAvanceeClickListener());

        MainActivity.changeActionBar(getSupportActionBar(),this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
