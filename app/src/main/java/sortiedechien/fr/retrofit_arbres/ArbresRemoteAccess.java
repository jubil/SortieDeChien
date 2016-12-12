package sortiedechien.fr.retrofit_arbres;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import sortiedechien.fr.data.Arbre;
import sortiedechien.fr.sortiedechien.R;

/**
 * Created by Faseldi on 12/12/2016.
 */

public class ArbresRemoteAccess {
    private ArbresAccessDownloaderInterface service;
    private File tmp;
    private Context context;
    private File cache;
    public ArbresRemoteAccess(Context context, List<INetworkNotifier> notifiers) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.url_arbres))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.context = context;
        service = retrofit.create(ArbresAccessDownloaderInterface.class);
        cache = context.getCacheDir();
        try{
            tmp = File.createTempFile("tmbbdd",".zip", cache);
        }catch(IOException e){
            Toast.makeText(context, R.string.error, Toast.LENGTH_LONG).show();
            return;
        }
    }
    public void callForListArbres(List<INetworkNotifier> notifiers){
        List<Arbre> res = new ArrayList<>();
        downloadFile(notifiers);
    }
    private void downloadFile(final List<INetworkNotifier> notifiers) {
        final Call<ResponseBody> call = service.zipArbres();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                if (response.isSuccess()) {
                    File target = new File(cache.getAbsolutePath()+"/unzipdir");
                    for(File f : cache.listFiles()){ // remove existing zip files or zip extract dirs
                        if(f.getAbsolutePath().endsWith("zip")|| f.getAbsolutePath().contains("zip")){
                            f.delete();
                        }
                    }
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());
                    try{
                        target.mkdir();
                        unzip(tmp, target);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    for(File file : target.listFiles()){
                        if(file.getName().endsWith(".csv")){
                            try{
                                abresValues(file, notifiers);
                            }catch (IOException e){}
                        }
                    }
                    tmp.delete();
                    target.delete();
                    Toast.makeText(context, writtenToDisk ? R.string.downloadsucces : R.string.downloaderrors, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }
    private void abresValues(File file, List<INetworkNotifier> notifiers) throws  IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        List<Arbre> arbres = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] spl = line.split(";");
        }
        for( INetworkNotifier notifier :notifiers){
            notifier.dataResult(arbres);
        }
    }
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(tmp);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
    public static void unzip(File zipFile, File targetDirectory) throws IOException {
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
        try {
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[8192];
            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(targetDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " + dir.getAbsolutePath());
                if (ze.isDirectory())
                    continue;
                FileOutputStream fout = new FileOutputStream(file);
                try {
                    while ((count = zis.read(buffer)) != -1)
                        fout.write(buffer, 0, count);
                } finally {
                    fout.close();
                }
            }
        } finally {
            zis.close();
        }
    }
}
