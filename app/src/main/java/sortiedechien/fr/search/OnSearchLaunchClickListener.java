package sortiedechien.fr.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import sortiedechien.fr.dao.ParcDao;
import sortiedechien.fr.data.Parc;
import sortiedechien.fr.sortiedechien.R;
import sortiedechien.fr.sqlite.DbHandler;

import static sortiedechien.fr.search.SearchListResultAdapter.toFiltre;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class OnSearchLaunchClickListener implements View.OnClickListener{
    private Context context;
    private boolean pointdeau, acceshand, autorise, sanitaires, jeux, parcclos;

    public OnSearchLaunchClickListener(Activity context){
        pointdeau = ((CheckBox) context.findViewById(R.id.pointdeau)).isChecked();
        acceshand = ((CheckBox) context.findViewById(R.id.acceshand)).isChecked();
        autorise = ((CheckBox) context.findViewById(R.id.autorise)).isChecked();
        sanitaires = ((CheckBox) context.findViewById(R.id.sanitaires)).isChecked();
        jeux = ((CheckBox) context.findViewById(R.id.jeux)).isChecked();
        parcclos = ((CheckBox) context.findViewById(R.id.parcclos)).isChecked();
        this.context = context;
    }
    @Override
    public void onClick(View view) {
        String filtre = toFiltre(pointdeau, acceshand, autorise, sanitaires, jeux, parcclos);
        Intent intent = new Intent(context, ResultSearchActivity.class);
        intent.putExtra("searchPts", filtre);
        context.startActivity(intent);
    }
}
