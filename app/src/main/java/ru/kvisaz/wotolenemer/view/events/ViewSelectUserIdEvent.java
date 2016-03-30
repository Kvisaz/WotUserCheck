package ru.kvisaz.wotolenemer.view.events;

public class ViewSelectUserIdEvent {
    public final int accountId;

    public ViewSelectUserIdEvent(int accountId) {
        this.accountId = accountId;
    }
}
