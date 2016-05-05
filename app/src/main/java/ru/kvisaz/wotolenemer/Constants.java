package ru.kvisaz.wotolenemer;

import android.content.Context;
import android.util.Log;

public class Constants {
    public static String APP_NAME;
    public static String LOG_TAG;
    public static String SAVEDSTATE_TOPIC;
    public static boolean IS_TABLET;

    public static String TAB1_TITLE;
    public static String TAB2_TITLE;

    public static String UI_MESSAGE_NO_USERS_FOUND;


    public static void getResources(Context context){
        APP_NAME = context.getString(R.string.app_name);
        LOG_TAG = context.getString(R.string.logTag);
        SAVEDSTATE_TOPIC = context.getString(R.string.savedStateTopic);

        UI_MESSAGE_NO_USERS_FOUND = context.getString(R.string.ui_message_no_users_found);

        TAB1_TITLE = context.getString(R.string.tab1_title);
        TAB2_TITLE = context.getString(R.string.tab2_title);

            IS_TABLET = context.getResources().getBoolean(R.bool.isTablet);


        Log.d(LOG_TAG,"Constants init");
    }

}
