package cc.vipazoo.www.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import cc.vipazoo.www.ui.controller.ArticleController;
import cc.vipazoo.www.ui.model.Article;
import cc.vipazoo.www.ui.model.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;

    private float downX;
    private float downY;
    // if the bottom widget can be seen
    private boolean if_show = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // initialize token from LoginActivity
        Intent intent = getIntent();
        Serializable se = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        user = (User) se;
        // get the first article
        ArticleController articleController = new ArticleController(user);

        TextView article = findViewById(R.id.article);
        article.setText(articleController.getArticle());
        article.setMovementMethod(ScrollingMovementMethod.getInstance());
        // get the first triplet

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent intent = new Intent(this, AddRelationActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_new_article) {
            // get the article from server
            ArticleController articleController = new ArticleController(user);

            TextView article = findViewById(R.id.article);
            article.setText(articleController.getArticle());
            article.setMovementMethod(ScrollingMovementMethod.getInstance());
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }
        else if (id == R.id.nav_gallery) {

        }
        else if (id == R.id.nav_slideshow) {

        }
        else if (id == R.id.nav_manage) {

        }
        else if (id == R.id.nav_share) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
}
