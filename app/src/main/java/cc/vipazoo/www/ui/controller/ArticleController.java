package cc.vipazoo.www.ui.controller;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import cc.vipazoo.www.ui.model.Converter;
import cc.vipazoo.www.ui.model.User;
import cc.vipazoo.www.ui.model.Article;
import cc.vipazoo.www.ui.model.Web_Message;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;

public class ArticleController {
    User user;
    private static final OkHttpClient connection = new OkHttpClient();
    static Converter conv = new Converter();
    private Article article = new Article();
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:

                    break;
                case 0:

                    break;
                default:
                    break;
            }

        }

    };

    public Article getArticle() {
        return this.article;
    }

    public ArticleController(User user)
    {
        this.user = user;
    }
    public boolean getArticleFromServer()
    {
        final Gson gson = new Gson();
        FormBody formbody = new FormBody.Builder()
                .add("token", user.gettoken())
                .build();
        Request request = new Request.Builder()
                .url("http://10.15.82.223:9090/app_get_data/app_get_entity")
                .post(formbody)
                .build();
        Call call = connection.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                System.out.println();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                String js = new String(conv.unicodeToUtf8(response.body().string()));
                article = gson.fromJson(js, Article.class);
                Web_Message msg = new Web_Message();
                msg = gson.fromJson(js, Web_Message.class);
                System.out.println(article.getContent());
                final String ret = article.getTitle();
                if(!ret.isEmpty())
                {
                    System.out.println(article.getContent());
                }
                else
                {
                    System.out.println(msg.getmsg());
                }
                Message tomain = handler.obtainMessage();
                tomain.what = 1;
                tomain.obj = article.getContent();
                handler.sendMessage(tomain);
            }
        });
        return true;        // Here has some problems, deal with failure
    }


}
