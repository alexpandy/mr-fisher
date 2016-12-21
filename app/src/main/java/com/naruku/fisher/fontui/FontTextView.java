package com.naruku.fisher.fontui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Custom TextView which allows us to apply custom font
 */
public class FontTextView extends TextView {

	public FontTextView(Context context) {
		super(context);
		CustomFont.applyFont(context, this, null);
	}
	
	public FontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		CustomFont.applyFont(context, this, attrs);
	}
	
	public FontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CustomFont.applyFont(context, this, attrs);
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {

	}
	
}
