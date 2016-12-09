package sortiedechien.fr.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sortiedechien.fr.data.Parc;

/**
 * Created by Faseldi on 09/12/2016.
 */

public interface ParcsAccessInterface {
    @GET("api/publication/24440040400129_VDN_VDN_00027/LOC_PARCS_JARDINS_NANTES_STBL/content/?format=json")
    Call<Data> listParcs();
}
