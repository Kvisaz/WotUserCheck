package ru.kvisaz.wotolenemer;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.kvisaz.wotolenemer.db.DatabaseWorker;
import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.events.FabShowEvent;
import ru.kvisaz.wotolenemer.events.ServerUserListEvent;
import ru.kvisaz.wotolenemer.events.ShowUserInHistoryEvent;
import ru.kvisaz.wotolenemer.events.ViewSearchUserEvent;
import ru.kvisaz.wotolenemer.events.ViewSelectUserIdEvent;
import ru.kvisaz.wotolenemer.model.UserModel;
import ru.kvisaz.wotolenemer.network.model.SharedInfo;
import ru.kvisaz.wotolenemer.network.Client;
import ru.kvisaz.wotolenemer.network.model.User;
import ru.kvisaz.wotolenemer.network.model.WotResp;

public class Presenter extends EventSubscriber {
    private final Context context;
    private final String apiId;

    ExecutorService executorService;
    Handler handler;

    private final int maxItemsInDatabaseHistory = 7;
    private final DatabaseWorker databaseWorker;

    public ArrayList<UserModel> history;
    private int currentUserInHistory;

    public void setCurrentUserInHistory(int position) {
        currentUserInHistory = position;
    }

    public int getCurrentUserInHistory() {
        return currentUserInHistory;
    }

    public Presenter(Context context) {
        this.context = context;
        apiId = context.getString(R.string.api_key); // use "demo" for test, real api key is secret

        executorService = Executors.newFixedThreadPool(1);
        handler = new Handler();

        databaseWorker = new DatabaseWorker(context);

        history = new ArrayList<>();
        history = databaseWorker.readHistory();
        if(history.size()>0) {
            currentUserInHistory = history.size()-1;
        }

        EventBus.getDefault().register(this);
    }

    public UserModel getCurrentUser() {
        return history.get(currentUserInHistory);
    }

    public UserModel getUser(int position) {
        return history.get(position);
    }

    public SharedInfo getSharedInfo() {
        UserModel user = getCurrentUser();
        if (user == null) {
            Log.d(Constants.LOG_TAG, "getSharedInfo has NULL currentUser");
            return null;
        }

        String title = context.getString(R.string.info_shared_title);
        String body = context.getString(R.string.info_shared_postfix);
        String subject = context.getString(R.string.info_shared_subject);

        Log.d(Constants.LOG_TAG, "pure SharedInfo nickname = " + user.nickname);
        subject = subject + " " + user.nickname;
        body = user.nickname + " - " + String.format("%.2f", user.percentOfWins) + body;

        return new SharedInfo(title, subject, body);
    }


    // -----------------------------------------
    // ------------------ ASYNC   --------------
    // -----------------------------------------

    // ------------------ searchUser  -----------------------
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void searchUser(ViewSearchUserEvent username) {
        Log.d(Constants.LOG_TAG, "GetUsersEvent start!---------");
        WotResp<List<User>> resp = Client.getUsers(username.text,apiId);
        try {
            EventBus.getDefault().postSticky(new ServerUserListEvent(resp.data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------  getUserInfo ----------------------------
    @Subscribe
    public void getUserInfo(ViewSelectUserIdEvent event) {
        Log.d(Constants.LOG_TAG, "eventGetUserInfo start!---------");
        List<Integer> ids = new ArrayList<>();
        ids.add(event.accountId);

        executorService.submit(new ServerLoadInfoTask(ids));
    }

    private class ServerLoadInfoTask implements Runnable {
        private List<Integer> ids;

        public ServerLoadInfoTask(List<Integer> ids) {
            this.ids = ids;
        }

        @Override
        public void run() {
            ArrayList<UserModel> newUsers = Client.getSimpleUserInfoList(ids,apiId);
            addToDatabase(newUsers);
            handler.post(new AddHistoryTask(newUsers));
        }

        private void addToDatabase(ArrayList<UserModel> users) {
            for (UserModel user : users) {
                databaseWorker.saveToHistory(user);
                long count = databaseWorker.getCount();
                Log.d(Constants.LOG_TAG,"save to database. Total items in base = " + count);

                if(count> maxItemsInDatabaseHistory){
                    Log.d(Constants.LOG_TAG,"database - Max Items. First item must be deleted ");
                    UserModel first = databaseWorker.removeFirst();
                    Log.d(Constants.LOG_TAG,"database - Removed user " + first.nickname);
                }
            }
        }

    }

    private class AddHistoryTask implements Runnable {
        private ArrayList<UserModel> newUsers;

        public AddHistoryTask(ArrayList<UserModel> newUsers) {
            this.newUsers = newUsers;
        }

        @Override
        public void run() {
            history.addAll(newUsers);
            currentUserInHistory = history.size() - 1;
            EventBus.getDefault().postSticky(new FabShowEvent());
            EventBus.getDefault().postSticky(new ShowUserInHistoryEvent(currentUserInHistory));
        }
    }
}