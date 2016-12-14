package sortiedechien.fr.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import sortiedechien.fr.sqlite.DbHandler;

/**
 * Created by guillaume on 12/12/16.
 */

public class Commentaire {


    private String image;
    private String nom;
    private String temps;
    private int rating;
    private String contenu;
    private Drawable drawable;


    public Commentaire(String image, String nom, String temps, int rating, String contenu) {
        this.image = image;
        this.nom = nom;
        this.temps = temps;
        this.rating = rating;
        this.contenu = contenu;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Drawable getDrawable() {

        new ImageTask().execute(image);

        return drawable;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }


    private class ImageTask extends AsyncTask<String, Void, Void>{


        @Override
        protected Void doInBackground(String... strings) {



            try {
               drawable =  urlToDrawable(strings[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        private Drawable urlToDrawable(String url) throws IOException {

            Bitmap x;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            x = BitmapFactory.decodeStream(input);

            return new BitmapDrawable(DbHandler.context.getResources(), x);
        }
    }


}