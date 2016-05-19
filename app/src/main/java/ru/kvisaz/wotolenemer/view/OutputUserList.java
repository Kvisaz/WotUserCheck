package ru.kvisaz.wotolenemer.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.events.ServerUserListEvent;
import ru.kvisaz.wotolenemer.network.model.User;
import ru.kvisaz.wotolenemer.utilits.DoubleClickPreventer;
import ru.kvisaz.wotolenemer.utilits.Keyboard;
import ru.kvisaz.wotolenemer.view.adapter.UserAdapterDataConverter;
import ru.kvisaz.wotolenemer.view.adapter.UserAdapterMapping;
import ru.kvisaz.wotolenemer.events.ViewSelectUserIdEvent;

/**
 *  Helper for user ListView
 */
public class OutputUserList  extends EventSubscriber {
    private ArrayList<Map<String,String>> userListMap;
    private final SimpleAdapter adapter;
    private final ListView userListView;

    Context contextOfView;

    public OutputUserList(View rootView){
        contextOfView = rootView.getContext();
        userListMap = UserAdapterDataConverter.getData(new ArrayList<User>());

        adapter = new SimpleAdapter(contextOfView,
                userListMap,
                R.layout.user,
                UserAdapterMapping.from,
                UserAdapterMapping.to );

        userListView = (ListView)rootView.findViewById(R.id.userListView);
        userListView.setAdapter(adapter);

        userListView.setOnItemClickListener(new UserClickListener());

    }

    // очистить тот же массив и добавить в него все данные
    public void show(List<User> userList){
        userListMap.clear();
        userListMap.addAll(UserAdapterDataConverter.getData(userList));
        adapter.notifyDataSetChanged();
    }

    // очистить
    public void clear(){
        userListMap.clear();
        adapter.notifyDataSetChanged();
    }

    private class UserClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // disable double action
            if(DoubleClickPreventer.isDisableClick()) return;
            DoubleClickPreventer.disableClick();

            Keyboard.hide(view); // hide keyboard if shown.

            Map<String,String> mapUserInfo = (Map)adapter.getItem(position);
            int accountId = Integer.parseInt(mapUserInfo.get(UserAdapterMapping.ACCOUNT_ID_TAG));

            EventBus.getDefault().post(new ViewSelectUserIdEvent(accountId));

        }
    }

    // --------------------------- Event Handlers ----------------------------

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onListUsersArrived(ServerUserListEvent event){
        Log.d(Constants.LOG_TAG, "onListUsersArrived start!---------");
        if(event.users==null || event.users.size()<1) {
            Log.d(Constants.LOG_TAG, "Null UserList size ---------");
            clear();

//            Toast.makeText(context,Constants.UI_MESSAGE_NO_USERS_FOUND,Toast.LENGTH_SHORT).show();
            return;
        }
        // refresh user list
        show(event.users);
    }
}
