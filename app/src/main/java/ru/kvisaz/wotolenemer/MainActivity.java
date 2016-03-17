package ru.kvisaz.wotolenemer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

import ru.kvisaz.wotolenemer.view.InputView;
import ru.kvisaz.wotolenemer.view.OutputView;

public class MainActivity extends AppCompatActivity {

    private InputView inputView;
    private OutputView outputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = new InputView(getWindow().getDecorView());
        outputView = new OutputView(getWindow().getDecorView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        inputView.bye();
        outputView.bye();
    }
}
