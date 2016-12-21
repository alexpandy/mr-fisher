package com.naruku.fisher;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Convenience class to {@link ProgressDialog} to enable quick show and
 * hide of a progress dialog.
 *
 */
public class CustomProgressDialog extends ProgressDialog {

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public CustomProgressDialog(Context context) {
		super(context, 0);
	}
	
	/**
	 * Convenience function to initiate and show a dialog.
	 * 
	 * @param msgResourceId
	 * 		The message to be shown indicated by the ID in strings.xml
	 */
	public void show(int msgResourceId) {
		show(0, msgResourceId);
	}
	
	/**
	 * Convenience function to initiate and show a dialog.
	 * 
	 * @param message
	 * 		The message to be shown
	 */
	public void show(String message) {
		show(null, message);
	}
	
	/**
	 * Convenience function to initiate and show a dialog.
	 * 
	 * @param titleResourceId
	 * 		The title of the dialog indicated by the ID in strings.xml
	 * @param msgResourceId
	 * 		The message to be shown indicated by the ID in strings.xml
	 */
	public void show(int titleResourceId, int msgResourceId) {
		show(getContext().getString(titleResourceId), getContext().getString(msgResourceId));
	}
	
	/**
	 * Convenience function to initiate and show a dialog.
	 * 
	 * @param title
	 * 		The title of the dialog
	 * @param message
	 * 		The message to be shown
	 */
	public void show(String title, String message) {
		
		// Check if title is empty. If not, set the title to this dialog.
		if (title != null && title.length() > 0) {
			setTitle(title);
		}
		
		// Check if message is empty. If not, set the message.
		// We only check this to avoid NPE. Avoiding empty progress dialog is 
		// developer's burden.
		if (message != null && message.length() > 0) {
			setMessage(message);
		}
		
		show();
	}
	
	
	/**
	 * Initiates and shows a non-cancelable progress dialog
	 * 
	 * @param msgResourceId
	 * 		The message to be shown indicated by the ID in strings.xml
	 */
	public void showNonCancelable(int msgResourceId) {
		showNonCancelable(0, msgResourceId);
	}
	
	/**
	 * Initiates and shows a non-cancelable progress dialog
	 * 
	 * @param message
	 * 		The message to be shown
	 */
	public void showNonCancelable(String message) {
		showNonCancelable(null, message);
	}
	
	/**
	 * Initiates and shows a non-cancelable progress dialog
	 * 
	 * @param titleResourceId
	 * 		The title of the dialog indicated by the ID in strings.xml
	 * @param msgResourceId
	 * 		The message to be shown indicated by the ID in strings.xml
	 */
	public void showNonCancelable(int titleResourceId, int msgResourceId) {
		showNonCancelable(getContext().getString(titleResourceId), getContext().getString(msgResourceId));
	}
	
	/**
	 * Initiates and shows a non-cancelable progress dialog
	 * 
	 * @param title
	 * 		The title of the dialog
	 * @param message
	 * 		The message to be shown
	 */
	public void showNonCancelable(String title, String message) {
		setCancelable(false);
		show(title, message);
	}
	
	public void hide() {
		if (isShowing()) {
			cancel();
		}
	}
}
