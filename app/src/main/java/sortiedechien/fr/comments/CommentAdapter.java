package sortiedechien.fr.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import sortiedechien.fr.data.Commentaire;
import sortiedechien.fr.sortiedechien.R;

/**
 * Created by guillaume on 12/12/16.
 */

public class CommentAdapter extends ArrayAdapter{


    public CommentAdapter(Context context, int resource, ArrayList<Commentaire> commentaires) {
        super(context, resource, commentaires);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){

            LayoutInflater v;
            v = LayoutInflater.from(getContext());
            view = v.inflate(R.layout.row_comment_layout, null);
        }

        Commentaire c = (Commentaire) getItem(position);

        if (c != null){

            ImageView image = (ImageView) view.findViewById(R.id.imageCom);
            TextView nom = (TextView) view.findViewById(R.id.nom);
            TextView temps = (TextView) view.findViewById(R.id.temps);
            RatingBar rating = (RatingBar) view.findViewById(R.id.rating);
            TextView contenu = (TextView) view.findViewById(R.id.contenu);

            if (image != null){

                image.setImageResource(c.getImage());
            }

            if (nom != null){

                nom.setText(c.getNom());
            }

            if (temps != null){

                nom.setText(c.getTemps());
            }

            if(rating != null){

                rating.setRating(c.getRating());
            }

            if(contenu != null){

                contenu.setText(c.getContenu());

            }
        }

        return view;
    }
}


