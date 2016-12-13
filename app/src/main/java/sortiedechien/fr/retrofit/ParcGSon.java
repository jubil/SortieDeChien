package sortiedechien.fr.retrofit;

import sortiedechien.fr.data.Parc;

/**
 * Created by Faseldi on 09/12/2016.
 */

public class ParcGSon {
    // utilisés par GSon en reflect
    @SuppressWarnings("unused")
    private String Jardin_clos;
    @SuppressWarnings("unused")
    private String Jeux;
    @SuppressWarnings("unused")
    private String Commentaire;
    @SuppressWarnings("unused")
    private String Pataugeoire;
    @SuppressWarnings("unused")
    private String Acces_tan;
    @SuppressWarnings("unused")
    private String Mobilier_pique_nique;
    @SuppressWarnings("unused")
    private Geo geo;
    @SuppressWarnings("unused")
    private String[] _l;
    @SuppressWarnings("unused")
    private String Acces_handicap_y_compris_sanitai;
    @SuppressWarnings("unused")
    private String Gardien;
    @SuppressWarnings("unused")
    private Integer Code;
    @SuppressWarnings("unused")
    private String adresse_postale;
    @SuppressWarnings("unused")
    private Integer Surface_hors_batiments;
    @SuppressWarnings("unused")
    private String Sanitaires;
    @SuppressWarnings("unused")
    private String Collection_vegetale;
    @SuppressWarnings("unused")
    private String Abris;
    @SuppressWarnings("unused")
    private String Point_d_eau;
    @SuppressWarnings("unused")
    private String Chien_interdit_en_laisse;

    public Parc toParc() {
        if(_l.length != 2){
            return null;
        }
        return new Parc(geo.getName(), _l[0], _l[1], Point_d_eau.equals("OUI"), Acces_handicap_y_compris_sanitai.equals("OUI"),
                Chien_interdit_en_laisse.equals("OUI"), Surface_hors_batiments, Sanitaires.equals("OUI"), Jeux.equals("OUI"), Jardin_clos.equals("OUI"));
    }
    private class Geo {
        String name;
        public String getName(){
            return name;
        }
    }
}
