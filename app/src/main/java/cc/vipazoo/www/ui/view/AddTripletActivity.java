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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.model.Triplet;

public class AddTripletActivity extends AppCompatActivity {

    private String pre = null, article;
    private int sentence_start;
    private String sentence = null;
    private ActionMode.Callback callback_entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_triplet);

        Intent intent = getIntent();
        article = intent.getStringExtra("add_triplet_article");
        pre = intent.getStringExtra("add_triplet_entity");
        sentence_start = intent.getIntExtra("add_entity_start", -1);
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

        Spinner spinner = findViewById(R.id.AddTripletRelation);
        String[] tags = {"亲属", "任职"};
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, tags));

    }

    public void AddTriplet(View view) {
        EditText entity1 = findViewById(R.id.AddTripletEntity1);
        EditText entity2 = findViewById(R.id.AddTripletEntity2);
        Spinner relation = findViewById(R.id.AddTripletRelation);
        String sentity1 = entity1.getText().toString();
        String sentity2 = entity2.getText().toString();
        String srelation = relation.getSelectedItem().toString();

        // HERE to add the Relation to the other activity
        int start1, start2;
        if (sentence == null) {
            start1 = article.indexOf(sentity1);
            start2 = article.indexOf(sentity2);
        }
        else {
            start1 = article.indexOf(sentity1, sentence_start);
            start2 = article.indexOf(sentity2, sentence_start);
        }

        if (start1 == -1 || start2 == -1) {
            Toast.makeText(this, "未找到实体", Toast.LENGTH_SHORT).show();
            return;
        }

        int end1 = start1 + sentity1.length();
        int end2 = start2 + sentity2.length();

        Triplet t = new Triplet();
        t.setLeft_e_start(start1);
        t.setLeft_e_end(end2);
        t.setLeft_entity(sentity1);
        t.setRight_e_start(start2);
        t.setRight_e_end(end2);
        t.setRight_entity(sentity2);
        // set relation id
        for (int i : ListViewTripletActivity.relationMap.keySet()) {
            if (ListViewTripletActivity.relationMap.get(i).equals(srelation)) {
                t.setRelation_id(i);
                break;
            }
        }

        t.setStatus(1);

        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        ListViewTripletActivity.TRIPLET.getTriplets().add(t);
        finish();
    }
}
