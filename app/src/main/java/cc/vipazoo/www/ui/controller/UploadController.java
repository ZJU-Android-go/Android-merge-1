package cc.vipazoo.www.ui.controller;

import com.google.gson.Gson;
import cc.vipazoo.www.ui.model.Converter;
import cc.vipazoo.www.ui.model.Entities;
import cc.vipazoo.www.ui.model.Triplets;
import cc.vipazoo.www.ui.model.User;
import cc.vipazoo.www.ui.model.Web_Message;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UploadController
{
    private User user;
    private Entities entities;
    private Triplets triplets;
    static Converter conv = new Converter();
    private static final OkHttpClient connection = new OkHttpClient();
    public User getuser()

    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    UploadController(Entities entities)
    {
        this.entities = entities;
    }
    UploadController(Triplets triplets)
    {
        this.triplets = triplets;
    }
    UploadController(Entities entities, Triplets triplets)
    {
        this.entities = entities;
        this.triplets = triplets;
    }
    public int upload_entities() throws Exception
    {
        Gson gson = new Gson();
        String js = gson.toJson(entities);
        FormBody formbody = new FormBody.Builder()
                .add("entities", js)
                .add("token", user.gettoken())
                .build();
        Request request = new Request.Builder()
                .url("http://10.15.82.223:9090/app_get_data/app_upload_entity")
                .post(formbody)
                .build();
        Response response = connection.newCall(request).execute();
        if(!response.isSuccessful())
        {
            throw new IOException("Unexpected code" + response);
        }
        js = new String(conv.unicodeToUtf8(response.body().string()));
        Web_Message msg;
        msg = gson.fromJson(js, Web_Message.class);
        final String ret = msg.getmsg();
        switch(ret) {
            case("尚未登录"):
            {

            }
            case("数据不得为空"):
            {

            }
            case("数据非Json格式"):
            {

            }
            case("上传成功"):
            {

            }
            default:
            {

            }
        }
        return 1;
    }
    public int upload_triplets() throws Exception
    {
        Gson gson = new Gson();
        String js = gson.toJson(triplets);
        FormBody formbody = new FormBody.Builder()
                .add("triplets", js)
                .add("token", user.gettoken())
                .build();
        Request request = new Request.Builder()
                .url("http://10.15.82.223:9090/app_get_data/app_upload_triple")
                .post(formbody)
                .build();
        Response response = connection.newCall(request).execute();
        if(!response.isSuccessful())
        {
            throw new IOException("Unexpected code" + response);
        }
        js = new String(conv.unicodeToUtf8(response.body().string()));
        Web_Message msg;
        msg = gson.fromJson(js, Web_Message.class);
        final String ret = msg.getmsg();
        switch(ret) {
            case("尚未登录"):
            {

            }
            case("数据不得为空"):
            {

            }
            case("数据非Json格式"):
            {

            }
            case("上传成功"):
            {

            }
            default:
            {

            }
        }
        return 1;
    }
}
