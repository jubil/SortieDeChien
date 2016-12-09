package sortiedechien.fr.retrofit;

import android.content.Context;
import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import sortiedechien.fr.data.Parc;

/**
 * Created by Faseldi on 09/12/2016.
 */

public class NetworkParkList {
    private Context context;
    private  AsyncGet asyncGet;
    private List<INetworkNotifier> notifiers;
    public NetworkParkList(Context context){
        this.context = context;
        notifiers = new ArrayList<>();
        asyncGet = new AsyncGet();
    }
    public void addNetworkNotifier(INetworkNotifier notifier){
        notifiers.add(notifier);
    }
    public void requestParkList(INetworkNotifier... toNotify) {
        for(INetworkNotifier in : toNotify){
            addNetworkNotifier(in);
        }
        asyncGet.execute();
    }
    private class AsyncGet extends AsyncTask<URL, Void, List<ParcGSon>> {

        @Override
        protected List<ParcGSon> doInBackground(URL... urls) {
            ParcsRemoteAccess parcsRemoteAccess = new ParcsRemoteAccess(context);
            return parcsRemoteAccess.getListParcs();
        }
        @Override
        public void onPostExecute(List<ParcGSon> parcs){
            List<Parc> res = new ArrayList<>();
            for(ParcGSon parc : parcs){
                parc.toParc();
            }
            for(INetworkNotifier notifier : notifiers){
                notifier.dataResult(res);
            }
        }
    }
}
