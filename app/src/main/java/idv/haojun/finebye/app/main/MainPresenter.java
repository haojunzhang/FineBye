package idv.haojun.finebye.app.main;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import idv.haojun.finebye.R;
import idv.haojun.finebye.adapter.BaseRecyclerViewAdapter;
import idv.haojun.finebye.adapter.ThemeColorRVAdapter;
import idv.haojun.finebye.data.DrawerItem;
import idv.haojun.finebye.helper.SPHelper;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private Context context;

    MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.context = (Context) mView;
    }

    private AlertDialog ad;

    private void themePickDialog() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_theme_pick, null);
        RecyclerView rv = v.findViewById(R.id.rv_dialog_theme_pick);

        final List<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(context, R.color.colorBluePrimary));
        colors.add(ContextCompat.getColor(context, R.color.colorRedAccent));
        colors.add(ContextCompat.getColor(context, R.color.colorGreenAccent));

        ThemeColorRVAdapter adapter = new ThemeColorRVAdapter(context, R.layout.item_rv_theme_pick);
        rv.setLayoutManager(new GridLayoutManager(context, 3));
        rv.setAdapter(adapter);
        adapter.setData(colors);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (SPHelper.getThemeColor(context) != colors.get(position)) {
                    ad.dismiss();
                    SPHelper.setThemeColor(context, colors.get(position));
                    mView.openWelcomeActivity();
                }
            }
        });

        ad = new AlertDialog.Builder(context)
                .setTitle(R.string.theme)
                .setView(v)
                .show();
    }

    @Override
    public void getAvatar() {
        String avatarUrl = SPHelper.getAvatarUrl(context);
        if (!avatarUrl.isEmpty())
            mView.displayAvatarUrl(avatarUrl);
    }

    @Override
    public void setAvatarUrlDialog() {
        final EditText et = new EditText(context);
        new AlertDialog.Builder(context)
                .setView(et)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String avatarUrl = et.getText().toString();
                        if (avatarUrl.isEmpty()) {
                            return;
                        }
                        SPHelper.setAvatarUrl(context, avatarUrl);
                        mView.displayAvatarUrl(avatarUrl);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onDrawerItemClick(DrawerItem drawerItem) {
        switch (drawerItem.getId()) {
            case DrawerItem.MAP:
                break;
            case DrawerItem.WARNING_SETTING:
                break;
            case DrawerItem.FINE_BOT:
                break;
            case DrawerItem.SEARCH:
                break;
            case DrawerItem.THEME:
                themePickDialog();
                break;
        }
    }
}
