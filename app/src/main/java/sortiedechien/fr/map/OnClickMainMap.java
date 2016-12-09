package sortiedechien.fr.map;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by Faseldi on 09/12/2016.
 */

public class OnClickMainMap implements View.OnClickListener{
    private Context context;
    public OnClickMainMap(Context context){
        this.context = context;
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, MapsActivity.class);
        context.startActivity(intent);
    }
}
