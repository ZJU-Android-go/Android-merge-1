package cc.vipazoo.www.ui;

import android.app.ListActivity;
// import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListVIewActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        setListAdapter(new SimpleAdapter(this, getData(), R.layout.list_item, new String[]{"title", "description"}, new int[]{R.id.list_view_text1, R.id.list_view_text2}));
    }

    private List<Map<String, String>> getData() {
        List<Map<String, String>> listData = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Map<String, String> map = new HashMap<>();
            map.put("title", "test " + i);
            map.put("description", "This is the description of the test" + i);
            listData.add(map);
        }

        return listData;
    }
}
