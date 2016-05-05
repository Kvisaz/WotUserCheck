package ru.kvisaz.wotolenemer.events;

public class ViewSelectUserIdEvent {
    public final int accountId;

    public ViewSelectUserIdEvent(int accountId) {
        this.accountId = accountId;
    }
}
