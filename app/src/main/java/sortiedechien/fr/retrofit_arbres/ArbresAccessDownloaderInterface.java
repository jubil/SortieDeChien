package sortiedechien.fr.retrofit_arbres;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Faseldi on 12/12/2016.
 */

public interface ArbresAccessDownloaderInterface {
    @GET("ws.zip")
    Call<ResponseBody> zipArbres();
}
