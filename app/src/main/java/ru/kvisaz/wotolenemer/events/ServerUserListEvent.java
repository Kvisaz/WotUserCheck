package ru.kvisaz.wotolenemer.events;

import java.util.List;

import ru.kvisaz.wotolenemer.network.model.User;

public class ServerUserListEvent {
    public final List<User> users;

    public ServerUserListEvent(List<User> users) {
        this.users = users;
    }
}
