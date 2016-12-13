package sortiedechien.fr.search.basic;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Console;

import sortiedechien.fr.sortiedechien.R;

public class BasicSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("DEBUG", "Ceci est un test");

        setContentView(R.layout.activity_basic_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_parc, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button recherche = (Button) findViewById(R.id.buttonRecherche);
        recherche.setOnClickListener(new OnRechercheClickListener(this));

        Button rechercheAvancee = (Button) findViewById(R.id.buttonRechercheAvancee);
        rechercheAvancee.setOnClickListener(new OnRechercheAvanceeClickListener(this));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#49D436")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
