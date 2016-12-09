package sortiedechien.fr.googleauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import sortiedechien.fr.sortiedechien.LoginActivity;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class GoogleUnlogger implements MenuItem.OnMenuItemClickListener{
    private Context context;
    public GoogleUnlogger(Context context){
        this.context = context;
    }
    private static void disconnect(SharedPreferences preferences){
        AccountInformations.disconnect(preferences);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        disconnect(context.getSharedPreferences(AccountInformations.prefName, Context.MODE_PRIVATE));
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        return true;
    }
}
