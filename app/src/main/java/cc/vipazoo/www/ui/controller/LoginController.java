package cc.vipazoo.www.ui.controller;

import com.google.gson.Gson;
import cc.vipazoo.www.ui.model.Web_Message;
import cc.vipazoo.www.ui.model.User;
import cc.vipazoo.www.ui.model.Converter;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginController {
    private static User user = new User();
    static Converter conv = new Converter();
    private static final OkHttpClient connection = new OkHttpClient();
    public String ret_login;

    public User getUser() {
        return user;
    }

//   public static void main(String[] argv)
//    {
////        user.setName("3160104050");
////        user.setPasswd("123456");
//        //user.setEmail_address("3160104050@zju.edu.cn");
//        LoginController loginController = new LoginController("", "");
//        try
//        {
//            loginController.login();
//            loginController.logout();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }


    public LoginController(String name, String passwd)
    {
        user.setName(name);
        user.setPasswd(passwd);
    }
    public LoginController(String name, String passwd, String email_address)
    {
        user.setName(name);
        user.setPasswd(passwd);
        user.setEmail_address(email_address);
    }


    public void login()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                Gson gson = new Gson();
                RequestBody formbody = new FormBody.Builder()
                        .add("username", user.getName())
                        .add("password", user.getPasswd())
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.15.82.223:9090/app_get_data/app_signincheck")
                        .post(formbody)
                        .build();
                Response response = connection.newCall(request).execute();
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code" + response);
                }
                String js = Converter.unicodeToUtf8(response.body().string());
                Web_Message msg;
                msg = gson.fromJson(js, Web_Message.class);
                System.out.println(msg.getmsg());
                ret_login = msg.getmsg();
                if (ret_login.equals("登录成功")) {
                    user.settoken(msg.gettoken());
                }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void logout() throws  Exception
    {
        Gson gson = new Gson();
        FormBody formbody = new FormBody.Builder()
                .add("token", user.gettoken())
                .build();
        Request request = new Request.Builder()
                .url("http://10.15.82.223:9090/app_get_data/app_logout")
                .post(formbody)
                .build();
        Response response = connection.newCall(request).execute();
        if(!response.isSuccessful())
        {
            throw new IOException("Unexpected code" + response);
        }
        String js = new String(conv.unicodeToUtf8(response.body().string()));
        Web_Message msg;
        msg = gson.fromJson(js, Web_Message.class);
        final String ret = msg.getmsg();
        switch(ret) {
            case("登出成功"):
            {
               // System.out.println("Successfully logout");
            }
            default:
            {

            }
        }
    }
    public String register() throws  Exception
    {

        Gson gson = new Gson();
        FormBody formbody = new FormBody.Builder()
                .add("username", user.getName())
                .add("email", user.getEmail_address())
                .add("password", user.getPasswd())
                .build();
        Request request = new Request.Builder()
                .url("http://10.15.82.223:9090/app_get_data/app_register")
                .post(formbody)
                .build();
        Response response = connection.newCall(request).execute();
        if(!response.isSuccessful())
        {
            throw new IOException("Unexpected code" + response);
        }
        String js = new String(conv.unicodeToUtf8(response.body().string()));
        Web_Message msg;
        msg = gson.fromJson(js, Web_Message.class);
        final String ret = msg.getmsg();
        return ret;
    }
}