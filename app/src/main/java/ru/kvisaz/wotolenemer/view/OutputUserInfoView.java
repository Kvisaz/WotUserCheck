package ru.kvisaz.wotolenemer.view;

import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Formatter;

import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.events.ServerUserInfoEvent;
import ru.kvisaz.wotolenemer.network.model.UserInfo;
import ru.kvisaz.wotolenemer.view.output.PictureGet;

public class OutputUserInfoView extends EventSubscriber{
    private final TextView textView;
    private final ImageView imageViewOnTop;
    private CountDownTimer timer;

    public OutputUserInfoView(View rootView){
        textView = (TextView)rootView.findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        imageViewOnTop = (ImageView)rootView.findViewById(R.id.detail_picture_top);
    }

    public void show(UserInfo userInfo){

        String name = userInfo.nickname;



        int wins = userInfo.statistics.random.wins;
        int battles = userInfo.statistics.random.battles;
        float rating = (float)wins / (float)battles * 100;

        int PictureRes = PictureGet.getPictureResforRating(rating);
        imageViewOnTop.setImageResource(PictureRes);

        Formatter formatter = new Formatter();
        formatter.format("%10.2f",rating);
        String message = userInfo.nickname + " имеет процент побед в Рандоме = " + formatter.toString();
        message = message + "\n" + "всего боев: " + userInfo.statistics.random.battles;
        message = message + "\n" + "глобальный рейтинг Wargaming: " + userInfo.global_rating;

      // таймштамп не расшифровывается
      //  message = message + "\n" + "зарегистрировался: " + new Date(Integer.parseInt(userInfo.created_at));;

        textView.setText(message);
    }

    // ------------------ EventBus Handling -----------------------
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onUserInfoList(ServerUserInfoEvent event){
        Log.d(Constants.LOG_TAG, "onUserInfoList start!---------");

        if(event.userInfos==null || event.userInfos.size()<1) {
            Log.d(Constants.LOG_TAG, "Null UserInfo List size ---------");
            return;
        }
        show(event.userInfos.get(0));
    }
}
