package ru.kvisaz.wotolenemer.events;

import java.util.List;

import ru.kvisaz.wotolenemer.network.model.UserInfo;

public class ServerUserInfoEvent {
    public final List<UserInfo> userInfos;

    public ServerUserInfoEvent(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
