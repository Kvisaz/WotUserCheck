package ru.kvisaz.wotolenemer.network.model;

import java.io.Serializable;

import ru.kvisaz.wotolenemer.network.model.stat.Statistics;

/**
 *  wot/account/info  Wargaming API -- Персональные данные игрока
 */
public class UserInfo implements Serializable {
    public Long _id; // for cupboard

    public String ban_info;
    public long ban_time; // timestamp  1413228514

    public int clan_id;
    public String client_language;

    public long created_at; // timestamp
    public long last_battle_time; // timestamp

    public String global_rating;

    public String nickname;
    public int account_id;

    public Statistics statistics;
}
