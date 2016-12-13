package sortiedechien.fr.search;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class AdvancedSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener( new OnSearchLaunchClickListener(this) );
        MainActivity.changeActionBar(getSupportActionBar(), this);
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
