package ru.kvisaz.wotolenemer.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Formatter;
import java.util.Locale;

import javax.inject.Inject;

import ru.kvisaz.wotolenemer.App;
import ru.kvisaz.wotolenemer.Presenter;
import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.model.UserModel;
import ru.kvisaz.wotolenemer.utilits.DateUtil;
import ru.kvisaz.wotolenemer.view.detail.TitleBar;
import ru.kvisaz.wotolenemer.view.output.ColorGetter;
import ru.kvisaz.wotolenemer.view.output.Rate;
import ru.kvisaz.wotolenemer.view.output.OutputUserInfoStrings;
import ru.kvisaz.wotolenemer.view.output.PictureGet;
import ru.kvisaz.wotolenemer.view.output.Status;

public class OutputUserInfoView extends EventSubscriber {
    @Inject
    Presenter presenter;

    private final TitleBar titleBar;
    private final ImageView imageViewOnTop;

    private final TextView percentTextView;
    private final TextView statusTextView;

    private final OutputUserInfoList infoList;
    private final OutputUserInfoStrings infoStrings;

    public OutputUserInfoView(View rootView) {
        App.getAppComponent().inject(this);

        titleBar = new TitleBar(rootView);
        imageViewOnTop = (ImageView) rootView.findViewById(R.id.detail_picture_top);

        percentTextView = (TextView) rootView.findViewById(R.id.info_win_percent_percent);
        statusTextView = (TextView) rootView.findViewById(R.id.info_status);

        infoList = new OutputUserInfoList(rootView);
        infoStrings = new OutputUserInfoStrings();
    }

    public void show(UserModel user) {
        titleBar.setText(user.nickname);


        int rate = Rate.get(user.percentOfWins, user.battles_all);
        setPicture(rate);
        setPercent(user.percentOfWins, rate);
        setStatus(rate);

        setInfoList(user);
    }


    // ------------------ View change functions -----------------------
    private void setPicture(int rate) {
        int PictureRes = PictureGet.getPictureResforRating(rate);
        imageViewOnTop.setImageResource(PictureRes);
    }

    private void setPercent(float percent, int rate) {
        Formatter formatter = new Formatter();
        formatter.format("%10.2f", percent);
        percentTextView.setText(formatter.toString().trim());

        int percentColor = ColorGetter.get(rate);
        percentTextView.setTextColor(percentColor);
    }

    private void setStatus(int rate) {
        statusTextView.setText(Status.get(rate));
        int percentColor = ColorGetter.get(rate);
        statusTextView.setBackgroundColor(percentColor);
    }

    private void setInfoList(UserModel user) {
        infoList.clear();

        String title;
        String body;
        float average;
        // Количество боёв
        title = infoStrings.BattlesTotal + " " + user.battles_all;
        body = infoStrings.BattlesRandom + " " + user.battles_random;
        infoList.add(title, body);

        // дата регистрации
        title = infoStrings.DateReg + " " + DateUtil.getRuDate(user.created_at);
        body = infoStrings.DateLast + " " + DateUtil.getRuDate(user.last_battle_time);
        infoList.add(title, body);

        // Рейтинг ВГ + хинт
        title = infoStrings.ratingTitle + " " + user.global_rating;
        body = infoStrings.ratingDesc;
        infoList.add(title, body);

        // Уничтожил врагов + в среднем
        title = infoStrings.killedEnemiesTotal + " " + user.frags_total;
        body = infoStrings.killedEnemiesAverage + " " + String.format("%.2f", user.frags_average);
        infoList.add(title, body);

        // выстрелы, попадания, пробития
        title = infoStrings.shootsPerBattle + " " + String.format("%.2f", user.shots_average);
        body = infoStrings.hitsPerBattle + " " + String.format("%.2f", user.hits_average)
                + " / " + infoStrings.piercesPerBattle + " " + String.format("%.2f", user.piercings_average);
        infoList.add(title, body);

        // засвет, дамаг по свету, дамаг по гусле
        title = infoStrings.spottedAverage + " " + String.format("%.2f", user.spotted_average);
        body = infoStrings.scoutDamagedAverage + " " + user.damage_assisted_radio_average;
        infoList.add(title, body);

        // база
        title = infoStrings.baseCaptureAverage + " " + String.format("%.2f", user.base_captured_average);
        body = infoStrings.baseDefenseAverage + " " + String.format("%.2f", user.base_defense_average);
        infoList.add(title, body);

        // язык
        title = infoStrings.languageTitle;
        body = infoStrings.languageDesc + " " + user.client_language;
        infoList.add(title, body);

        infoList.refresh();
    }
}