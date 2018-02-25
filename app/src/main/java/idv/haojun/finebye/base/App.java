package idv.haojun.finebye.base;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;
import java.util.Set;

import idv.haojun.finebye.data.MyObjectBox;
import io.objectbox.BoxStore;
import io.objectbox.BoxStoreBuilder;

public class App extends Application {
    private static App instance;
    private Set<Activity> activities;
    private BoxStore boxStore;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
    }

    public BoxStore getBoxStore(){
        return boxStore;
    }

    public void registerActivity(Activity activity) {
        if (activities == null) {
            activities = new HashSet<>();
        }
        activities.add(activity);
    }

    public void unregisterActivity(Activity activity) {
        if (activities != null)
            activities.remove(activity);
    }

    public void exitApp() {
        if (activities != null) {
            synchronized (activities) {
                for (Activity act : activities) {
                    if (act != null && !act.isFinishing())
                        act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
