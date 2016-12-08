package sortiedechien.fr.data;

/**
 * Created by guillaume on 08/12/16.
 */

public class Parc {

    private String libelle;
    private String position_x;
    private String position_y;
    private boolean point_eau;
    private boolean acces_handicape;
    private boolean chien_interdit;
    private int surface;
    private boolean sanitaire;
    private boolean jeux;
    private boolean parc_clos;

    public Parc(String libelle, String position_x, String position_y, boolean point_eau, boolean acces_handicape, boolean chien_interdit, int surface, boolean sanitaire, boolean jeux, boolean parc_clos ){

        this.libelle = libelle;
        this.position_x = position_x;
        this.position_y = position_y;
        this.point_eau = point_eau;
        this.acces_handicape = acces_handicape;
        this.chien_interdit = chien_interdit;
        this.surface = surface;
        this.sanitaire = sanitaire;
        this.jeux = jeux;
        this.parc_clos = parc_clos;

    }



    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPosition_x() {
        return position_x;
    }

    public void setPosition_x(String position_x) {
        this.position_x = position_x;
    }

    public String getPosition_y() {
        return position_y;
    }

    public void setPosition_y(String position_y) {
        this.position_y = position_y;
    }

    public boolean isPoint_eau() {
        return point_eau;
    }

    public void setPoint_eau(boolean point_eau) {
        this.point_eau = point_eau;
    }

    public boolean isAcces_handicape() {
        return acces_handicape;
    }

    public void setAcces_handicape(boolean acces_handicape) {
        this.acces_handicape = acces_handicape;
    }

    public boolean isChien_interdit() {
        return chien_interdit;
    }

    public void setChien_interdit(boolean chien_interdit) {
        this.chien_interdit = chien_interdit;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public boolean isSanitaire() {
        return sanitaire;
    }

    public void setSanitaire(boolean sanitaire) {
        this.sanitaire = sanitaire;
    }

    public boolean isJeux() {
        return jeux;
    }

    public void setJeux(boolean jeux) {
        this.jeux = jeux;
    }

    public boolean isParc_clos() {
        return parc_clos;
    }

    public void setParc_clos(boolean parc_clos) {
        this.parc_clos = parc_clos;
    }
}
