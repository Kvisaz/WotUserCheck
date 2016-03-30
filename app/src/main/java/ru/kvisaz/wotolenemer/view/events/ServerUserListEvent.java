package ru.kvisaz.wotolenemer.view.events;

import java.util.List;

import ru.kvisaz.wotolenemer.model.User;

public class ServerUserListEvent {
    public final List<User> users;

    public ServerUserListEvent(List<User> users) {
        this.users = users;
    }
}
