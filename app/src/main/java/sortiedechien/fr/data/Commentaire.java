package sortiedechien.fr.data;

import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by guillaume on 12/12/16.
 */

public class Commentaire {


    private int image;
    private String nom;
    private String temps;
    private int rating;
    private String contenu;


    public Commentaire(int image, String nom, String temps, int rating, String contenu) {
        this.image = image;
        this.nom = nom;
        this.temps = temps;
        this.rating = rating;
        this.contenu = contenu;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
