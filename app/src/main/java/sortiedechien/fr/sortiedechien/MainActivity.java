package sortiedechien.fr.sortiedechien;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import sortiedechien.fr.googleauth.AccountInformations;
import sortiedechien.fr.googleauth.GoogleUnlogger;
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
    }
    private boolean isConnected(){
        return AccountInformations.getId(getSharedPreferences(AccountInformations.prefName, MODE_PRIVATE)) != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setOnMenuItemClickListener(new GoogleUnlogger(this));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
