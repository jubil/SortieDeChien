package sortiedechien.fr.search;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class OnSearchClickListener implements View.OnClickListener {
    private Context baseContext;
    public OnSearchClickListener(Context context){
        this.baseContext = context;
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(baseContext, AdvancedSearchActivity.class);
        baseContext.startActivity(intent);
    }
}
