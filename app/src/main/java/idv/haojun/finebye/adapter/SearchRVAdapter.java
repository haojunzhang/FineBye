package idv.haojun.finebye.adapter;

import android.content.Context;

import idv.haojun.finebye.R;
import idv.haojun.finebye.data.Cam;

public class SearchRVAdapter extends BaseRecyclerViewAdapter<Cam> {

    public SearchRVAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void convert(Context context, BaseViewHolder holder, Cam cam) {
        holder.setText(R.id.tv_item_rv_search_city_name, cam.getCityName())
                .setText(R.id.tv_item_rv_search_region_name, cam.getRegionName())
                .setText(R.id.tv_item_rv_search_address, cam.getAddress())
                .setText(R.id.tv_item_rv_search_deptNm, cam.getDeptNm())
                .setText(R.id.tv_item_rv_search_branchNm, cam.getBranchNm())
                .setText(R.id.tv_item_rv_search_longitude, cam.getLongitude())
                .setText(R.id.tv_item_rv_search_latitude, cam.getLatitude())
                .setText(R.id.tv_item_rv_search_direct, cam.getDirect())
                .setText(R.id.tv_item_rv_search_limit, cam.getLimit());
    }
}
