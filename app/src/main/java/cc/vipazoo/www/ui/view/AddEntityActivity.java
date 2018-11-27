package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cc.vipazoo.www.ui.R;

public class AddEntityActivity extends AppCompatActivity {

    private String pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entity);

        Intent intent = getIntent();
        pre = intent.getStringExtra("add_entity_entity");
        pre = (String)intent.getSerializableExtra("add_entity_entity");

        EditText editText = findViewById(R.id.AddEntityEntity);
        editText.setText(pre);
    }

    public void AddEntity(View view) {
        EditText entity = findViewById(R.id.AddEntityEntity);
        EditText tag = findViewById(R.id.AddEntityTag);
        String sentity = entity.getText().toString();
        String stag = tag.getText().toString();

        // HERE to add the Relation to the other activity

        finish();
    }
}
