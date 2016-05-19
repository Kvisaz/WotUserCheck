package ru.kvisaz.wotolenemer.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.kvisaz.wotolenemer.MainActivity;
import ru.kvisaz.wotolenemer.db.DatabaseWorker;
import ru.kvisaz.wotolenemer.view.DetailBoxView;
import ru.kvisaz.wotolenemer.view.InputView;
import ru.kvisaz.wotolenemer.view.OutputUserInfoView;
import ru.kvisaz.wotolenemer.view.detail.SharedButton;
import ru.kvisaz.wotolenemer.view.output.OutputUserInfoStrings;
import ru.kvisaz.wotolenemer.view.output.viewpager.DetailPagerAdapter;

@Component (dependencies = AppModule.class,modules = {PresenterModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(InputView inputView);
    void inject(OutputUserInfoStrings outputUserInfoStrings);
    void inject(OutputUserInfoView outputUserInfoView);
    void inject(SharedButton sharedButton);
    void inject(DetailPagerAdapter detailPagerAdapter);
    void inject(DetailBoxView detailBoxView);
}
