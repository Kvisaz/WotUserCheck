package ru.kvisaz.wotolenemer;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.events.ServerUserInfoEvent;
import ru.kvisaz.wotolenemer.events.ServerUserListEvent;
import ru.kvisaz.wotolenemer.events.ViewSearchUserEvent;
import ru.kvisaz.wotolenemer.events.ViewSelectUserIdEvent;
import ru.kvisaz.wotolenemer.network.Client;
import ru.kvisaz.wotolenemer.network.model.User;
import ru.kvisaz.wotolenemer.network.model.UserInfo;
import ru.kvisaz.wotolenemer.network.model.WotResp;

public class Presenter extends EventSubscriber {
    private final Context context;

    public Presenter(Context context) {
        this.context = context;
    }

    public void test(){
        Log.d(Constants.LOG_TAG,"Presenter injected and tested");
    }

    // -----------------------------------------
    // ------------------ ASYNC   --------------
    // -----------------------------------------

    // ------------------ searchUser  -----------------------
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void searchUser(ViewSearchUserEvent username){
        Log.d(Constants.LOG_TAG, "GetUsersEvent start!---------");
        WotResp<List<User>> resp = Client.getUsers(username.text);
        try{
            EventBus.getDefault().postSticky(new ServerUserListEvent(resp.data));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // -------------------  getUserInfo
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getUserInfo(ViewSelectUserIdEvent event){
        Log.d(Constants.LOG_TAG, "eventGetUserInfo start!---------");
        List<Integer> ids = new ArrayList<>();
        ids.add(event.accountId);

        try{
            WotResp<Map<String,UserInfo>> respInfos = Client.getUserInfoList(ids);
            List<UserInfo> userInfos = new ArrayList<>();
            for (Map.Entry<String,UserInfo> userInfoEntry:respInfos.data.entrySet()) {
                userInfos.add(userInfoEntry.getValue());
            }
            EventBus.getDefault().postSticky(new ServerUserInfoEvent(userInfos));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
