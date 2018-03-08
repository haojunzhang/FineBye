package idv.haojun.finebye.app.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import idv.haojun.finebye.R;
import idv.haojun.finebye.adapter.BaseRecyclerViewAdapter;
import idv.haojun.finebye.adapter.SearchRVAdapter;
import idv.haojun.finebye.base.BaseActivity;
import idv.haojun.finebye.data.Cam;

public class SearchActivity extends BaseActivity implements SearchContract.View, BaseRecyclerViewAdapter.OnItemClickListener {

    @BindView(R.id.et_search_keyword)
    EditText et_keyword;

    @BindView(R.id.rv_search)
    RecyclerView rv;

    private SearchContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initTitle(R.string.search);
        ButterKnife.bind(this);

        et_keyword.addTextChangedListener(textWatcher);
        SearchRVAdapter adapter = new SearchRVAdapter(this, R.layout.item_rv_search);
        adapter.setOnItemClickListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        mPresenter = new SearchPresenter(this);
        mPresenter.getCams();
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPresenter.onTextChanged(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onItemClick(View view, int position) {
        List<Cam> cams = ((SearchRVAdapter) rv.getAdapter()).getData();
        Cam cam = cams.get(position);
        setResult(
                RESULT_OK,
                new Intent()
                        .putExtra("latitude", Double.valueOf(cam.getLatitude()))
                        .putExtra("longitude", Double.valueOf(cam.getLongitude()))
        );
        finish();
    }

    @Override
    public void displayCams(List<Cam> cams) {
        ((SearchRVAdapter) rv.getAdapter()).setData(cams);
    }
}
