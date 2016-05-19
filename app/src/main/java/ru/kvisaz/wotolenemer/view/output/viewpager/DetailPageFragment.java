package ru.kvisaz.wotolenemer.view.output.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.model.UserModel;
import ru.kvisaz.wotolenemer.view.OutputUserInfoView;

public class DetailPageFragment extends Fragment {
    private UserModel userModel;
    private OutputUserInfoView outputUserInfoView;

    public static final String INFO_TAG = "info_tag";

    public static DetailPageFragment getInstance(UserModel user){
        DetailPageFragment fragment = new DetailPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(INFO_TAG,user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userModel = (UserModel)getArguments().getSerializable(INFO_TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_box_content, container, false);

        outputUserInfoView = new OutputUserInfoView(view);
        outputUserInfoView.show(userModel);

        return view;
    }
}
