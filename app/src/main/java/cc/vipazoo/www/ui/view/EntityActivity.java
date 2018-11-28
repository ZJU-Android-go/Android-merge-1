package cc.vipazoo.www.ui.view;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import cc.vipazoo.www.ui.R;
import cc.vipazoo.www.ui.controller.ArticleController;
import cc.vipazoo.www.ui.model.Entities;
import cc.vipazoo.www.ui.model.User;

public class EntityActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;
    private ActionMode.Callback callback_entity;

    // if finished
    static public boolean if_finished = true;

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
        if (if_finished) set_article();
        else {
            TextView maintitle = findViewById(R.id.maintitle);
            TextView subtitle = findViewById(R.id.subtitle);
            TextView article = findViewById(R.id.article_entity);
            // separate title and subtitle
            String title = ListViewEntityActivity.ARTICLE.getTitle();
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
            article.setText(ListViewEntityActivity.ARTICLE.getContent());

        }

        // required by my teammate, to deal with text

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TextView textView = findViewById(R.id.article_entity);
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
                            // ConstraintLayout add_entity = findViewById(R.id.constraint_layout_add_entity);
                            Intent intent1 = new Intent(EntityActivity.this, AddEntityActivity.class);
                            TextView content = findViewById(R.id.article_entity);
                            int start = content.getSelectionStart();
                            int end = content.getSelectionEnd();
                            String s = String.valueOf(content.getText().subSequence(start, end));
                            intent1.putExtra("add_entity_entity", s);
                            intent1.putExtra("add_entity_start", start);
                            intent1.putExtra("add_entity_end", end);
                            startActivity(intent1);
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
            Intent intent = new Intent(this, ListViewTripletActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("TO MY_TRIPLET", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.nav_entity) {
            // Do nothing
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout_entity);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void set_article() {
        ArticleController articleController = new ArticleController(user);
        TextView maintitle = findViewById(R.id.maintitle);
        TextView subtitle = findViewById(R.id.subtitle);
        TextView article = findViewById(R.id.article_entity);

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

        ListViewEntityActivity.ARTICLE = articleController.getArticle();
        ListViewEntityActivity.ENTITIES = new Entities();
        ListViewEntityActivity.ENTITIES.setDoc_id(ListViewEntityActivity.ARTICLE.getDoc_id());
        ListViewEntityActivity.ENTITIES.setSend_id(ListViewEntityActivity.ARTICLE.getSend_id());
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
        if_finished = false;
        article.setText(articleController.getArticle().getContent());
        // comment the following statement to enable the selection
 //        article.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    // following gets a sentence and select entity and tag
}







