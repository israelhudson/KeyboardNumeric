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
    InputConnection inputConnection;
    String frase = "";
    String palavraExcluida = "AU";

    @Override
    public View onCreateInputView() {
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.number_pad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int i) {


    }

    @Override
    public void onRelease(int i) {
        char code = (char) i;
        frase += String.valueOf(code);
        Log.i("TEXTO", frase);

        if(frase.contains(palavraExcluida)){
            inputConnection.deleteSurroundingText(palavraExcluida.length(), 0);
            frase = frase.replaceAll(palavraExcluida, "");
            inputConnection.commitText(" PROIBIDO ", frase.length());
        }
    }

    @Override
    public void onKey(int primatyCode, int[] keyCodes) {
        inputConnection = getCurrentInputConnection();

        if (inputConnection != null) {
            switch(primatyCode) {
                case Keyboard.KEYCODE_DELETE :
                    CharSequence selectedText = inputConnection.getSelectedText(0);

                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0);
                    } else {
                        inputConnection.commitText("", 1);
                    }

                    break;

                default :
                    char code = (char) primatyCode;
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