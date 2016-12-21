package com.naruku.fisher.fontui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Custom Button which allows us to apply custom font
 */
public class FontButton extends Button {

	public FontButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontButton(Context context) {
		super(context);
		CustomFont.applyFont(context, this, null);
	}
	
}
