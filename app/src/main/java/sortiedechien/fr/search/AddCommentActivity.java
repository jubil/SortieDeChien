package sortiedechien.fr.search;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.IOException;

import sortiedechien.fr.dao.CommentDao;
import sortiedechien.fr.googleauth.AccountInformations;
import sortiedechien.fr.sortiedechien.MainActivity;
import sortiedechien.fr.sortiedechien.R;

public class AddCommentActivity extends AppCompatActivity {
    EditText comment;
    String libelle, filtre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        libelle = getIntent().getStringExtra("libelle");
        filtre = getIntent().getStringExtra("searchPts");


        comment = (EditText) findViewById(R.id.editComment);
        setTitle(getText(R.string.postcomment));
        findViewById(R.id.validercomment).setOnClickListener( new OnClickOnSubmit() );
        MainActivity.changeActionBar(getSupportActionBar(), this);;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, DetailSearchActivity.class);
            intent.putExtra("searchPts", filtre);
            startActivity(intent);
        }
        return true;
    }

    private class OnClickOnSubmit implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            String text = comment.getText().toString();
            if(text.isEmpty()){
                Toast.makeText(view.getContext(), getText(R.string.submitimpossible), Toast.LENGTH_SHORT).show();
                return;
            }
            CommentDao commentDao = new CommentDao(view.getContext());
            try{
                commentDao.open();
            }catch(IOException e){
                Toast.makeText(view.getContext(), getText(R.string.error), Toast.LENGTH_SHORT).show();
                return;
            }
            SharedPreferences preferences = getSharedPreferences(AccountInformations.prefName, MODE_PRIVATE);
            int note = ((RatingBar) findViewById(R.id.ratingBarComment)).getNumStars() % 5;
            commentDao.insert(AccountInformations.getURL(preferences), AccountInformations.getName(preferences), note, text, libelle);
            Intent intent = new Intent(view.getContext(), ResultSearchActivity.class);
            intent.putExtra("searchPts", libelle);

            startActivity(intent);
        }
    }
}
