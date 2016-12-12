package sortiedechien.fr.search.basic;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import sortiedechien.fr.search.AdvancedSearchActivity;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class OnRechercheAvanceeClickListener implements View.OnClickListener {
    private Context baseContext;
    public OnRechercheAvanceeClickListener(Context context){
        this.baseContext = context;
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(baseContext, AdvancedSearchActivity.class);
        baseContext.startActivity(intent);
    }
}
