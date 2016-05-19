package ru.kvisaz.wotolenemer.view;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.view.adapter.InfoItemAdapterMapping;

/**
 *  meta view for info list
 */
public class OutputUserInfoList {
    private final Context contextOfView;

    private final ArrayList<Map<String,String>> listOfInfoElements;
    private final ListView listView;
    private final SimpleAdapter adapter;


    public OutputUserInfoList(View rootView) {
        contextOfView = rootView.getContext();

        listOfInfoElements = new ArrayList<>();
        adapter = new SimpleAdapter(contextOfView,
                listOfInfoElements,
                R.layout.detail_item,
                InfoItemAdapterMapping.from,
                InfoItemAdapterMapping.to);

        listView = (ListView)rootView.findViewById(R.id.info_list_view);
        listView.setAdapter(adapter);
    }

    public void clear(){
        listOfInfoElements.clear();
        adapter.notifyDataSetChanged();
    }

    public void refresh(){
        adapter.notifyDataSetChanged();
    }

    public void add(String title, String body){
        listOfInfoElements.add(InfoItemAdapterMapping.getMap(title,body));
    }
}
