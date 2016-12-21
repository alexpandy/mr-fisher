package com.naruku.fisher.fontui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

/**
 * Custom Checked TextView which allows us to apply custom font
 */
public class FontCheckedTextView extends CheckedTextView {

	public FontCheckedTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontCheckedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		CustomFont.applyFont(context, this, attrs);
	}

	public FontCheckedTextView(Context context) {
		super(context);
		CustomFont.applyFont(context, this, null);
	}

}
