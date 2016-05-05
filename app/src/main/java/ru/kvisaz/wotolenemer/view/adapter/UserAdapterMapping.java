package ru.kvisaz.wotolenemer.view.adapter;

import java.util.HashMap;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.network.model.User;

public class UserAdapterMapping {
    public static final String USERNAME_TAG = "nickname";
    public static final int USER_NAME_LAYOUTID = R.id.userNameId;

    public static final String ACCOUNT_ID_TAG = "account_id";
//    public static final int  ACCOUNT_ID_LAYOUTID = R.id.userNameId;

    public final static String[] from = {USERNAME_TAG};
    public final static int[] to = {USER_NAME_LAYOUTID};

    public static HashMap<String,String> getMap(User user){
        HashMap<String,String> mapUserInfo = new HashMap<>();
        mapUserInfo.put(USERNAME_TAG, user.nickname);
        mapUserInfo.put(ACCOUNT_ID_TAG,user.account_id+"");
        return mapUserInfo;
    }


}
