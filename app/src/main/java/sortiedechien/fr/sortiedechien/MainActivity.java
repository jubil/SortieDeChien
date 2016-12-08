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
import android.widget.TextView;

import org.w3c.dom.Text;

import sortiedechien.fr.googleauth.AccountInformations;

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
        //TextView name = (TextView) findViewById(R.id.nameLabel);
        //name.setText(R.string.hello+", "+AccountInformations.getName(getSharedPreferences(AccountInformations.prefName, MODE_PRIVATE)));
    }
    private boolean isConnected(){
        return AccountInformations.getId(getSharedPreferences(AccountInformations.prefName, MODE_PRIVATE)) != null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
