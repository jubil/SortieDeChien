package sortiedechien.fr.search;

/**
 * Created by Nico on 09/12/2016.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;

import sortiedechien.fr.sortiedechien.R;

public class DetailSearchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parc);
    }

    @Override
    public boolean onOptionItemListSelected(MenuItem item){
        if(item.getItemId() == android.R.id.){
            Intent intent = new Intent(this, OnSearchClickListener.class);
            startActivity(intent);
        }
        return true;

    }


}
