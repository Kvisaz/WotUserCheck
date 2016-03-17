package ru.kvisaz.wotolenemer.view.events;

import java.util.List;

import ru.kvisaz.wotolenemer.model.User;

public class UserListEvent {
    public final List<User> users;

    public UserListEvent(List<User> users) {
        this.users = users;
    }
}
