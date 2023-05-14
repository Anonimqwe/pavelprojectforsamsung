package com.example.myapplication.button;

import android.content.Context;
import android.util.AttributeSet;

public class MyImageButton extends androidx.appcompat.widget.AppCompatImageButton {

    private boolean clicked;
    private boolean right;

    public MyImageButton(Context context) {
        super(context);
    }
    public MyImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isClicked() {
        return clicked;
    }

    public boolean isRight() {
        return right;
    }
}
