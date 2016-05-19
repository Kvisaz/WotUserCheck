package ru.kvisaz.wotolenemer.view.output;

import ru.kvisaz.wotolenemer.Constants;

public class ColorGetter {
    public static int get(int rate){
        return Constants.colorsOfRate.getColor(rate,Constants.colorAccent);
    }
}
