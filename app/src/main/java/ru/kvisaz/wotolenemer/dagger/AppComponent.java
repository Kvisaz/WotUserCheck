package ru.kvisaz.wotolenemer.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.kvisaz.wotolenemer.MainActivity;
import ru.kvisaz.wotolenemer.view.InputView;

@Component (dependencies = AppModule.class,modules = {})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(InputView inputView);
}
