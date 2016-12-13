package sortiedechien.fr.search;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;

import sortiedechien.fr.sortiedechien.R;

import static sortiedechien.fr.search.SearchListResultAdapter.toFiltre;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class OnSearchLaunchClickListener implements View.OnClickListener{
    private Activity context;
    private boolean pointdeau, acceshand, autorise, sanitaires, jeux, parcclos;

    public OnSearchLaunchClickListener(Activity context){
        this.context = context;
    }
    private void updateThis(){
        pointdeau = ((CheckBox) context.findViewById(R.id.pointdeau)).isChecked();
        acceshand = ((CheckBox) context.findViewById(R.id.acceshand)).isChecked();
        autorise = ((CheckBox) context.findViewById(R.id.autorise)).isChecked();
        sanitaires = ((CheckBox) context.findViewById(R.id.sanitaires)).isChecked();
        jeux = ((CheckBox) context.findViewById(R.id.jeux)).isChecked();
        parcclos = ((CheckBox) context.findViewById(R.id.parcclos)).isChecked();
    }
    @Override
    public void onClick(View view) {
        updateThis();
        String filtre = toFiltre(pointdeau, acceshand, autorise, sanitaires, jeux, parcclos);
        Intent intent = new Intent(context, ResultSearchActivity.class);
        intent.putExtra("searchPts", filtre);
        context.startActivity(intent);
    }
}
