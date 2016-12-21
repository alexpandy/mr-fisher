package com.naruku.fisher.fontui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.naruku.fisher.Logger;
import com.naruku.fisher.R;

import java.util.HashMap;


/**
 * Class which allows us to enable custom font, color, size, font-face
 */
public class CustomFont {

	private static HashMap<String, Typeface> mTypefaces = new HashMap<String, Typeface>();
	
	public static void applyFont(Context context, TextView inputView, AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.fontView);
			
			if (a.hasValue(R.styleable.fontView_fontFace)) {
				String fontFace = a.getString(R.styleable.fontView_fontFace);
				Logger.d("Applying font face "+fontFace+" to "+inputView.getId());
				setFontface(inputView, getTypeface(context, fontFace));
			}
			a.recycle();
		}
	}

	public static Typeface getTypeface(Context context, String fontFace) {
		Typeface typeface = null;
		
		try {
			// Typeface.createFromAsset always returns a new typeface generated from font file
			// present in the assets directory. Hence, we are storing it locally for any further
			// references after the first to avoid that loading.
			if (mTypefaces.containsKey(fontFace)) {
				typeface = mTypefaces.get(fontFace);
			} else {
				typeface = Typeface.createFromAsset(context.getAssets(), fontFace);
				// A new typeface has been read from assets. Store it locally for future reference.
				mTypefaces.put(fontFace, typeface);
			}
		} catch (Exception e) {
			e.printStackTrace();
		//	AirbrakeNotifier.notify(e);

		}
		
		return typeface;
	}
	
	public static void setFontface(TextView inputView, Typeface typeface) {
		if (typeface != null) {
			inputView.setTypeface(typeface);
		}
	}
	
	public static void setFontface(Context context, TextView inputView, String typefacePath) {
		if (typefacePath != null && typefacePath.length() > 0) {
			Typeface typeface = Typeface.createFromAsset(context.getAssets(), typefacePath);
			if (typeface != null) {
				inputView.setTypeface(typeface);
			}
		}
	}
	
}
