package com.incture.leaveme;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Mohammed on 10/6/2015.
 */
public class TextViewRobotto extends TextView {

    public TextViewRobotto(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        createFont();
    }

    public TextViewRobotto(Context context, AttributeSet attrs) {
        super(context, attrs);
        createFont();
    }

    public TextViewRobotto(Context context) {
        super(context);
        createFont();
    }

    public void createFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Thin.ttf");
        setTypeface(font);
    }
}