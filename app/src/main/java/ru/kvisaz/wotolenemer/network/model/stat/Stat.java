package ru.kvisaz.wotolenemer.network.model.stat;

import java.io.Serializable;

/**
 *  Статистика для конкретного режима игры World of Tanks
 */
public class Stat implements Serializable {
    public Long _id; // for cupboard

    public int wins;
    public int battles;

    public int frags;
    public int survived_battles;

    public int shots;
    public int hits;
    public int piercings;

    public int spotted;
    public float avg_damage_assisted_radio;
    public float avg_damage_assisted_track;

    public int dropped_capture_points;
    public int capture_points;
}
