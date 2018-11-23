package cc.vipazoo.www.ui.view;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.controller.LoginController;

public class LoginActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "cc.vipazoo.www.ui.view.LoginActivity.toMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void LogIn(View view) {
        // get the username and password
        EditText editText1 = findViewById(R.id.editTextUsername);
        String username = editText1.getText().toString();
        EditText editText2 = findViewById(R.id.editTextPassword);
        String password = editText2.getText().toString();

        // HERE add thing to get the real username and password
        final LoginController loginController = new LoginController(username, password);

        try {
            Log.e("Before loginController", "OK");
            loginController.login();
            Log.e("After loginController", "OK");
        }
        catch (Exception e) {
            Log.e("loginController", "ERROR");
            Toast.makeText(this, "Something wrong happens", Toast.LENGTH_SHORT).show();
        }

        while (loginController.ret_login == null) {
            try {
                Thread.sleep(500);
                Log.e("status", "SLEEP");
            }
            catch (Exception e) {
                Log.e("status", "SLEEP WRONG");
            }
        }

        if(loginController.ret_login.equals("登录成功")) {
            // jump to main activity
            Intent intent = new Intent(this, WelcomeActivity.class);
            // save the user data
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRA_MESSAGE, loginController.getUser());
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {
            // login failed
            Toast.makeText(this, loginController.ret_login, Toast.LENGTH_SHORT).show();
        }
    }

    public void JumpToRegister(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}
