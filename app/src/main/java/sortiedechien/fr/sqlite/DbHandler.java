package sortiedechien.fr.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by guillaume on 08/12/16.
 */

public class DbHandler extends SQLiteOpenHelper{

    public static final String TABLE_PARCS = "PARCS";
    public static final String COLUMN_LIBELLE = "LIBELLE";
    public static final String COLUMN_POSITION_X = "POSITION_X";
    public static final String COLUMN_POSITION_Y = "POSITION_Y";
    public  static final String COLUMN_point_d_eau = "POINT_D_EAU";
    public static final String COLUMN_ACCES_HANDICAPE = "ACCES_HANDICAPE";
    public static final String COLUMN_CHIEN_INTERDIT = "CHIEN_INTERDIT";
    public static final String COLUMN_SURFACE = "SURFACE";
    public static final String COLUMN_SANITAIRE = "SANITAIRE";
    public static final String COLUMN_JEUX = "JEUX";
    public static final String COLUMN_PARC_CLOS = "PARC_CLOS";


    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
