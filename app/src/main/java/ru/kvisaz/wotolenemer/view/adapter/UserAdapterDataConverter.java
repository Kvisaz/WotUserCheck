package ru.kvisaz.wotolenemer.view.adapter;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.model.User;

public class UserAdapterDataConverter {

    public static ArrayList<Map<String,String>> getData(List<User> users){
        ArrayList<Map<String,String>> listOfMap;

        listOfMap = new ArrayList<>();
        for(User user: users){
            listOfMap.add(UserAdapterMapping.getMap(user));
        }
        return listOfMap;
    }
}
