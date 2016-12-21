package com.naruku.fisher.fontui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Custom EditText which allows us to apply custom font
 */
public class FontEditText extends EditText {
	public FontEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontEditText(Context context) {
		super(context);
		CustomFont.applyFont(context, this, null);
	}
	@Override
	public void setError(CharSequence error, Drawable icon) {
		setCompoundDrawables(null, null, icon, null);
	}
}
