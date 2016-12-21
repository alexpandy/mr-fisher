package com.naruku.fisher.fontui;

import android.content.Context;
import android.util.AttributeSet;

import com.dd.processbutton.iml.ActionProcessButton;

/**
 * Custom Button which allows us to apply custom font
 */
public class FontProcessButton extends ActionProcessButton {

	public FontProcessButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontProcessButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontProcessButton(Context context) {
		super(context);
		CustomFont.applyFont(context, this, null);
	}
	
}
