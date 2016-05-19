package ru.kvisaz.wotolenemer.dagger;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

import ru.kvisaz.wotolenemer.Presenter;

@Module
public class PresenterModule {
    private final Presenter presenter;
    public PresenterModule(Context context){
        presenter = new Presenter(context);
    }

    @Provides
    Presenter providesPresenter(){
        return presenter;
    }
}
