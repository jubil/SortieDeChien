package sortiedechien.fr.retrofit_arbres;

import android.content.Context;
import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import sortiedechien.fr.data.Arbre;

/**
 * Created by Faseldi on 12/12/2016.
 */

public class NetworkTreeList {
    private Context context;
    private AsyncGet asyncGet;
    private List<INetworkNotifier> notifiers;
    public NetworkTreeList(Context context){
        this.context = context;
        notifiers = new ArrayList<>();
        asyncGet = new AsyncGet();
    }
    public void addNetworkNotifier(INetworkNotifier notifier){
        notifiers.add(notifier);
    }
    public void requestTreeList(INetworkNotifier... toNotify) {
        for(INetworkNotifier in : toNotify){
            addNetworkNotifier(in);
        }
        asyncGet.execute();
    }
    private class AsyncGet extends AsyncTask<URL, Void, Integer> {
        @Override
        protected Integer doInBackground(URL... urls) {
            ArbresRemoteAccess arbresRemoteAccess= new ArbresRemoteAccess(context, notifiers);
            arbresRemoteAccess.callForListArbres(notifiers);
            return 1;
        }
    }
}
