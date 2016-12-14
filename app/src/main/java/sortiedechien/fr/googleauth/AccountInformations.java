package sortiedechien.fr.googleauth;

import android.content.SharedPreferences;

/**
 * Created by Faseldi on 08/12/2016.
 */

public class AccountInformations {
    private AccountInformations(){}
    public static final String prefName = "infos";
    public static String getId(SharedPreferences preferences){
        return preferences.getString("id", null);
    }
    public static String getEmail(SharedPreferences preferences){
        return preferences.getString("email", null);
    }
    public static String getFamilyName(SharedPreferences preferences){
        return preferences.getString("familyname", null);
    }
    public static String getName(SharedPreferences preferences){
        return preferences.getString("name", null);
    }
    public static void disconnect(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        for(String s : new String[]{"id"," email", "familyname", "name"}){
            editor.remove(s);
        }
        editor.apply();
    }
    public static String getURL(SharedPreferences preferences){
        return preferences.getString("url","");
    }
}
