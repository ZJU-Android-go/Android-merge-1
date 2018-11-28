package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.model.Entity;

public class AddEntityActivity extends AppCompatActivity {

    private String pre, article;
    private int start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entity);

        Intent intent = getIntent();
        article = intent.getStringExtra("add_entity_article");
        pre = intent.getStringExtra("add_entity_entity");
        start = intent.getIntExtra("add_entity_start", -1);
        end = intent.getIntExtra("add_entity_end", -1);

        EditText editText = findViewById(R.id.AddEntityEntity);
        editText.setText(pre);

        Spinner spinner = findViewById(R.id.AddEntityTag);
        String[] tags = {"PERSON", "TITLE"};
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, tags));
    }

    public void AddEntity(View view) {
        EditText entity = findViewById(R.id.AddEntityEntity);
        Spinner tag = findViewById(R.id.AddEntityTag);
        String sentity = entity.getText().toString();
        String stag = tag.getSelectedItem().toString();

        // HERE to add the Relation to the other activity
        start = article.indexOf(sentity);
        while (start == -1) {
            Toast.makeText(this, "未找到实体", Toast.LENGTH_SHORT).show();
            return;
        }
        end = start + sentity.length();

        Entity e = new Entity();
        e.setName(sentity);
        e.setStart(start);
        e.setEnd(end);
        e.setTag(stag);
        ListViewEntityActivity.ENTITIES.getEntities().add(e);

        finish();
    }
}
