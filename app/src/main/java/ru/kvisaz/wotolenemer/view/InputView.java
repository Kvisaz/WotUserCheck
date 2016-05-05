package ru.kvisaz.wotolenemer.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import ru.kvisaz.wotolenemer.App;
import ru.kvisaz.wotolenemer.R;
import ru.kvisaz.wotolenemer.events.EventSubscriber;
import ru.kvisaz.wotolenemer.events.ViewSearchUserEvent;
import ru.kvisaz.wotolenemer.utilits.DoubleClickPreventer;
import ru.kvisaz.wotolenemer.utilits.Keyboard;
import ru.kvisaz.wotolenemer.utilits.PreventerServerOverload;

/**
 *   user search and text input
 */
public class InputView extends EventSubscriber {
    private final EditText input;
    private final static int MIN_CHAR_COUNT = 3;

    @Inject
    Context context;

    public InputView(View rootView) {
        input = (EditText)rootView.findViewById(R.id.editTextInput);
        setupInputListener();

        App.getAppComponent().inject(this);
    }

    private void setupInputListener() {
        // Hide Keyboard and make request
        input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    hideKeyBoardAndSearch(v);
                    return false;
                }
                return false;
            }
        });

        // for dynamic "ajax"-queries
        input.addTextChangedListener(new InputListener());
    }



    private class EnterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            hideKeyBoardAndSearch(v);
        }
    }

    private void hideKeyBoardAndSearch(View v){
        if(DoubleClickPreventer.isDisableClick()) return; // disable double action
        DoubleClickPreventer.disableClick();

        postString(input.getText().toString());
        Keyboard.hide(v);
    }

    private class InputListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            // disable server overload
            if(PreventerServerOverload.isQueryDisabled()) return;
            PreventerServerOverload.disableQueries();

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
