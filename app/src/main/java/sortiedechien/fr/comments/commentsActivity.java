package sortiedechien.fr.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sortiedechien.fr.data.Commentaire;
import sortiedechien.fr.sortiedechien.R;

/**
 * Created by guillaume on 12/12/16.
 */

public class commentsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Commentaire> commentaires;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_comment);

        listView = (ListView) findViewById(R.id.listeComs);

        CommentAdapter adapter = new CommentAdapter(this,R.layout.row_comment_layout, commentaires);
        listView.setAdapter(adapter);


    }
}
