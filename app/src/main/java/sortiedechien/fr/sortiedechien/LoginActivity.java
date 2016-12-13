package sortiedechien.fr.sortiedechien;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;

import sortiedechien.fr.googleauth.AccountInformations;
import sortiedechien.fr.googleauth.GoogleConnectionFailedListener;

public class LoginActivity extends AppCompatActivity {
    public static final int RC_SIGN_IN = 20;
    private static GoogleApiClient mGoogleApiClient;
    private GoogleConnectionFailedListener onConnectionFailedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestScopes(new Scope(Scopes.PLUS_ME))
                .requestId()
                .requestProfile()
                .build();
        onConnectionFailedListener = new GoogleConnectionFailedListener();
        onConnectionFailedListener.setContext(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,  onConnectionFailedListener /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        findViewById(R.id.sign_in_button).setOnClickListener(new ClickOnGoogleButton());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.i("SignIn", "handleSignInResult:" + result.isSuccess() +" code : "+result.getStatus());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            updateUI(true, acct);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false, null);
        }
    }

    private void updateUI(boolean b, @Nullable GoogleSignInAccount account){
        if(b && account != null){
            Log.i("SignIn", "Conneté à google, affichage de la vue d'accueil");
            SharedPreferences preferences = getSharedPreferences(AccountInformations.prefName, MODE_APPEND);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("id", account.getId());
            editor.putString("email", account.getEmail());
            editor.putString("familyname", account.getFamilyName());
            editor.putString("name", account.getDisplayName());
            editor.apply();
            Intent launchMain = new Intent(this, MainActivity.class);
            this.startActivity(launchMain);
        }else{
            Log.i("SignIn", "Pas connecté à google");
            Toast.makeText(this, R.string.faillog, Toast.LENGTH_LONG).show();
        }
    }
    private class ClickOnGoogleButton implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.sign_in_button){
                signIn();
            }
        }
        private void signIn(){
            Log.i("StartResult", "Starting activity Log for result");
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }
    public static final void disconnect(final Context context){
        if(mGoogleApiClient == null || !mGoogleApiClient.isConnected()){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return;
        }
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                });
    }
}
