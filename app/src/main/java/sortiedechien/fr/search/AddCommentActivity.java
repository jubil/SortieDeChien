package sortiedechien.fr.search;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class AddCommentActivity extends AppCompatActivity {
    EditText comment;
    String libelle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        libelle = getIntent().getStringExtra("libelle");
        String filtre = getIntent().getStringExtra("searchPts");


        comment = (EditText) findViewById(R.id.editComment);
        setTitle(getText(R.string.postcomment));
        findViewById(R.id.validercomment).setOnClickListener( new OnClickOnSubmit() );
        MainActivity.changeActionBar(getSupportActionBar(), this);;
    }


    private class OnClickOnSubmit implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            String text = comment.getText().toString();
            if(text == null || text.isEmpty()){
                Toast.makeText(view.getContext(), getText(R.string.submitimpossible), Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(view.getContext(), ResultSearchActivity.class);
            intent.putExtra("searchPts", libelle);

            startActivity(intent);
        }
    }
}
