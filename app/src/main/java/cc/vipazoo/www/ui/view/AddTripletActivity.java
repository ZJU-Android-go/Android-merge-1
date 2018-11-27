package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.model.Triplet;

public class AddTripletActivity extends AppCompatActivity {

    private String pre;
    private int start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_triplet);

        Intent intent = getIntent();
        pre = intent.getStringExtra("add_triplet_entity");
        start = intent.getIntExtra("add_entity_start", -1);
        end = intent.getIntExtra("add_entity_end", -1);

        EditText editText = findViewById(R.id.AddTripletEntity1);
        editText.setText(pre);

    }

    public void AddTriplet(View view) {
        EditText entity1 = findViewById(R.id.AddTripletEntity1);
        EditText entity2 = findViewById(R.id.AddTripletEntity2);
        EditText relation = findViewById(R.id.AddTripletRelation);
        String sentity1 = entity1.getText().toString();
        String sentity2 = entity2.getText().toString();
        String srelation = relation.getText().toString();

        // HERE to add the Relation to the other activity
        if (pre == null) {

        }
        else {
            Triplet t = new Triplet();
            t.setLeft_e_start(start);
            t.setLeft_e_end(end);
            t.setLeft_entity(sentity1);
            // set right position here

            t.setRight_entity(sentity2);


            ListViewTripletActivity.TRIPLET.getTriplets().add(t);
        }
        finish();
    }
}
