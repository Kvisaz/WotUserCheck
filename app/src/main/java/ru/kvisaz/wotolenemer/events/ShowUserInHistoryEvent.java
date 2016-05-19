package ru.kvisaz.wotolenemer.events;

public class ShowUserInHistoryEvent {
    public final int user;

    public ShowUserInHistoryEvent(int user) {
        this.user = user;
    }
}
