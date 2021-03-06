package sortiedechien.fr.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import sortiedechien.fr.data.Arbre;
import sortiedechien.fr.sqlite.DbHandler;

/**
 * Created by guillaume on 12/12/16.
 */

public class ArbreDao extends BaseDao {

    private String tablename = DbHandler.TABLE_ARBRE;
    private String[] allcolumns = {
            DbHandler.COLUMN_ID,
            DbHandler.COLUMN_ADRESSE,
            DbHandler.COLUMN_LONGITUDE,
            DbHandler.COLUMN_LATTITUDE
    };

    public ArbreDao(Context context) {
        super(context);
    }


    public ArrayList<Arbre> selectAll(){

        ArrayList<Arbre> arbres = new ArrayList<Arbre>();
        Cursor c = super.db.rawQuery("SELECT * FROM "+tablename, null);

        while (c.moveToNext()){
            arbres.add(new Arbre(c.getInt(0), c.getString(1), c.getFloat(2), c.getFloat(3)));
        }

        c.close();

        return arbres;
    }

    public void insert(int id, String adresse, float lat, float lon){
        super.db.execSQL(String.format("INSERT INTO %s VALUES(\"%s\", \"%s\", \"%s\", \"%s\");", tablename, id, adresse, lat, lon));
    }
}
