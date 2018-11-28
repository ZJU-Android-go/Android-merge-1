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

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPause() {
        super.onPause();
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void SignUp(View view) {
        int try_times = 0;
        // get the input
        EditText editText0 = findViewById(R.id.editTextEmail);
        String Email = editText0.getText().toString();
        EditText editText1 = findViewById(R.id.editTextUsernameSignup);
        String username = editText1.getText().toString();
        EditText editText2 = findViewById(R.id.editTextPasswordSignup);
        String password1 = editText2.getText().toString();
        EditText editText3 = findViewById(R.id.editTextPasswordSignup2);
        String password2 = editText3.getText().toString();

        // HERE to add the operation
        LoginController loginController = new LoginController(username, password1, Email);
        String status;
        if (!password1.equals(password2)) {
            status = "两次密码输入不一致";
        }
        else {
            try {
                loginController.register();
            }
            catch (Exception e){
                Log.e("ERROR", "^ ^");
            }
            while (loginController.ret_signup == null) {
                try {
                    Thread.sleep(500);
                    try_times++;
                }
                catch (Exception e) {

                }
                if (try_times == 6) {
                    Toast.makeText(this, "操作超时", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            status = loginController.ret_signup;
        }
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}
