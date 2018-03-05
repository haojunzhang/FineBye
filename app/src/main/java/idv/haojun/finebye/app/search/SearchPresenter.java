package idv.haojun.finebye.app.search;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import idv.haojun.finebye.base.App;
import idv.haojun.finebye.data.Cam;
import io.objectbox.Box;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;
    private Context context;
    private List<Cam> cams;

    SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
        this.context = (Context) mView;
        this.cams = new ArrayList<>();
    }

    @Override
    public void onTextChanged(CharSequence keyword) {
        List<Cam> result = new ArrayList<>();
        for (Cam cam : cams) {
            if (cam.getCityName().contains(keyword) ||
                    cam.getRegionName().contains(keyword) ||
                    cam.getAddress().contains(keyword) ||
                    cam.getDeptNm().contains(keyword) ||
                    cam.getBranchNm().contains(keyword) ||
                    cam.getDirect().contains(keyword) ||
                    cam.getLimit().contains(keyword)) {
                result.add(cam);
            }
        }
        mView.displayCams(result);
    }

    @Override
    public void getCams() {
        final Box<Cam> camBox = ((App) ((Activity) mView).getApplication()).getBoxStore().boxFor(Cam.class);
        cams = camBox.query().build().find();
        cams.remove(0);
        mView.displayCams(cams);
    }
}
