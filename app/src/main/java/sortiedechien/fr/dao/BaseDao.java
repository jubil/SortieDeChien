package sortiedechien.fr.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

import sortiedechien.fr.sqlite.DbHandler;

/**
 * Created by guillaume on 09/12/16.
 */

public class BaseDao {


    private  final static String NAME = "sortieChien.db";
    private final static int VERSION = 2;
    protected SQLiteDatabase db = null;
    protected DbHandler handler = null;


    public  BaseDao(Context context){

        this.handler = new DbHandler(context, NAME, null, VERSION);
    }

    public SQLiteDatabase open() throws IOException{

        this.db = handler.getWritableDatabase();

        return db;

    }


    public void close(){

        db.close();
    }

    public SQLiteDatabase getDb(){

        return db;
    }


}
