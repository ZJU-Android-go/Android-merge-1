package cc.vipazoo.www.ui.controller;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import cc.vipazoo.www.ui.model.Article;
import cc.vipazoo.www.ui.model.Converter;
import cc.vipazoo.www.ui.model.Triplet;
import cc.vipazoo.www.ui.model.Triplets;
import cc.vipazoo.www.ui.model.User;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TripletController {
    private OkHttpClient mOkHttpClient;     //okHttpClient 实例
    private Handler okHttpHandler;      //全局处理子线程和M主线程通信
    private static volatile TripletController mInstance;//单例引用

    User user;
    static Converter conv = new Converter();
    private Triplets triplet = new Triplets();

    public TripletController(User user)
    {
        this.user = user;
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();
    }

    private TripletController(Context context) {
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();
        okHttpHandler = new Handler(context.getMainLooper());
    }

    public static TripletController getInstance(Context context) {
        TripletController inst = mInstance;
        if (inst == null) {
            synchronized (TripletController.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new TripletController(context.getApplicationContext());
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    public Triplets getTriplets() {
        return triplet;
    }

    // okHttp POST 异步请求
    public void getTripletsFromServer()
    {
        Gson gson = new Gson();
        FormBody formbody = new FormBody.Builder()
                .add("token", user.gettoken())
                .build();
        Request request = new Request.Builder()
                .url("http://10.15.82.223:9090/app_get_data/app_get_triple")
                .post(formbody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String js = new String(conv.unicodeToUtf8(response.body().string()));
                    triplet = gson.fromJson(js, Triplets.class);
                    Log.e("TAG", "response --> " + js);
                }
                else {
                    Log.e("TAG", "WRONG!");
                }
            }
        });
    }
}
