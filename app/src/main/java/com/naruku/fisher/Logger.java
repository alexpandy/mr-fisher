package com.naruku.fisher;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;

public class Logger {

	/**
	 * Assign this value to {@link Logger#LOG_LEVEL} to disable logs
	 * completely.
	 */
	private static final int LOG_LEVEL_DISABLED = 0x8;


	/**
	 * LOG_LEVEL by default is set to INFO level.
	 * This enables the following logs - INFO, WARN, ERROR, ASSERT
	 */
	private static final int LOG_LEVEL = Log.DEBUG;


	/**
	 * Enables logging to file in SD card. Disabled
	 * by default since its costly. Use only when its
	 * necessary.
	 */
	private static final boolean FILE_LOG = false;


	/**
	 * Holds the file instance to which we write the logs.
	 * This is to avoid creating several file instances each time a log
	 * is written
	 */
	private static File s_LogFile = null;


	/**
	 * The name of the file to which Logs will be written to.
	 * Can be changed to represent logs from each application
	 */
	private static final String LOG_FILE_NAME = "Logger.txt";

	/**
	 * The maximum file size log files are allowed to be written
	 */
	private static final long MAX_LOG_FILE_SIZE = 100 * 1024 * 1024; 	// 100 MB

	/**
	 * The default log tag to be used if not provided. Set this value if
	 * a default Log tag is required.
	 */
	private static final String DEFAULT_LOG_TAG = "FishCatch";

	/**
	 * Utility function that prints the message to Console if {@link Logger#LOG_LEVEL}
	 * is lesser than {@link Log#INFO}
	 *
	 * <p>If {@link Logger#FILE_LOG} is true, the logs are written to file {@link Logger#LOG_FILE_NAME}
	 * in SD card. </p>
	 *
	 * @param tag
	 * 			Tag to be written to console.
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void i(String tag, String message) {
		if (canLog(Log.INFO)) {
			Log.i(tag, message);

			if (FILE_LOG) {
				writeLogToFile("INFO", tag, message);
			}
		}
	}

	/**
	 * Convenience function for {@link Logger#i(String, String)} using
	 * {@link Logger#DEFAULT_LOG_TAG} for tag.
	 *
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void i(String message) {
		i(DEFAULT_LOG_TAG, message);
	}

	/**
	 * Convenience function for {@link Logger#v(String, String)} using
	 * {@link Logger#DEFAULT_LOG_TAG} for tag.
	 *
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void v(String message) {
		v(DEFAULT_LOG_TAG, message);
	}

	/**
	 * Utility function that prints the message to Console if {@link Logger#LOG_LEVEL}
	 * is lesser than {@link Log#VERBOSE}
	 *
	 * <p>If {@link Logger#FILE_LOG} is true, the logs are written to file {@link Logger#LOG_FILE_NAME}
	 * in SD card. </p>
	 *
	 * @param tag
	 * 			Tag to be written to console.
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void v(String tag, String message) {
		if (canLog(Log.VERBOSE)) {
			Log.v(tag, message);

			if (FILE_LOG) {
				writeLogToFile("VERBOSE", tag, message);
			}
		}
	}

	/**
	 * Convenience function for {@link Logger#d(String, String)} using
	 * {@link Logger#DEFAULT_LOG_TAG} for tag.
	 *
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void d(String message) {
		d(DEFAULT_LOG_TAG, message);
	}

	/**
	 * Utility function that prints the message to Console if {@link Logger#LOG_LEVEL}
	 * is lesser than {@link Log#DEBUG}
	 *
	 * <p>If {@link Logger#FILE_LOG} is true, the logs are written to file {@link Logger#LOG_FILE_NAME}
	 * in SD card. </p>
	 *
	 * @param tag
	 * 			Tag to be written to console.
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void d(String tag, String message) {
		if (canLog(Log.DEBUG)) {
			Log.d(tag, message);

			if (FILE_LOG) {
				writeLogToFile("DEBUG", tag, message);
			}
		}
	}

	/**
	 * Convenience function for {@link Logger#w(String, String)} using
	 * {@link Logger#DEFAULT_LOG_TAG} for tag.
	 *
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void w(String message) {
		w(DEFAULT_LOG_TAG, message);
	}

	/**
	 * Utility function that prints the message to Console if {@link Logger#LOG_LEVEL}
	 * is lesser than {@link Log#WARN}
	 *
	 * <p>If {@link Logger#FILE_LOG} is true, the logs are written to file {@link Logger#LOG_FILE_NAME}
	 * in SD card. </p>
	 *
	 * @param tag
	 * 			Tag to be written to console.
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void w(String tag, String message) {
		if (canLog(Log.WARN)) {
			Log.w(tag, message);

			if (FILE_LOG) {
				writeLogToFile("WARN", tag, message);
			}
		}
	}

	/**
	 * Convenience function for {@link Logger#e(String, String)} using
	 * {@link Logger#DEFAULT_LOG_TAG} for tag.
	 *
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void e(String message) {
		e(DEFAULT_LOG_TAG, message);
	}

	/**
	 * Utility function that prints the message to Console if {@link Logger#LOG_LEVEL}
	 * is lesser than {@link Log#ERROR}
	 *
	 * <p>If {@link Logger#FILE_LOG} is true, the logs are written to file {@link Logger#LOG_FILE_NAME}
	 * in SD card. </p>
	 *
	 * @param tag
	 * 			Tag to be written to console.
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void e(String tag, String message) {
		if (canLog(Log.ERROR)) {
			Log.e(tag, message);

			if (FILE_LOG) {
				writeLogToFile("ERROR", tag, message);
			}
		}
	}

	/**
	 * Convenience function for {@link Logger#a(String, String)} using
	 * {@link Logger#DEFAULT_LOG_TAG} for tag.
	 *
	 * @param message
	 * 			Message to be written to console.
	 */
	public static void a(String message) {
		a(DEFAULT_LOG_TAG, message);
	}

	/**
	 * Utility function that prints the message to Console if {@link Logger#LOG_LEVEL}
	 * is lesser than {@link Log#ASSERT}
	 *
	 * <p>If {@link Logger#FILE_LOG} is true, the logs are written to file {@link Logger#LOG_FILE_NAME}
	 * in SD card. </p>
	 *
	 * @param tag
	 * 			Tag to be written to console.
	 * @param message
	 * 			Message to be written to console.
	 * @see Log#wtf(String, String)
	 */
	public static void a(String tag, String message) {
		if (canLog(Log.ASSERT)) {
			Log.wtf(tag, message);

			if (FILE_LOG) {
				writeLogToFile("ASSERT", tag, message);
			}
		}
	}

	public static void exc(Exception e) {
		if (canLog(Log.ERROR)) {
			e.printStackTrace();

			if (FILE_LOG) {
				writeStackTraceToFile(e);
			}
		}
	}

	/**
	 * Determines if the current log level is allowed for writing to console
	 * @param logLevel
	 * 			The log level to be tested for eligibility
	 * @return
	 * 			True, if the logging for the level is allowed.
	 * 			False, if otherwise.
	 */
	private static boolean canLog(int logLevel) {
		return logLevel >= LOG_LEVEL;
	}

	/**
	 * Funtion to check SD card is mounted and is available for writing.
	 * This function does not verify if the permission WRITE_EXTERNAL_STORAGE has
	 * been granted in the application's manifest.
	 *
	 * @return
	 * 		TRUE, if we can write to SD card. False, if otherwise.
	 */
	private static boolean isSdcardMounted() {
		String state = Environment.getExternalStorageState();

		if (!state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			Log.i(DEFAULT_LOG_TAG, "SDCard is NOT mounted. Cannot write Logs to FILE");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Prints the message to file specified by {@link Logger#LOG_FILE_NAME} if SD card is mounted
	 * and available for writing.
	 * 
	 * @param logType
	 * 			The type of log to be written. One of "VERBOSE", "DEBUG", "INFO", "WARN", "ERROR", "ASSERT"
	 * @param tag
	 * 			The tag that was designed for console. Used to indicate application or class
	 * @param message
	 * 			The log message to be written
	 */
	private static synchronized void writeLogToFile(String logType, String tag, String message) {
		if (isSdcardMounted()) {
			PrintStream printStream = null;
			
			try {
				printStream = getPrintStream();
				
				String msg = getTime() + ": " + logType + "/" + tag + ": " + message + System.getProperty("line.separator");
				printStream.write(msg.getBytes());
				printStream.flush();
				
			} catch (Exception e) {
			//	AirbrakeNotifier.notify(e);

				Log.i(DEFAULT_LOG_TAG, "Exception in writing Logs to FILE: " + e.getMessage());
				
			} finally {
				// Close the stream. Do not enable file logs unless absolutely necessary.
				// This is too costly.
				if (printStream != null) {
					printStream.close();
			    }
			}
		}
	}
	
	private static synchronized void writeStackTraceToFile(Exception e) {
		if (isSdcardMounted()) {
			PrintStream printStream = null;
			
			try {
				printStream = getPrintStream();
				e.printStackTrace(printStream);
			} catch (Exception ex) {
			//	AirbrakeNotifier.notify(ex);

				Log.i(DEFAULT_LOG_TAG, "Exception in writing stack trace to FILE: " + ex.getMessage());
			} finally {
				// Close the stream. Do not enable file logs unless absolutely necessary.
				// This is too costly.
				if (printStream != null) {
					printStream.close();
			    }
			}
		
		}
	}
	
	private synchronized static PrintStream getPrintStream() {
		
		PrintStream printStream = null;
		if (s_LogFile == null) {
			s_LogFile = new File(Environment.getExternalStorageDirectory(), LOG_FILE_NAME);
		}
		
		try {
			if (s_LogFile.length() > MAX_LOG_FILE_SIZE) {
				// Maximum size for Log file has been breached. Clear the logs and start anew.
				printStream = new PrintStream(s_LogFile);		// Overwrite
			} else {
				// We can continue appending
				OutputStream outputFile = new FileOutputStream(s_LogFile, true);	// Append
				printStream = new PrintStream(outputFile);
			}
		} catch (FileNotFoundException e) {
			//AirbrakeNotifier.notify(e);

			Log.e(DEFAULT_LOG_TAG, "Exception in getting stream: " + e.getMessage());
		}
		return printStream;
	}
	
	/**
	 * @return
	 * 		Returns time in a format that can be printed on the file for each log statements.
	 */
	private static String getTime(){
		Calendar calendar= Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		String time = calendar.get(Calendar.DATE) + "/"
				+ calendar.get(Calendar.MONTH) + "/"
				+ calendar.get(Calendar.YEAR) + " "
				+ calendar.get(Calendar.HOUR) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND);
		return time;
	}
	
}
