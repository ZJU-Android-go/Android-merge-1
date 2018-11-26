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
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.controller.ArticleController;
import cc.vipazoo.www.ui.model.User;

public class EntityActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;

    private float downX;
    private float downY;
    // if the bottom widget can be seen
    private boolean if_show = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_entity);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("TO NAV_ENTITY");
////////////////////////////////////////////////////////////////////////////////
        // get the first article
        set_article();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_entity);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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
            Intent intent = new Intent(this, AddEntityActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_new_article) {
            set_article();
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
            startActivity(new Intent(this, ListViewEntityActivity.class));
        }
        else if (id == R.id.nav_settings) {

        }
        else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_entity);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    private void set_article() {
        ArticleController articleController = new ArticleController(user);
        TextView maintitle = findViewById(R.id.maintitle);
        TextView subtitle = findViewById(R.id.subtitle);
        TextView article = findViewById(R.id.article);

        // get the article
        articleController.getArticleFromServer();

        // wait until the server returns
        while (articleController.getArticle().getTitle() == null) {
            try {
                article.setText(null);
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // separate title and subtitle
        String title = articleController.getArticle().getTitle();
        String[] title_list = title.split("\\|");
        // check if the title is not consist of 2 parts
        if (title_list.length == 2) {
            maintitle.setText(title_list[0]);
            subtitle.setText(title_list[1]);
        }
        else {
            maintitle.setText(title);
            subtitle.setText(null);
        }

        // set content
        article.setText(articleController.getArticle().getContent());
        article.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
