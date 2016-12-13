package sortiedechien.fr.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import sortiedechien.fr.data.Parc;
import sortiedechien.fr.sqlite.DbHandler;

/**
 * Created by guillaume on 08/12/16.
 *
 */

public class ParcDao extends BaseDao{

    private String tableName = DbHandler.TABLE_PARCS;
    private String[] allColumns = {
            DbHandler.COLUMN_LIBELLE,
            DbHandler.COLUMN_POSITION_X,
            DbHandler.COLUMN_POSITION_Y,
            DbHandler.COLUMN_point_d_eau,
            DbHandler.COLUMN_ACCES_HANDICAPE,
            DbHandler.COLUMN_CHIEN_INTERDIT,
            DbHandler.COLUMN_SURFACE,
            DbHandler.COLUMN_SANITAIRE,
            DbHandler.COLUMN_JEUX,
            DbHandler.COLUMN_PARC_CLOS};

    public ParcDao(Context context) {
        super(context);
    }

    public ArrayList<Parc> selectAll(){

        ArrayList<Parc> parcs = new ArrayList<>();
        Cursor c  = super.db.rawQuery("SELECT * FROM  " + tableName + " ;", null);

        while (c.moveToNext()){
            parcs.add(new Parc(c.getString(0),
                    c.getString(1),
                    c.getString(2),
                    intToBool(c.getInt(3)),
                    intToBool(c.getInt(4)),
                    intToBool(c.getInt(5)),
                    c.getInt(6),
                    intToBool(c.getInt(7)),
                    intToBool(c.getInt(8)),
                    intToBool(c.getInt(9))
            ));
        }
        c.close();
        return parcs;
    }

    private boolean intToBool(int i){
        return  i == 1;
    }
}