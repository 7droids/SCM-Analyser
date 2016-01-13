/**
 *
 */
package org.sevendroids.scm.analyse.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author 7droids
 *
 */
public class PropertyUtil {
	// Properties
	public static final String URL_PROPERTY = "url";
	public static final String USERNAME_PROPERTY = "username";
	public static final String PASSWORD_PROPERTY = "password";
	public static final String FROM_DATE_PROPERTY = "fromDate";
	public static final String TO_DATE_PROPERTY = "toDate";
	public static final String FILENAME_PROPERTY = "file";
	public static final String AUTHOR_PROPERTY = "author";
	public static final String COMMENT_PROPERTY = "comment";
	public static final String MIN_NO_OF_COMMENTS_PROPERTY = "minNoOfComments";
	// DateFormatPattern
	public static final String DATE_FORMAT = "YYYY-MM-dd";

	/**
	 * Utility classes cannot be instantiated
	 */
	private PropertyUtil() {
		super();
	}

	public static String checkProperties() {
		return checkProperties(System.getProperties());
	}

	public static String checkProperties(Properties propertiesToCheck) {
		StringBuilder errorMessage = new StringBuilder();
		// Check mandatory fields
		if (propertiesToCheck.getProperty(URL_PROPERTY) == null
				|| propertiesToCheck.getProperty(USERNAME_PROPERTY) == null
				|| propertiesToCheck.getProperty(PASSWORD_PROPERTY) == null)
			errorMessage.append("Neither of the proprties <" + URL_PROPERTY + ">, <" + USERNAME_PROPERTY + "> or <"
					+ PASSWORD_PROPERTY + "> shall be empty.\n");

		if (propertiesToCheck.getProperty(FROM_DATE_PROPERTY) != null)
			try {
				new SimpleDateFormat(DATE_FORMAT).parse(propertiesToCheck.getProperty(FROM_DATE_PROPERTY));
			} catch (ParseException e) {
				errorMessage.append("The property <" + FROM_DATE_PROPERTY + "> is not parseble with the pattern <"
						+ DATE_FORMAT + ">.\n");
			}

		if (propertiesToCheck.getProperty(TO_DATE_PROPERTY) != null)
			try {
				new SimpleDateFormat(DATE_FORMAT).parse(propertiesToCheck.getProperty(TO_DATE_PROPERTY));
			} catch (ParseException e) {
				errorMessage.append("The property <" + TO_DATE_PROPERTY + "> is not parseble with the pattern <"
						+ DATE_FORMAT + ">.\n");
			}

		if (propertiesToCheck.getProperty(AUTHOR_PROPERTY) != null)
			try {
				Pattern.compile(propertiesToCheck.getProperty(AUTHOR_PROPERTY));
			} catch (PatternSyntaxException e) {
				errorMessage.append("The property <" + AUTHOR_PROPERTY + "> has not a valid regular expression <"
						+ propertiesToCheck.getProperty(AUTHOR_PROPERTY) + ">.\n");
			}

		if (propertiesToCheck.getProperty(COMMENT_PROPERTY) != null)
			try {
				Pattern.compile(propertiesToCheck.getProperty(COMMENT_PROPERTY));
			} catch (PatternSyntaxException e) {
				errorMessage.append("The property <" + COMMENT_PROPERTY + "> has not a valid regular expression <"
						+ propertiesToCheck.getProperty(COMMENT_PROPERTY) + ">.\n");
			}

		if (propertiesToCheck.getProperty(FILENAME_PROPERTY) != null)
			try {
				Pattern.compile(propertiesToCheck.getProperty(FILENAME_PROPERTY));
			} catch (PatternSyntaxException e) {
				errorMessage.append("The property <" + FILENAME_PROPERTY + "> has not a valid regular expression <"
						+ propertiesToCheck.getProperty(FILENAME_PROPERTY) + ">.\n");
			}

		if (propertiesToCheck.getProperty(MIN_NO_OF_COMMENTS_PROPERTY) != null)
			try {
				Integer.parseInt(propertiesToCheck.getProperty(MIN_NO_OF_COMMENTS_PROPERTY));
			} catch (NumberFormatException e) {
				errorMessage.append("The property <" + MIN_NO_OF_COMMENTS_PROPERTY + "> has not a valid integer value <"
						+ propertiesToCheck.getProperty(MIN_NO_OF_COMMENTS_PROPERTY) + ">.\n");
			}
		return errorMessage.toString().isEmpty() ? null : errorMessage.toString();
	}

}
