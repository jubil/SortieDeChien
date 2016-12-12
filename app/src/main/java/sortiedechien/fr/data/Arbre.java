package sortiedechien.fr.data;

/**
 * Created by guillaume on 12/12/16.
 */

public class Arbre {

    private int id;
    private String adresse;
    private float lattitude;
    private float longitude;

    public Arbre(int id, String adresse, float lattitude, float longitude) {
        this.id = id;
        this.adresse = adresse;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
