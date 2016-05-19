package ru.kvisaz.wotolenemer.view.output;

import android.content.Context;
import javax.inject.Inject;
import ru.kvisaz.wotolenemer.App;
import ru.kvisaz.wotolenemer.R;

// Resource reader

public class OutputUserInfoStrings {
    @Inject
    Context context;

    public String SharedDialogTitle;
    public String SharedPostfix;
    public String sharedSubject;

    public String BattlesTotal;
    public String BattlesRandom;

    public String DateReg;
    public String DateLast;

    public String ratingTitle;
    public String ratingDesc;

    public String killedEnemiesTotal;
    public String killedEnemiesAverage;

    public String spottedAverage;
    public String scoutDamagedAverage;
    public String caterpillarDamagedAverage;

    public String shootsPerBattle;
    public String hitsPerBattle;
    public String piercesPerBattle;

    public String baseCaptureAverage;
    public String baseDefenseAverage;

    public String languageTitle;
    public String languageDesc;

    public OutputUserInfoStrings(){
        App.getAppComponent().inject(this);

        SharedPostfix = context.getString(R.string.info_shared_postfix);
        SharedDialogTitle = context.getString(R.string.info_shared_title);
        sharedSubject = context.getString(R.string.info_shared_subject);

        BattlesTotal = context.getString(R.string.prefix_battles_total);
        BattlesRandom = context.getString(R.string.prefix_battles_random);

        DateReg = context.getString(R.string.prefix_date_reg);
        DateLast = context.getString(R.string.prefix_date_last);

        ratingTitle = context.getString(R.string.prefix_ratingTitle);
        ratingDesc = context.getString(R.string.prefix_ratingDesc);

        killedEnemiesTotal = context.getString(R.string.prefix_killedEnemiesTotal);
        killedEnemiesAverage = context.getString(R.string.prefix_killedEnemiesAverage);

        shootsPerBattle = context.getString(R.string.prefix_shootsPerBattle);
        hitsPerBattle = context.getString(R.string.prefix_hitsPerBattle);
        piercesPerBattle = context.getString(R.string.prefix_piercesPerBattle);

        spottedAverage = context.getString(R.string.prefix_scoutExploredAverage);
        scoutDamagedAverage = context.getString(R.string.prefix_scoutDamaged);
        caterpillarDamagedAverage = context.getString(R.string.prefix_caterpillarDamaged);

        baseCaptureAverage = context.getString(R.string.prefix_baseCaptureAverage);
        baseDefenseAverage = context.getString(R.string.prefix_baseDefenseAverage);

        languageTitle = context.getString(R.string.prefix_languageTitle);
        languageDesc = context.getString(R.string.prefix_languageDesc);
    }
}
