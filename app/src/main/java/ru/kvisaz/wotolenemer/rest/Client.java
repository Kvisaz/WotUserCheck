package ru.kvisaz.wotolenemer.rest;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.model.User;
import ru.kvisaz.wotolenemer.model.WotResp;
import ru.kvisaz.wotolenemer.view.events.InputEvent;
import ru.kvisaz.wotolenemer.view.events.UserListEvent;


/**
 *   Client functions
 *   get server response in same thread - use loader or any other async task
 *
 */
public class Client {

    public Client(){
        EventBus.getDefault().register(this);
    }

    public void close(){
        EventBus.getDefault().unregister(this);
    }

    public static void getUsers(String checkName){
        WotResp<List<User>> resp;
        try{
            resp = RetrofitFactory
                    .getApiService()
                    .findUsers(checkName)
                    .execute()
                    .body();
            EventBus.getDefault().postSticky(new UserListEvent(resp.data));
        }
        catch (Exception e)
        {
            Log.d(Constants.LOGTAG,"GetUsers Exception");
            e.printStackTrace();
        }
    }

    // --------------------------- Events --------------------------
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void getUsersEvent(InputEvent nameEvent){
        Log.d(Constants.LOGTAG,"GetUsersEvent start!---------");
        getUsers(nameEvent.text);
    }


}
