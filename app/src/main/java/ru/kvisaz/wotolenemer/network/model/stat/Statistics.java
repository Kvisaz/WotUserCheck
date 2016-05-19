package ru.kvisaz.wotolenemer.network.model.stat;

import java.io.Serializable;

/**
 * statistics subclass for UserInfo
 */
public class Statistics implements Serializable {
    public Long _id; // for cupboard

    public Stat all; // Суммарная статистика по Случайным, Ротным и клановым боям без учёта статистики боёв на Глобальной Карте 2.0
    public Stat clan;      // Статистика боёв в составе клана
    public Stat company;  // Статистика боёв в составе роты
    public Stat fallout; // Статистика в режиме «Бой до последнего».
    public Stat globalmap_absolute; // Статистика боёв на Глобальной карте в Абсолютном дивизионе.
    public Stat globalmap_champion; // Статистика боёв на Глобальной карте в Чемпионском дивизионе.
    public Stat globalmap_middle; // Статистика боёв на Глобальной карте в Среднем дивизионе.
    public Stat random; // Статистика случайных боёв.
    public Stat regular_team; // Статистика командных боёв постоянных команд
    public Stat stronghold_defense; // Общая по всем кланам статистика боёв игрока в режиме обороны Укрепрайона
    public Stat stronghold_skirmish; // Общая по всем кланам статистика боёв игрока в режиме вылазок Укрепрайона
    public Stat team; // Статистика командных боёв

}
