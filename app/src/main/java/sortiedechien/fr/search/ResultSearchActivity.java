package sortiedechien.fr.search;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class ResultSearchActivity extends AppCompatActivity {
    private SearchListResultAdapter searchListResultAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        String filtre = getIntent().getStringExtra("searchPts");
        String[] lesparcs = new String[0];
        String[] tmp = getIntent().getStringArrayExtra("lesparcs");
        lesparcs = tmp == null ? lesparcs : tmp;
        searchListResultAdapter = new SearchListResultAdapter(this, filtre, lesparcs);
        ListView listeRes = (ListView) findViewById(R.id.resultList);
        listeRes.setAdapter(searchListResultAdapter.getAdapter());
        listeRes.deferNotifyDataSetChanged();
        MainActivity.changeActionBar(getSupportActionBar(), this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, AdvancedSearchActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
