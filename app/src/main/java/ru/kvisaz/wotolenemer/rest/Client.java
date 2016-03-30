package ru.kvisaz.wotolenemer.rest;

import android.util.Log;

import java.util.List;
import java.util.Map;

import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.model.User;
import ru.kvisaz.wotolenemer.model.UserInfo;
import ru.kvisaz.wotolenemer.model.WotResp;


/**
 *   Client functions
 *   get server response in same thread - use loader or any other async task
 *
 */
public class Client {

    public static  WotResp<List<User>> getUsers(String checkName){
        WotResp<List<User>> resp;
        try{
            resp = RetrofitFactory
                    .getApiService()
                    .findUsers(checkName)
                    .execute()
                    .body();
            return resp;
        }
        catch (Exception e)
        {
            Log.d(Constants.LOGTAG,"GetUsers Exception");
            e.printStackTrace();
            return null;
        }
    }

    public static WotResp<Map<String,UserInfo>> getUserInfoList (List<Integer> account_ids) {
        try{
            WotResp<Map<String,UserInfo>> resp = RetrofitFactory
                    .getApiService()
                    .getUserInfoList(account_ids)
                    .execute()
                    .body();
            return  resp;
        }
        catch (Exception e)
        {
            Log.d(Constants.LOGTAG,"getUserInfoList Exception");
            Log.d(Constants.LOGTAG, e.toString());
            return null;
        }
    }

}
