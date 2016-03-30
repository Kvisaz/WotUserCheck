package ru.kvisaz.wotolenemer.view.events;

import java.util.List;

import ru.kvisaz.wotolenemer.model.UserInfo;

public class ServerUserInfoEvent {
    public final List<UserInfo> userInfos;

    public ServerUserInfoEvent(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
