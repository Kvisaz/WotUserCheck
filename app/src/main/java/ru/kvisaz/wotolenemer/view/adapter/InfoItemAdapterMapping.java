package ru.kvisaz.wotolenemer.view.adapter;

import java.util.HashMap;

import ru.kvisaz.wotolenemer.R;

public class InfoItemAdapterMapping {
    public static final String INFO_TITLE_TAG = "title";
    public static final int INFO_TITLE_LAYOUTID = R.id.detail_item_title;

    public static final String INFO_BODY_TAG = "body";
    public static final int INFO_BODY_LAYOUTID = R.id.detail_item_body;


    public final static String[] from = {INFO_TITLE_TAG,INFO_BODY_TAG};
    public final static int[] to = {INFO_TITLE_LAYOUTID, INFO_BODY_LAYOUTID};

    public static HashMap<String,String> getMap(String title, String body){
        HashMap<String,String> mapUserInfo = new HashMap<>();
        mapUserInfo.put(INFO_TITLE_TAG, title);
        mapUserInfo.put(INFO_BODY_TAG,body);
        return mapUserInfo;
    }

}
