package ru.kvisaz.wotolenemer.model;

import java.io.Serializable;

import ru.kvisaz.wotolenemer.network.model.UserInfo;

// Simple User model for database
public class UserModel implements Serializable {
    public Long _id; // for cupboard

    public String nickname;

    public long created_at; // timestamp
    public long last_battle_time; // timestamp

    // общий процент побед
    public float percentOfWins;

    // Количество боёв
    public int battles_all;
    public int battles_random;

    // Рейтинг ВГ + хинт
    public String global_rating;

    // Уничтожил врагов + в среднем
    public int frags_total;
    public float frags_average;

    // выстрелы, попадания, пробития
    public float shots_average;
    public float hits_average;
    public float piercings_average;

    // засвет, дамаг по свету
    public float spotted_average;
    public float damage_assisted_radio_average;

    // база
    public float base_captured_average;
    public float base_defense_average;

    // язык
    public String client_language;

    public static UserModel getInstance(UserInfo userInfo){
        UserModel user = new UserModel();

        user.nickname = userInfo.nickname;
        user.created_at = userInfo.created_at;
        user.last_battle_time = userInfo.last_battle_time;
        user.client_language = userInfo.client_language;

        int wins = userInfo.statistics.all.wins;
        int battles = userInfo.statistics.all.battles;
        if (battles > 0) {
            user.percentOfWins = (float) wins / (float) battles * 100;
        }

        user.battles_all = userInfo.statistics.all.battles;
        user.battles_random = userInfo.statistics.random.battles;
        user.global_rating = userInfo.global_rating;

        user.frags_total = userInfo.statistics.all.frags;
        user.frags_average = (float) userInfo.statistics.all.frags / (float) userInfo.statistics.all.battles;

        user.shots_average = (float) userInfo.statistics.all.shots / userInfo.statistics.all.battles;
        user.hits_average = (float) userInfo.statistics.all.hits / userInfo.statistics.all.battles;
        user.piercings_average = (float) userInfo.statistics.all.piercings / userInfo.statistics.all.battles;

        user.spotted_average = (float) userInfo.statistics.all.spotted / (float) userInfo.statistics.all.battles;
        user.damage_assisted_radio_average = userInfo.statistics.all.avg_damage_assisted_radio;

        user.base_captured_average = (float) userInfo.statistics.all.capture_points / userInfo.statistics.all.battles;
        user.base_defense_average = (float) userInfo.statistics.all.dropped_capture_points / userInfo.statistics.all.battles;

        return user;
    }
}
