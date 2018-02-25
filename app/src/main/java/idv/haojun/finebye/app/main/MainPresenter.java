package idv.haojun.finebye.app.main;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import idv.haojun.finebye.R;
import idv.haojun.finebye.helper.SPHelper;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private Context context;

    MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.context = (Context) mView;
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
}
