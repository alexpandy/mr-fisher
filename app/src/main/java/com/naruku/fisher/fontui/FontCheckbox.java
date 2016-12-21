package com.naruku.fisher.fontui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Custom CheckBox which allows us to apply custom font
 */
public class FontCheckbox extends Button {

	public FontCheckbox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontCheckbox(Context context, AttributeSet attrs) {
		super(context, attrs);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontCheckbox(Context context) {
		super(context);
		CustomFont.applyFont(context, this, null);
	}
	
}
