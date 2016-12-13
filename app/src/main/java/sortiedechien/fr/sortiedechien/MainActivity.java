package sortiedechien.fr.sortiedechien;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.googleauth.AccountInformations;
import sortiedechien.fr.googleauth.GoogleUnlogger;
import sortiedechien.fr.map.OnClickMainMap;
import sortiedechien.fr.search.OnSearchClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!isConnected()){
            Intent newActivity = new Intent(this, LoginActivity.class);
            this.startActivity(newActivity);
        }else{
            Log.i("Connection", "Connetion à google réussie");
        }
        ImageButton search_button = (ImageButton) findViewById(R.id.search_button);
        search_button.setOnClickListener(new OnSearchClickListener(this));

        ImageButton map_button = (ImageButton) findViewById(R.id.map_button);
        map_button.setOnClickListener(new OnClickMainMap(this));

        this.setTitle(AccountInformations.getName(getSharedPreferences(AccountInformations.prefName, MODE_PRIVATE)));;
    }
    private boolean isConnected(){
        return AccountInformations.getId(getSharedPreferences(AccountInformations.prefName, MODE_PRIVATE)) != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setOnMenuItemClickListener(new GoogleUnlogger(this));
        menu.getItem(1).setOnMenuItemClickListener(new DbUpdate());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private class DbUpdate implements MenuItem.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            try{
                ParcDao parcDao = new ParcDao(getApplicationContext());
                parcDao.open();
                parcDao.changeVersion();
            }catch (IOException e){
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
    public static final void changeActionBar(@Nullable  ActionBar actionBar, Context context){
        if(actionBar == null){
            return;
        }
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(context.getResources().getString(R.string.appcolor))));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
