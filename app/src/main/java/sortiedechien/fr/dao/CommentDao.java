package sortiedechien.fr.dao;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sortiedechien.fr.data.Commentaire;
import sortiedechien.fr.sqlite.DbHandler;

/**
 * Created by guillaume on 14/12/16.
 */

public class CommentDao extends BaseDao {

    private String tablename = DbHandler.TABLE_COMMENTAIRE;
    private String[] allColumns = {
            DbHandler.COLUMN_ID_C,
            DbHandler.COLUMN_NOM,
            DbHandler.COLUMN_IMG,
            DbHandler.COLUMN_CONTENU,
            DbHandler.COLUMN_LIBELLE_PARC,
            DbHandler.COLUMN_NOTE,
            DbHandler.COLUMN_DATE};


    public CommentDao(Context context) {
        super(context);
    }


    public ArrayList<Commentaire> select(String libelle) {

        ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();

        String[] params = {tablename, allColumns[4], libelle};
        Cursor c = db.rawQuery("SELECT * FROM ? WHERE ? = '?'", params);


        while (c.moveToNext()) {

            commentaires.add(new Commentaire(c.getString(2),
                    c.getString(1),
                    timeStampToString(Timestamp.valueOf(c.getString(6))),
                    c.getInt(5),
                    c.getString(3)));
        }

        return commentaires;
    }


    public void insert(String image, String nom, Timestamp temps, int rating, String contenu) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            String id = sha1(timestamp.toString());
            String[] params = {tablename,};

            db.rawQuery("INSERT INTO ? VALUES(?, ?, ?, ?, ?, ?)", params);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }


    private String timeStampToString(Timestamp timestamp) {


        String s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp);
        return s;


    }

    private String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
