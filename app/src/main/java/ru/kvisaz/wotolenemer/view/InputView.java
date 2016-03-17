package ru.kvisaz.wotolenemer.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.greenrobot.eventbus.EventBus;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.view.events.InputEvent;

/**
 *   user search and text input
 */
public class InputView {
    private final EditText input;
    private final Button btEnter;

    public InputView(View rootView) {
        input = (EditText)rootView.findViewById(R.id.editTextInput);
        btEnter = (Button)rootView.findViewById(R.id.btInput);

        btEnter.setOnClickListener(new EnterClickListener());
    }

    // Clean before exit
    public void bye()  {
        EventBus.getDefault().unregister(this);
    }

    private class EnterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            input.setText("DONE!!!!!!!!");
            EventBus.getDefault().post(new InputEvent("DONE!!!!!!!!"));
        }
    }
}
