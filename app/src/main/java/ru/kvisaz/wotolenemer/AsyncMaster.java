package ru.kvisaz.wotolenemer;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.kvisaz.wotolenemer.model.User;
import ru.kvisaz.wotolenemer.model.UserInfo;
import ru.kvisaz.wotolenemer.model.WotResp;
import ru.kvisaz.wotolenemer.rest.Client;
import ru.kvisaz.wotolenemer.view.events.ViewSelectUserIdEvent;
import ru.kvisaz.wotolenemer.view.events.ViewSearchUserEvent;
import ru.kvisaz.wotolenemer.view.events.ServerUserInfoEvent;
import ru.kvisaz.wotolenemer.view.events.ServerUserListEvent;
import ru.kvisaz.wotolenemer.view.events.ViewTabShowInfoEvent;

/**
 *  Async event handlers for UI and EventBus
 *
 */
public class AsyncMaster {
    public AsyncMaster() { EventBus.getDefault().register(this); }
    public void close() { EventBus.getDefault().unregister(this);}

    // ------------------ Async EventBus Handlers -----------------------
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void searchUser(ViewSearchUserEvent username){
        Log.d(Constants.LOGTAG, "GetUsersEvent start!---------");
        WotResp<List<User>> resp = Client.getUsers(username.text);
        try{
          EventBus.getDefault().postSticky(new ServerUserListEvent(resp.data));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // -------------------
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getUserInfo(ViewSelectUserIdEvent event){
        Log.d(Constants.LOGTAG, "eventGetUserInfo start!---------");
        List<Integer> ids = new ArrayList<>();
        ids.add(event.accountId);

        try{
            WotResp<Map<String,UserInfo>> respInfos = Client.getUserInfoList(ids);
            List<UserInfo> userInfos = new ArrayList<>();
            for (Map.Entry<String,UserInfo> userInfoEntry:respInfos.data.entrySet()) {
                userInfos.add(userInfoEntry.getValue());
            }
            EventBus.getDefault().postSticky(new ServerUserInfoEvent(userInfos));
            EventBus.getDefault().post(new ViewTabShowInfoEvent());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
