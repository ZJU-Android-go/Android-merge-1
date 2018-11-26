package cc.vipazoo.www.ui.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cc.vipazoo.www.ui.R;

public class AddTripletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_triplet);
    }

    public void AddTriplet(View view) {
        EditText entity1 = findViewById(R.id.AddTripletEntity1);
        EditText entity2 = findViewById(R.id.AddTripletEntity2);
        EditText relation = findViewById(R.id.AddTripletRelation);
        String sentity1 = entity1.getText().toString();
        String sentity2 = entity2.getText().toString();
        String srelation = relation.getText().toString();

        // HERE to add the Relation to the other activity

        finish();
    }
}
