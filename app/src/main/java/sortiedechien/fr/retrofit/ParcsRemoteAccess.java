package sortiedechien.fr.retrofit;

import android.content.Context;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import sortiedechien.fr.sortiedechien.R;

/**
 * Created by Faseldi on 09/12/2016.
 */

public class ParcsRemoteAccess {
    private ParcsAccessInterface  service;
    public ParcsRemoteAccess(Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.url_parcs))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ParcsAccessInterface.class);
    }
    public List<ParcGSon> getListParcs(){
        Call<Data> repos = service.listParcs();
        try {
            Response<Data> rep = repos.execute();
            return Arrays.asList(rep.body().getData());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
