package cc.vipazoo.www.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cc.vipazoo.www.ui.controller.LoginController;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void SignUp(View view) {
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
        String status = "";
        if (!password1.equals(password2)) {
            status = "两次密码输入不一致";
        }
        else {
            try {
                status = loginController.register();
            }
            catch (Exception e) {
                status = "Something wrong happens";
            }
        }
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}
