package ru.kvisaz.wotolenemer.view;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;


import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.Swipe;

/*
*  todo Перевести всё из хардкода в строковые ресурсы
* */
public class OutputTabView {
    private final TabHost tabHost;
    private Swipe swipeLocal;

    private final boolean LEFT = false;
    private final boolean RIGHT = true;

    public OutputTabView(View rootView){
        tabHost = (TabHost)rootView.findViewById(R.id.tab_host);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Игроки");

        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Инфо");

        tabHost.addTab(spec);

        tabHost.setOnTouchListener(new LocalTouchListener());

        showUserList();

        swipeLocal = new Swipe();
    }

    public void showUserList(){
        tabHost.setCurrentTab(0);
    }

    public void showUserInfo(){
        tabHost.setCurrentTab(1);
    }

    public void switchTabs(boolean toLeftDirection){
        int maxTabN = tabHost.getTabWidget().getTabCount()-1;
        int currentTabN = tabHost.getCurrentTab();
        if(toLeftDirection)  // <------  left
        {
            if(tabHost.getCurrentTab()==0)
                tabHost.setCurrentTab(maxTabN);
            else
                tabHost.setCurrentTab(currentTabN-1);
        }
        else{ // ------>  right
            if(currentTabN==maxTabN)
                tabHost.setCurrentTab(0);
            else
                tabHost.setCurrentTab(currentTabN+1);
        }
    }

    private class LocalTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            // todo ГЛЮЧИТ СВАЙП НА СПИСКЕ - СДЕЛАЙ ПЕРЕХОД НА ИНФО ПО КЛИКУ
            float x = event.getX();
            float y = event.getY();

            swipeLocal.calculate(event);

            if(swipeLocal.isRight) {
                switchTabs(RIGHT);
                return false;
            }
            else if(swipeLocal.isLeft){
                switchTabs(LEFT);
                return false;
            }
            // see details -  http://stackoverflow.com/questions/14776271/android-ontouch-motionevent-action-move-is-not-recognized
            return true;
        }
    }
}
