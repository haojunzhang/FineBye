package idv.haojun.finebye.app.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import idv.haojun.finebye.R;
import idv.haojun.finebye.adapter.BaseRecyclerViewAdapter;
import idv.haojun.finebye.adapter.DrawerRVAdapter;
import idv.haojun.finebye.app.welcome.WelcomeActivity;
import idv.haojun.finebye.base.App;
import idv.haojun.finebye.base.BaseActivity;
import idv.haojun.finebye.data.DrawerItem;
import idv.haojun.finebye.util.ImageLoader;

public class MainActivity extends BaseActivity implements MainContract.View, BaseRecyclerViewAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.dl_main)
    DrawerLayout dl;

    @BindView(R.id.iv_main_avatar_background)
    ImageView iv_avatar_background;

    @BindView(R.id.iv_main_avatar)
    ImageView iv_avatar;

    @BindView(R.id.rv_main_drawer)
    RecyclerView rv;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initDrawer();

        mPresenter = new MainPresenter(this);
        mPresenter.getAvatar();
    }

    private void initDrawer() {

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dl,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        dl.addDrawerListener(toggle);
        toggle.syncState();

        List<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(DrawerItem.MAP, R.drawable.ic_map, R.string.map));
        drawerItems.add(new DrawerItem(DrawerItem.WARNING_SETTING, R.drawable.ic_warning, R.string.warning_setting));
        drawerItems.add(new DrawerItem(DrawerItem.FINE_BOT, R.drawable.ic_fine_bot, R.string.fine_bot));
        drawerItems.add(new DrawerItem(DrawerItem.SEARCH, R.drawable.ic_search, R.string.search));
        drawerItems.add(new DrawerItem(DrawerItem.THEME, R.drawable.ic_theme, R.string.theme));


        DrawerRVAdapter adapter = new DrawerRVAdapter(this, R.layout.item_rv_drawer);
        adapter.setData(drawerItems);
        adapter.setOnItemClickListener(this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(adapter);

    }

    @Override
    public void displayAvatarUrl(String avatarUrl) {
        ImageLoader.loadBlur(this, avatarUrl, iv_avatar_background);
        ImageLoader.load(this, avatarUrl, iv_avatar);
    }

    @Override
    public void openWelcomeActivity() {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }

    @OnClick({R.id.iv_main_avatar, R.id.iv_main_avatar_background})
    public void onAvatarClick() {
        mPresenter.setAvatarUrlDialog();
    }

    private long lastBackPressedTime = 0;

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - lastBackPressedTime > 2000) {
                lastBackPressedTime = System.currentTimeMillis();
            } else {
                App.getInstance().exitApp();
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        }
        mPresenter.onDrawerItemClick(((DrawerRVAdapter) rv.getAdapter()).getData().get(position));
    }
}
