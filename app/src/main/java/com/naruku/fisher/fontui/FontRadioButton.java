package com.naruku.fisher.fontui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Custom RadioButton which allows us to apply custom font
 */
public class FontRadioButton extends RadioButton {

	public FontRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontRadioButton(Context context) {
		super(context);
		CustomFont.applyFont(context, this, null);
	}
	
}
