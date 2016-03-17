package ru.kvisaz.wotolenemer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ru.kvisaz.wotolenemer.rest.Client;
import ru.kvisaz.wotolenemer.view.InputView;
import ru.kvisaz.wotolenemer.view.OutputView;

public class MainActivity extends AppCompatActivity {

    private InputView inputView;
    private OutputView outputView;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    // --------------- Subscribe & UnSubscribe Event Handlers --------------


    @Override
    protected void onStart() {
        super.onStart();

        View rootView = getWindow().getDecorView();
        inputView = new InputView(rootView);
        outputView = new OutputView(rootView);
        client = new Client();
    }

    @Override
    protected void onStop() {
        super.onStop();
        inputView.close();
        outputView.close();
        client.close();
    }
}
