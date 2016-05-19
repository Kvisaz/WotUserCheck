package ru.kvisaz.wotolenemer.network;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.model.UserModel;
import ru.kvisaz.wotolenemer.network.model.User;
import ru.kvisaz.wotolenemer.network.model.UserInfo;
import ru.kvisaz.wotolenemer.network.model.WotResp;


/**
 *   Client functions
 *   get server response in same thread - use loader or any other async task
 *
 */
public class Client {


    public static  WotResp<List<User>> getUsers(String checkName, String apiId){

        WotResp<List<User>> resp;
        try{
            resp = RetrofitFactory
                    .getApiService()
                    .findUsers(checkName, apiId)
                    .execute()
                    .body();
            return resp;
        }
        catch (Exception e)
        {
            Log.d(Constants.LOG_TAG,"GetUsers Exception");
            e.printStackTrace();
            return null;
        }
    }

    public static WotResp<Map<String,UserInfo>> getUserInfoList (List<Integer> account_ids, String apiId) {
        try{
            WotResp<Map<String,UserInfo>> resp = RetrofitFactory
                    .getApiService()
                    .getUserInfoList(account_ids, apiId)
                    .execute()
                    .body();
            return  resp;
        }
        catch (Exception e)
        {
            Log.d(Constants.LOG_TAG,"getUserInfoList Exception");
            Log.d(Constants.LOG_TAG, e.toString());
            return null;
        }
    }

    public static ArrayList<UserModel> getSimpleUserInfoList(List<Integer> account_ids, String apiId){
        ArrayList<UserModel> users = new ArrayList<>();

        WotResp<Map<String,UserInfo>> map = getUserInfoList(account_ids, apiId);
        for (Map.Entry<String, UserInfo> userInfoEntry : map.data.entrySet()) {
            UserInfo nextInfo = userInfoEntry.getValue();
            users.add(UserModel.getInstance(nextInfo));
        }
        return  users;
    }

}
