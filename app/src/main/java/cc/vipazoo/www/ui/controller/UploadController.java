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
    public String upload_ret;

    public User getuser()

    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public UploadController(Entities entities)
    {
        this.entities = entities;
    }
    public UploadController(Triplets triplets)
    {
        this.triplets = triplets;
    }
    public UploadController(Entities entities, Triplets triplets)
    {
        this.entities = entities;
        this.triplets = triplets;
    }
    public void upload_entities() throws Exception
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
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
                    upload_ret = ret;
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
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public void upload_triplets() throws Exception
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Gson gson = new Gson();
                    String js = gson.toJson(triplets);
                    FormBody formbody = new FormBody.Builder()
                            .add("triples", js)
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
                    upload_ret = ret;
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
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
