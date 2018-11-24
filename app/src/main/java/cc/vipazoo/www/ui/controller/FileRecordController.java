package cc.vipazoo.www.ui.controller;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;

import cc.vipazoo.www.ui.model.Entities;
import cc.vipazoo.www.ui.model.Triplets;
import cc.vipazoo.www.ui.model.User;

public class FileRecordController {
    final String app_name = "Naive_annotator";
    private String Entity_record_file;
    private String Triplet_record_file;
    FileRecordController(User user)
    {
        Entity_record_file = user.getName() + "_entity_record.txt";
        Triplet_record_file = user.getName() + "_triplet_record.txt";
    }

    public void setEntity_record_file(String entity_record_file) {
        Entity_record_file = entity_record_file;
    }

    public void setTriplet_record_file(String triplet_record_file) {
        Triplet_record_file = triplet_record_file;
    }

    public String getEntity_record_file() {
        return Entity_record_file;
    }

    public String getTriplet_record_file() {
        return Triplet_record_file;
    }

    public Entities readEntity_record_file()
    {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + app_name;
        Entities entities = new Entities();
        Gson gson = new Gson();
        try{
            File f = new File(path, Entity_record_file);
            if(!f.exists())
            {

            }
            else
            {

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
        String path = Environment.getExternalStorageDirectory().getPath() + app_name;
        Triplets triplets = new Triplets();
        return triplets;
    }

    public void writeEntity_record_file(Entities entities)
    {
        String path = Environment.getExternalStorageDirectory().getPath() + app_name;
    }

    public void writeTriplets_record_file(Triplets triplets)
    {
        String path = Environment.getExternalStorageDirectory().getPath() + app_name;
    }
}
