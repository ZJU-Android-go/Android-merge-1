package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.model.User;

public class ListViewEntityActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_entity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_view_entity);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("TO MY_ENTITY");
////////////////////////////////////////////////////////////////////////////////

        ListView listView = findViewById(R.id.list_view_entity);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),
                R.layout.list_item_entity, new String[]{"title", "description"}, new int[]{R.id.list_view_entity_text1, R.id.list_view_entity_text2});
        listView.setAdapter(simpleAdapter);
    }

    private List<Map<String, String>> getData() {
        List<Map<String, String>> listData = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            Map<String, String> map = new HashMap<>();
            map.put("title", "test " + i);
            map.put("description", "This is the description of the test" + i);
            listData.add(map);
        }

        return listData;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_view_entity);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_triplet) {
            Intent intent = new Intent(this, TripletActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TO NAV_TRIPLET", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.my_triplet) {

        }
        else if (id == R.id.nav_entity) {

        }
        else if (id == R.id.my_entity) {
            // DO nothing
        }
        else if (id == R.id.nav_settings) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_view_entity);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
