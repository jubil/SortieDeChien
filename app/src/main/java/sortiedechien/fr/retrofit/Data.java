package sortiedechien.fr.retrofit;

/**
 * Created by Faseldi on 09/12/2016.
 */

public class Data {
    private int version = 1;
    private int nb_result = 91;
    private ParcGSon[] data;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getNb_result() {
        return nb_result;
    }

    public void setNb_result(int nb_result) {
        this.nb_result = nb_result;
    }

    public ParcGSon[] getData() {
        return data;
    }

    public void setData(ParcGSon[] data) {
        this.data = data;
    }
}
