package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.controller.UploadController;
import cc.vipazoo.www.ui.model.Triplet;
import cc.vipazoo.www.ui.model.Triplets;
import cc.vipazoo.www.ui.model.User;

public class ListViewTripletActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static Triplets TRIPLET = new Triplets();

    private User user;
//    private List<Map<String, String>> listData;
    private int item_selected = -1;

    static HashMap<Integer, String> relationMap = new HashMap<Integer, String>() {
        {
            put(0, "任职");
            put(1, "亲属");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_triplet);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_view_triplet);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("TO MY_TRIPLET");
////////////////////////////////////////////////////////////////////////////////

        ListView listView = findViewById(R.id.list_view_triplet);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),
                R.layout.list_item_triplet, new String[]{"entity1", "entity2", "relation"}, new int[]{R.id.list_view_triplet_entity1, R.id.list_view_triplet_entity2, R.id.list_view_triplet_relation});
        listView.setAdapter(simpleAdapter);

        // register for menu
        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // return false to continue the message
                item_selected = i;
                return false;
            }
        });
    }

    private List<Map<String, String>> getData() {
        ArrayList<Triplet> triplets = TRIPLET.getTriplets();
        List<Map<String, String>> listData = new ArrayList<>();
        for (Triplet t: triplets) {
            Map<String, String> map = new HashMap<>();
            map.put("entity1", t.getLeft_entity());
            map.put("entity2", t.getRight_entity());
            map.put("relation", relationMap.get(t.getRelation_id()));
            listData.add(map);
        }

        return listData;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.selection_show, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.selection_show_edit:
                break;
            case R.id.selection_show_remove:
                TRIPLET.getTriplets().remove(item_selected);
                ListView listView = findViewById(R.id.list_view_triplet);
                SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(),
                        R.layout.list_item_triplet, new String[]{"entity1", "entity2", "relation"}, new int[]{R.id.list_view_triplet_entity1, R.id.list_view_triplet_entity2, R.id.list_view_triplet_relation});
                listView.setAdapter(simpleAdapter);
                break;
            default: break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_view_triplet);
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
            // Do nothing
        }
        else if (id == R.id.nav_entity) {
            Intent intent = new Intent(this, EntityActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TO NAV_ENTITY", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.my_entity) {
            Intent intent = new Intent(this, ListViewEntityActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TO MY_ENTITY", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.nav_settings) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_view_triplet);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void uploadTriplets(View view) {
        UploadController uploadController = new UploadController(TRIPLET);
        uploadController.setUser(user);
        try{
            Log.e("Before uploadEntities", "OK");
            uploadController.upload_triplets();
            Log.e("After uploadEntities", "OK");
            Toast.makeText(this, uploadController.upload_ret, Toast.LENGTH_SHORT).show();
            TripletActivity.if_finished = true;
        }
        catch(Exception e)
        {
            Log.e("uploadController", "ERROR");
            Toast.makeText(this, uploadController.upload_ret, Toast.LENGTH_SHORT).show();

        }
    }
}
