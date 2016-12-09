package sortiedechien.fr.retrofit;

import java.util.List;

import sortiedechien.fr.data.Parc;

/**
 * Created by Faseldi on 09/12/2016.
 */

public interface INetworkNotifier {
    public void dataResult(List<Parc> parcs);
}
