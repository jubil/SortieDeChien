package sortiedechien.fr.search.basic;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import sortiedechien.fr.search.AdvancedSearchActivity;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class OnRechercheAvanceeClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AdvancedSearchActivity.class);
        view.getContext().startActivity(intent);
    }
}
