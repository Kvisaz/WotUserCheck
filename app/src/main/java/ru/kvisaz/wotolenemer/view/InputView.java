package ru.kvisaz.wotolenemer.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.greenrobot.eventbus.EventBus;

import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.view.events.ViewSearchUserEvent;

/**
 *   user search and text input
 */
public class InputView {
    private final EditText input;
    private final Button btEnter;
    private final static int MIN_CHAR_COUNT = 3;

    public InputView(View rootView) {
        input = (EditText)rootView.findViewById(R.id.editTextInput);
//       setupInputListener();


        btEnter = (Button)rootView.findViewById(R.id.btInput);
        btEnter.setOnClickListener(new EnterClickListener());
    }

    private void setupInputListener() {
        input.addTextChangedListener(new InputListener());
    }

    // Clean before exit
    public void close()  {
        // no listeners here yet
    //    EventBus.getDefault().unregister(this);
    }

    private class EnterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            postString(input.getText().toString());
        }
    }

    private class InputListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            postString(s.toString());
        }
    }


   // --------------------------- Event Senders ----------------------------
    private void postString(String str){
       // проверка минимальной длины
        if(str.length()>=MIN_CHAR_COUNT){
            EventBus.getDefault().post(new ViewSearchUserEvent(str));
        }
    }
}
