package ru.kvisaz.wotolenemer.view.output.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

import ru.kvisaz.wotolenemer.App;
import ru.kvisaz.wotolenemer.Presenter;
import ru.kvisaz.wotolenemer.model.UserModel;

public class DetailPagerAdapter extends FragmentStatePagerAdapter {
    @Inject
    Presenter presenter;

    public DetailPagerAdapter(FragmentManager fm) {
        super(fm);
        App.getAppComponent().inject(this);
    }

    @Override
    public Fragment getItem(int position) {
        UserModel userModel = presenter.getUser(position);
        DetailPageFragment fragment = DetailPageFragment.getInstance(userModel);
        return fragment;
    }

    @Override
    public int getCount() {
        return presenter.history.size();
    }

}
