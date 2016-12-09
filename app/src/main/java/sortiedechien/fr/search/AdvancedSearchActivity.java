package sortiedechien.fr.search;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

<<<<<<< HEAD
import sortiedechien.fr.sortiedechien.MainActivity;
=======
import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
>>>>>>> 07333ccd6f8a67e363c6255611e9de701350b64d
import sortiedechien.fr.sortiedechien.R;
import sortiedechien.fr.sqlite.DbHandler;

public class AdvancedSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener( new OnSearchLaunchClickListener(this) );

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#49D436")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
