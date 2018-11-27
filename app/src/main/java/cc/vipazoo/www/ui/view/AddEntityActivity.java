package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.model.Entity;

public class AddEntityActivity extends AppCompatActivity {

    private String pre;
    private int start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entity);

        Intent intent = getIntent();
        pre = intent.getStringExtra("add_entity_entity");
        start = intent.getIntExtra("add_entity_start", -1);
        end = intent.getIntExtra("add_entity_end", -1);

        EditText editText = findViewById(R.id.AddEntityEntity);
        editText.setText(pre);
    }

    public void AddEntity(View view) {
        EditText entity = findViewById(R.id.AddEntityEntity);
        EditText tag = findViewById(R.id.AddEntityTag);
        String sentity = entity.getText().toString();
        String stag = tag.getText().toString();

        // HERE to add the Relation to the other activity
        if (pre == null) {

        }
        else {
            Entity e = new Entity();
            e.setName(sentity);
            e.setStart(start);
            e.setEnd(end);
            e.setTag(stag);
            ListViewEntityActivity.ENTITIES.getEntities().add(e);
        }

        finish();
    }
}
