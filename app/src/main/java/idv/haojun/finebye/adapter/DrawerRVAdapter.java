package idv.haojun.finebye.adapter;

import android.content.Context;

import idv.haojun.finebye.R;
import idv.haojun.finebye.data.DrawerItem;

public class DrawerRVAdapter extends BaseRecyclerViewAdapter<DrawerItem> {

    public DrawerRVAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void convert(Context context, BaseViewHolder holder, DrawerItem drawerItem) {
        holder.setImageResource(R.id.iv_item_rv_drawer_icon, drawerItem.getIconId())
                .setText(R.id.tv_item_rv_drawer_title, drawerItem.getTitleId());
    }
}
