package ru.kvisaz.wotolenemer.network.model;

import ru.kvisaz.wotolenemer.network.model.stat.Statistics;

/**
 *  wot/account/info  Wargaming API -- Персональные данные игрока
 */
public class UserInfo {
    public String ban_info;
    public String ban_time; // timestamp  1413228514

    public int clan_id;
    public String client_language;

    public String created_at; // timestamp

    public String global_rating;

    public String nickname;
    public int account_id;

    public Statistics statistics;
}
