package xyz.ihudapp.keyboardnumeric;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import java.io.Console;

public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    Keyboard keyboard;
    KeyboardView keyboardView;
    InputConnection inputConnection;
    char code;
    CharSequence selectedText;
    String frase = "";

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        keyboard = new Keyboard(this, R.xml.number_pad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        inputConnection = getCurrentInputConnection();

        return keyboardView;
    }

    @Override
    public void onPress(int i) {
    }

    @Override
    public void onRelease(int i) {
        Log.i("TEXTO", frase);
        frase += String.valueOf(code);

        if(frase.contains("AU")){
            Log.i("TEXTO", "noassa");
            frase = frase.replaceAll("AU", " NOME FEIO");
            inputConnection.deleteSurroundingText(2, 0);

        }else{
            inputConnection.commitText("", 1);
        }
        inputConnection.finishComposingText();

    }

    @Override
    public void onKey(int primatyCode, int[] keyCodes) {

        if (inputConnection != null) {

            switch(primatyCode) {
                case Keyboard.KEYCODE_DELETE :
                    selectedText = inputConnection.getSelectedText(0);

                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0);
                    } else {
                        inputConnection.commitText("", 1);
                    }

                    break;
                default :
                    code = (char) primatyCode;
                    inputConnection.commitText(String.valueOf(code), 1);
            }
        }
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
