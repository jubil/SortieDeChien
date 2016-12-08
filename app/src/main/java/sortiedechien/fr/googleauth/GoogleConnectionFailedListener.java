package sortiedechien.fr.googleauth;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import sortiedechien.fr.sortiedechien.R;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class GoogleConnectionFailedListener implements GoogleApiClient.OnConnectionFailedListener {
    private Context context;
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("ErrorLogin",connectionResult.getErrorMessage());
        Toast.makeText(context, R.string.faillog, Toast.LENGTH_LONG);
    }
    public void setContext(Context context){
        this.context = context;
    }
}
