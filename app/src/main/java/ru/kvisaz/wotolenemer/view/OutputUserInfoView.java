package ru.kvisaz.wotolenemer.view;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.model.UserInfo;

public class OutputUserInfoView {
    private final TextView textView;

    public OutputUserInfoView(View rootView){
        textView = (TextView)rootView.findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void show(UserInfo userInfo){

        String name = userInfo.nickname;

        int wins = userInfo.statistics.random.wins;
        int battles = userInfo.statistics.random.battles;
        float rating = (float)wins / (float)battles * 100;
        Formatter formatter = new Formatter();
        formatter.format("%10.2f",rating);
        String message = userInfo.nickname + " имеет процент побед в Рандоме = " + formatter.toString();
        message = message + "\n" + "всего боев: " + userInfo.statistics.random.battles;
        message = message + "\n" + "глобальный рейтинг Wargaming: " + userInfo.global_rating;
      // таймштамп не расшифровывается
      //  message = message + "\n" + "зарегистрировался: " + new Date(Integer.parseInt(userInfo.created_at));;

        textView.setText(message);
    }
}
