package idv.haojun.finebye.adapter;

import android.content.Context;

import idv.haojun.finebye.R;

public class ThemeColorRVAdapter extends BaseRecyclerViewAdapter<Integer> {

    public ThemeColorRVAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void convert(Context context, BaseViewHolder holder, Integer color) {
        holder.setBackgroundColor(R.id.view_item_rv_theme_pick, color);
    }
}
