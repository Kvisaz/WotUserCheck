package ru.kvisaz.wotolenemer;

import android.app.Application;

import ru.kvisaz.wotolenemer.dagger.AppComponent;
import ru.kvisaz.wotolenemer.dagger.AppModule;
import ru.kvisaz.wotolenemer.dagger.DaggerAppComponent;
import ru.kvisaz.wotolenemer.dagger.PresenterModule;

public class App extends Application {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent buildAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .presenterModule(new PresenterModule(this))
                .build();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        appComponent = buildAppComponent();
        Constants.getResources(this);
    }
}
