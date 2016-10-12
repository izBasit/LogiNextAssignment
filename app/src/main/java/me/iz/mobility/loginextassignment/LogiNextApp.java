package me.iz.mobility.loginextassignment;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import io.realm.Realm;
import me.iz.mobility.loginextassignment.dagger.components.AppComponent;
import me.iz.mobility.loginextassignment.dagger.components.DaggerAppComponent;
import me.iz.mobility.loginextassignment.dagger.modules.AppModule;
import me.iz.mobility.loginextassignment.impl.DummyDataCreator;
import me.iz.mobility.loginextassignment.utils.Constants;
import me.iz.mobility.loginextassignment.utils.FakeCrashLibrary;
import me.iz.mobility.loginextassignment.utils.Preferences;
import timber.log.Timber;

/**
 * Created by ibasit on 10/10/2016.
 */

public class LogiNextApp extends Application {

    private static Context mContext;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        Realm.init(this);
        Preferences.init(mContext);

        loadInitialData();



        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public static Context getContext() {
        return mContext;
    }

    public static LogiNextApp from(@NonNull Context context) {
        return (LogiNextApp) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

    /**
     * Method puts dummy data into the realm for the first time
     */
    private void loadInitialData() {
        boolean hasDummyData = Preferences.getBoolean(Constants.FIRSTTIME);

        if(hasDummyData)
            return;

        // dumping data into Realm
        DummyDataCreator.dumpData();
        Preferences.saveBoolean(Constants.FIRSTTIME, true);
    }
}
