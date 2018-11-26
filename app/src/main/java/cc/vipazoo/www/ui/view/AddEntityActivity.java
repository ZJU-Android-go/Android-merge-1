package cc.vipazoo.www.ui.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cc.vipazoo.www.ui.R;

public class AddEntityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entity);
    }

    public void AddEntity(View view) {
        EditText entity1 = findViewById(R.id.AddEntityEntity);
        EditText entity2 = findViewById(R.id.AddEntityTag);
        String sentity1 = entity1.getText().toString();
        String sentity2 = entity2.getText().toString();

        // HERE to add the Relation to the other activity

        finish();
    }
}
