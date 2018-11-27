package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.animation.TranslateAnimation;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.controller.TripletController;
import cc.vipazoo.www.ui.model.User;

public class TripletActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;

    private float downX;
    private float downY;
    // if the bottom widget can be seen
    private boolean if_show = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triplet);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_triplet);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("TO NAV_TRIPLET");
////////////////////////////////////////////////////////////////////////////////

        // get the first triplet
        set_triplet();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_triplet);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.triplet_entity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_add_relation) {
            startActivity(new Intent(this, AddTripletActivity.class));
            return true;
        }
        else if (id == R.id.action_new_article) {
            set_triplet();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_triplet) {
            // Do nothing
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout_triplet);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // when touch down
        float x = event.getX();
        float y = event.getY();
        // switch the gesture
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // save the pos when touching
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_UP:
                // get the subtraction
                float dx = x - downX;
                float dy = y - downY;
                // give a fault-tolerance
                if(Math.abs(dx) > 8 && Math.abs(dy) > 8) {
                    int orientation = getOrientation(dx, dy);
                    switch (orientation) {
                        case 'r':   // right
                            break;
                        case 'l':   // left
                            break;
                        case 't':   // top
                            ConstraintLayout layout1 = findViewById(R.id.constrain_whole);
                            TranslateAnimation ta1 = new TranslateAnimation(0.0f, 0.0f, 0.0f, 300.0f);
                            ta1.setDuration(500);
                            ta1.setFillAfter(true);
                            if (if_show) {
                                layout1.startAnimation(ta1);
                                if_show = false;
                            }
                            break;
                        case 'b':   // bottom
                            ConstraintLayout layout2 = findViewById(R.id.constrain_whole);
                            TranslateAnimation ta2 = new TranslateAnimation(0.0f, 0.0f, 300.0f, 0.0f);
                            ta2.setDuration(500);
                            ta2.setFillAfter(true);
                            if (!if_show) {
                                layout2.startAnimation(ta2);
                                if_show = true;
                            }
                            break;
                    }
                }
                // if just a touch
                else {
                    ConstraintLayout layout2 = findViewById(R.id.constrain_whole);
                    TranslateAnimation ta2 = new TranslateAnimation(0.0f, 0.0f, 300.0f, 0.0f);
                    ta2.setDuration(500);
                    ta2.setFillAfter(true);
                    if (!if_show) {
                        layout2.startAnimation(ta2);
                        if_show = true;
                    }
                }
                break;
            default: break;
        }
        return super.dispatchTouchEvent(event);
    }

    private int getOrientation(float dx, float dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            // x axis movement
            return dx > 0 ? 'r' : 'l';
        }
        else {
            // y axis movement
            return dy > 0 ? 'b' : 't';
        }
    }

    private void set_triplet() {
        TripletController tripletController = new TripletController(user);
//        tripletController.getTriplets();
    }
}
