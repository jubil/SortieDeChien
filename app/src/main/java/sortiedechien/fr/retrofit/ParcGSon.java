package sortiedechien.fr.retrofit;

import android.util.Log;

import sortiedechien.fr.data.Parc;

/**
 * Created by Faseldi on 09/12/2016.
 */

public class ParcGSon {
    private String Jardin_clos;
    private String Jeux;
    private String Commentaire;
    private String Pataugeoire;
    private String Acces_tan;
    private String Mobilier_pique_nique;
    private Geo geo;
    private String[] _l;
    private String Acces_handicap_y_compris_sanitai;
    private String Gardien;
    private Integer Code;
    private String Adresse_postale;
    private Integer Surface_hors_batiments;
    private String Sanitaires;
    private String Collection_vegetale;
    private String Abris;
    private String Point_d_eau;
    private String Chien_interdit_en_laisse;

    public Parc toParc() {
        return new Parc(geo.getName(), _l[0], _l[1], Point_d_eau.equals("OUI"), Acces_handicap_y_compris_sanitai.equals("OUI"),
                Chien_interdit_en_laisse.equals("OUI"), Surface_hors_batiments, Sanitaires.equals("OUI"), Jeux.equals("OUI"), Jardin_clos.equals("OUI"));
    }
    private class Geo {
        String name;
        public String getName(){
            return name;
        }
    }
    public String getGeo(){
        return geo.getName();
    }
}
