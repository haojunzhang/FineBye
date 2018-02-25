package idv.haojun.finebye.app.welcome;

public interface WelcomeContract {

    interface View {

        void openMainActivity();
    }

    interface Presenter {

        void displayWelcome();
    }
}
