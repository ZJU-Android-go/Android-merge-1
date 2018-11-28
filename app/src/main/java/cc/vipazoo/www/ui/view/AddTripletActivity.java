package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.model.Triplet;

public class AddTripletActivity extends AppCompatActivity {

    private String pre = null;
    private int start, end;
    private String sentence = null;
    private ActionMode.Callback callback_entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_triplet);

        Intent intent = getIntent();
        pre = intent.getStringExtra("add_triplet_entity");
        start = intent.getIntExtra("add_entity_start", -1);
        end = intent.getIntExtra("add_entity_end", -1);
        sentence = intent.getStringExtra("add_triplet_sentence");

        if (sentence != null) {
            final TextView textView = findViewById(R.id.textview_hidden);
            textView.setText(sentence);
            textView.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textView.setTextIsSelectable(true);

                callback_entity = new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        MenuInflater inflater = actionMode.getMenuInflater();
                        inflater.inflate(R.menu.selection_entity_triplet, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.selection_entity:
                                TextView content = findViewById(R.id.textview_hidden);
                                TextView entity2 = findViewById(R.id.AddTripletEntity2);
                                int start = content.getSelectionStart();
                                int end = content.getSelectionEnd();
                                String s = String.valueOf(content.getText().subSequence(start, end));
                                entity2.setText(s);
                                content.clearFocus();
                                break;
                            default:
                                return false;
                        }

                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {
                    }
                };
                textView.setCustomSelectionActionModeCallback(callback_entity);
            }

            EditText editText = findViewById(R.id.AddTripletEntity1);
            editText.setText(pre);

        }
    }

    public void AddTriplet(View view) {
        EditText entity1 = findViewById(R.id.AddTripletEntity1);
        EditText entity2 = findViewById(R.id.AddTripletEntity2);
        EditText relation = findViewById(R.id.AddTripletRelation);
        String sentity1 = entity1.getText().toString();
        String sentity2 = entity2.getText().toString();
        String srelation = relation.getText().toString();

        if (!ListViewTripletActivity.relationMap.containsValue(srelation)) {
            Toast.makeText(this, "关系不存在", Toast.LENGTH_SHORT);
            return;
        }
        else {
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

                for (int i : ListViewTripletActivity.relationMap.keySet()) {
                    if (ListViewTripletActivity.relationMap.get(i).equals(srelation)) {
                        t.setRelation_id(i);
                        break;
                    }
                }

                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT);
                ListViewTripletActivity.TRIPLET.getTriplets().add(t);
            }
            finish();
        }
    }
}
