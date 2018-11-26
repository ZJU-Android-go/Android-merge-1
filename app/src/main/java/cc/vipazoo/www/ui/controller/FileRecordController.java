package cc.vipazoo.www.ui.controller;
import android.content.Context;
import android.os.Environment;
import android.util.Xml;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cc.vipazoo.www.ui.model.Entities;
import cc.vipazoo.www.ui.model.Triplet;
import cc.vipazoo.www.ui.model.Triplets;
import cc.vipazoo.www.ui.model.User;

public class FileRecordController {
    private Context context;
    final String app_name = "Naive_annotator";
    private String Entity_record_file;
    private String Triplet_record_file;
    FileRecordController(User user)
    {
       // External_cache_dir = ListViewActivity.getContext();
        Entity_record_file = user.getName() + "_entity_record.json";
        Triplet_record_file = user.getName() + "_triplet_record.json";
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setEntity_record_file(String entity_record_file) {
        Entity_record_file = entity_record_file;
    }

    public void setTriplet_record_file(String triplet_record_file) {
        Triplet_record_file = triplet_record_file;
    }

    public Context getContext() {
        return context;
    }

    public String getEntity_record_file() {
        return Entity_record_file;
    }

    public String getTriplet_record_file() {
        return Triplet_record_file;
    }

    public void createTriplet_record_file()
    {
        try{
            String path = context.getExternalCacheDir().getAbsolutePath();
            File f = new File(path, Triplet_record_file);
            f.createNewFile();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return;
    }

    public boolean deleteTriplet_record_file()
    {
            String path = context.getExternalCacheDir().getAbsolutePath();
            File f = new File(path, Triplet_record_file);
           return f.delete();
    }

    public void createEntity_record_file()
    {
         try{
             String path = context.getExternalCacheDir().getAbsolutePath();
             File f = new File(path, Entity_record_file);
            f.createNewFile();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return;
    }

    public boolean deleteEntity_record_file()
    {
        String path = context.getExternalCacheDir().getAbsolutePath();
        File f = new File(path, Triplet_record_file);
        return f.delete();
    }

    public Entities readEntity_record_file()
    {
        Entities entities = new Entities();
        Gson gson = new Gson();
        try{
            String path = context.getExternalCacheDir().getAbsolutePath();
            File f = new File(path, Entity_record_file);
            if(!f.exists())
            {
                createEntity_record_file();
            }
            else
            {
                FileInputStream fin = context.openFileInput(f.getName());
                byte[] buffer = new byte[4096];
                fin.read(buffer);
                String js = new String(buffer, "UTF-8");
                entities = gson.fromJson(js, Entities.class);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return entities;
    }

    public Triplets readTriplets_record_file()
    {
        Triplets triplets = new Triplets();
        Gson gson = new Gson();
        try{
            String path = context.getExternalCacheDir().getAbsolutePath();
            File f = new File(path, Triplet_record_file);
            if(!f.exists())
            {
                createTriplet_record_file();
            }
            else
            {
                FileInputStream fin = context.openFileInput(f.getName());
                byte[] buffer = new byte[4096];
                fin.read(buffer);
                String js = new String(buffer, "UTF-8");
                triplets = gson.fromJson(js, Triplets.class);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return triplets;
    }

    public void writeEntity_record_file(Entities entities)
    {
        Gson gson = new Gson();
        try{
            String path = context.getExternalCacheDir().getAbsolutePath();
            File f = new File(path, Entity_record_file);
            if(!f.exists())
            {
                createEntity_record_file();
            }
            else
            {
                FileOutputStream fout = context.openFileOutput(f.getName(), Context.MODE_PRIVATE);
                byte[] buffer = new byte[4096];
                buffer = gson.toJson(entities).getBytes("UTF-8");
                fout.write(buffer);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return;
    }

    public void writeTriplets_record_file(Triplets triplets)
    {
        Gson gson = new Gson();
        try{
            String path = context.getExternalCacheDir().getAbsolutePath();
            File f = new File(path, Triplet_record_file);
            if(!f.exists())
            {
                createEntity_record_file();
            }
            else
            {
                FileOutputStream fout = context.openFileOutput(f.getName(), Context.MODE_PRIVATE);
                byte[] buffer = new byte[4096];
                buffer = gson.toJson(triplets).getBytes("UTF-8");
                fout.write(buffer);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return;
    }
}
