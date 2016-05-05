package ru.kvisaz.wotolenemer.dagger;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

import ru.kvisaz.wotolenemer.Presenter;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    Presenter providesPresenter(Context context){
        return new Presenter(context);
    }
}
