package sortiedechien.fr.search;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class ResultSearchActivity extends AppCompatActivity {
    private OnSearchLaunchClickListener onSearchClickLaunchListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        onSearchClickLaunchListener = new OnSearchLaunchClickListener(this);

        ListView listeRes = (ListView) findViewById(R.id.resultList);
        listeRes.setAdapter(onSearchClickLaunchListener.getAdapter());
        listeRes.deferNotifyDataSetChanged();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#49D436")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
