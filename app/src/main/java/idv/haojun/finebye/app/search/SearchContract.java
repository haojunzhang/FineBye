package idv.haojun.finebye.app.search;

import java.util.List;

import idv.haojun.finebye.data.Cam;

public interface SearchContract {
    interface View{
        void displayCams(List<Cam> cams);
    }
    interface Presenter{
        void onTextChanged(CharSequence keyword);

        void getCams();
    }
}
