package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import cc.vipazoo.www.ui.model.Article;
import cc.vipazoo.www.ui.model.Entities;
import cc.vipazoo.www.ui.model.Entity;
import cc.vipazoo.www.ui.model.User;

public class ListViewEntityActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;
    private List<Map<String, String>> listData;

    static Entities ENTITIES = new Entities();
    static Article ARTICLE = new Article();

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
        listData = getData();

        ListView listView = findViewById(R.id.list_view_entity);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listData,
                R.layout.list_item_entity, new String[]{"entity", "tag"}, new int[]{R.id.list_view_entity_text1, R.id.list_view_entity_text2});
        listView.setAdapter(simpleAdapter);

        // register for menu
        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // return false to continue the message
                return false;
            }
        });

    }

    private List<Map<String, String>> getData() {
        ArrayList<Entity> entities = ENTITIES.getEntities();
        List<Map<String, String>> listData = new ArrayList<>();
        for (Entity e : entities) {
            Map<String, String> map = new HashMap<>();
            map.put("entity", e.getName());
            map.put("tag", e.getTag());
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
                break;
            default: break;
        }
        return super.onContextItemSelected(item);
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
            Intent intent = new Intent(this, ListViewTripletActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TO MY_TRIPLET", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.nav_entity) {
            Intent intent = new Intent(this, EntityActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TO NAV_ENTITY", user);
            intent.putExtras(bundle);
            startActivity(intent);
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

    public void uploadEntities(View view) {
        int try_times = 0;
        UploadController uploadController = new UploadController(ENTITIES);
        uploadController.setUser(user);
        try{
            Log.e("Before uploadEntities", "OK");
            uploadController.upload_entities();
            Log.e("After uploadEntities", "OK");
            while (uploadController.upload_ret == null) {
                try {
                    Thread.sleep(500);
                    try_times++;
                }
                catch (Exception e) {

                }
                if (try_times == 6) {
                    Toast.makeText(this, "上传超时", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Toast.makeText(this, uploadController.upload_ret, Toast.LENGTH_SHORT).show();
            EntityActivity.if_finished = true;
        }
        catch(Exception e)
        {
            Log.e("uploadController", "ERROR");
            Toast.makeText(this, uploadController.upload_ret, Toast.LENGTH_SHORT).show();

        }
    }
}
