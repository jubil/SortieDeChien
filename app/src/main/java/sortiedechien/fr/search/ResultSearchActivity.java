package sortiedechien.fr.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
    }
}
