/**
 *
 */
package org.sevendroids.scm.analyse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.sevendroids.scm.analyse.business.NoOfCommentComparator;
import org.sevendroids.scm.analyse.business.SCMConnectionException;
import org.sevendroids.scm.analyse.business.SCMReadLogException;
import org.sevendroids.scm.analyse.business.SVNLogReader;
import org.sevendroids.scm.analyse.business.SimpleSCMBusiness;
import org.sevendroids.scm.analyse.data.FileData;

/**
 * This is the main class to run the SCM Analyzer from console. The necessary
 * parameter can be provide either by a property file or via command line option
 * (with -D<parametername>). Mandatory are:<br>
 * <ul>
 * <li>-Durl: The URL to the starting point of the repository. At this level the
 * analyzer starts to fetch the comments
 * <li>-Dusername: The username to get read access to the repository
 * <li>-Dpassword: The password to the user
 * </ul>
 * The followings parameter are optional:
 * <ul>
 * <li>-DfromDate: The date where the reading of the comment should start. If
 * this parameter is not given then the search starts from the beginning of the
 * repository. The date format pattern is <YYYY-MM-dd>. For example
 * -DfromDate=2015-12-30
 * <li>-DtoDate: The date where the reading of the comment should end. If this
 * parameter is not given then the search today. The date format pattern is
 * <YYYY-MM-dd>. For example -DtoDate=2015-12-31
 * <li>-Dfile With this parameter a regular expression can be defined to look
 * for certain files.
 * <li>-Dauthor With this parameter a regular expression can be defined to
 * search for comments from certain author.
 * <li>-Dcomment With this parameter a regular expression can be defined to
 * search for comments with a certain content.
 * <li>-DminNoOfComments And finally only files with the given number of
 * comments are evaluated.
 * </ul>
 * To keep the console parameters shorter a property file can be specified. In
 * this case the location of the file need to be provided. The property file
 * itself contains all necessary information from above.
 *
 * @author 7droids
 *
 */
public class SCMAnalyzer {
	// Properties
	private static final String URL_PROPERTY = "url";
	private static final String USERNAME_PROPERTY = "username";
	private static final String PASSWORD_PROPERTY = "password";
	private static final String FROM_DATE_PROPERTY = "fromDate";
	private static final String TO_DATE_PROPERTY = "toDate";
	private static final String FILENAME_PROPERTY = "file";
	private static final String AUTHOR_PROPERTY = "author";
	private static final String COMMENT_PROPERTY = "comment";
	private static final String MIN_NO_OF_COMMENTS_PROPERTY = "minNoOfComments";
	// DateFormatPattern
	private static final String DATE_FORMAT = "YYYY-MM-dd";

	public SCMAnalyzer() {
		SimpleSCMBusiness business = new SimpleSCMBusiness(new SVNLogReader());
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		Date fromDate = null;
		try {
			if (System.getProperty(FROM_DATE_PROPERTY) != null)
				fromDate = formatter.parse(System.getProperty(FROM_DATE_PROPERTY));
		} catch (ParseException e) {
			System.out.println("The property <" + FROM_DATE_PROPERTY + "> with the value <"
					+ System.getProperty(FROM_DATE_PROPERTY) + "> is not parsable. The default value NULL is used.");
		}
		Date toDate = null;
		try {
			if (System.getProperty(TO_DATE_PROPERTY) != null)
				toDate = formatter.parse(System.getProperty(TO_DATE_PROPERTY));
		} catch (ParseException e) {
			System.out.println("The property <" + TO_DATE_PROPERTY + "> with the value <"
					+ System.getProperty(TO_DATE_PROPERTY) + "> is not parsable. The default value NULL is used.");
		}
		int minNoOfComments = -1;
		try {
			if (System.getProperty(MIN_NO_OF_COMMENTS_PROPERTY) != null)
				minNoOfComments = Integer.parseInt(System.getProperty(MIN_NO_OF_COMMENTS_PROPERTY));
		} catch (NumberFormatException e) {
			System.out.println("The property <" + MIN_NO_OF_COMMENTS_PROPERTY + "> with the value <"
					+ System.getProperty(MIN_NO_OF_COMMENTS_PROPERTY)
					+ "> is not parsable. The default value -1 is used.");
		}
		try {
			List<FileData> result = business.filter(
					business.readLog(System.getProperty(URL_PROPERTY), System.getProperty(USERNAME_PROPERTY),
							System.getProperty(PASSWORD_PROPERTY).toCharArray(), fromDate, toDate),
					System.getProperty(FILENAME_PROPERTY), System.getProperty(AUTHOR_PROPERTY),
					System.getProperty(COMMENT_PROPERTY), minNoOfComments);
			Collections.sort(result, new NoOfCommentComparator());
			Collections.reverse(result);
			System.out.println("#################### R e s u l t ####################");
			System.out.println("# Files with the most comments at the top           ");
			System.out.println("# Ordered by number of comments                     ");
			System.out.println("#");
			NumberFormat nf = new DecimalFormat("0000");
			for (FileData fileData : result) {
				System.out.println("# " + nf.format(fileData.getLogCount()) + " -> " + fileData.getFileName());
			}
			System.out.println("#");
			System.out.println("#####################################################");
		} catch (SCMConnectionException e) {
			System.out.println(
					"The connection to the SCM repository cannot be stablished. Please check the connection information.");
			System.out.println("Error message: " + e.getLocalizedMessage());
		} catch (SCMReadLogException e) {
			System.out.println("There is a problem reading the comments. Please check the following message.");
			System.out.println("Error message: " + e.getLocalizedMessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args != null && args.length > 0)
			readPropertyFile(args[0]);
		String errorMessage = checkProperties();
		if (errorMessage == null)
			new SCMAnalyzer();
		else
			printHelp(errorMessage);
	}

	private static void printHelp(String errorMessage) {
		System.out.println(errorMessage);
		System.out.println();
		System.out.println("##################################### H e l p ###################################");
		System.out.println("#                                                                               #");
		System.out.println("# This is the main class to run the SCM Analyzer from console. The necessary    #");
		System.out.println("# parameter can be provide either by a property file or via command line option #");
		System.out.println("# (with -D<parametername>). Mandatory are:                                      #");
		System.out.println("# - -Durl: The URL to the starting point of the repository. At this level the   #");
		System.out.println("#          analyzer starts to fetch the comments                                #");
		System.out.println("# - -Dusername: The username to get read access to the repository               #");
		System.out.println("# - -Dpassword: The password to the user                                        #");
		System.out.println("#                                                                               #");
		System.out.println("# The followings parameter are optional:                                        #");
		System.out.println("# - -DfromDate: The date where the reading of the comment should start. If this #");
		System.out.println("#               parameter is not given then the search starts from the          #");
		System.out.println("#               beginning of the repository.                                    #");
		System.out.println("#               The date format pattern is <YYYY-MM-dd>.                        #");
		System.out.println("#               For example -DfromDate=2015-12-30                               #");
		System.out.println("# - -DtoDate: The date where the reading of the comment should end. If this     #");
		System.out.println("#             parameter is not given then the search today.                     #");
		System.out.println("#             The date format pattern is <YYYY-MM-dd>.                          #");
		System.out.println("#             For example -DfromDate=2015-12-31s                                #");
		System.out.println("# - -Dfile With this parameter a regular expression can be defined to look for  #");
		System.out.println("#          certain files.                                                       #");
		System.out.println("# - -Dauthor With this parameter a regular expression can be defined to search  #");
		System.out.println("#            for comments from certain author.                                  #");
		System.out.println("# - -Dcomment With this parameter a regular expression can be defined to        #");
		System.out.println("#             search for comments with a certain content.                       #");
		System.out.println("# - -DminNoOfComments And finally only files with the given number of comments  #");
		System.out.println("#                     are evaluated. Negativ values are interpreted as if no    #");
		System.out.println("#                     value is provided                                         #");
		System.out.println("#                                                                               #");
		System.out.println("# To keep the console parameters shorter a property file can be specified. In   #");
		System.out.println("# this case the location of the file need to be provided. The property file     #");
		System.out.println("# itself contains all necessary information from above.                         #");
		System.out.println("#                                                                               #");
		System.out.println("##################################### H e l p ###################################");
	}

	private static String checkProperties() {
		StringBuilder errorMessage = new StringBuilder();
		// Check mandatory fields
		if (System.getProperty(URL_PROPERTY) == null || System.getProperty(AUTHOR_PROPERTY) == null
				|| System.getProperty(PASSWORD_PROPERTY) == null)
			errorMessage.append("Neither of the proprties <" + URL_PROPERTY + ">, <" + USERNAME_PROPERTY + "> or <"
					+ PASSWORD_PROPERTY + "> shall be empty.\n");

		if (System.getProperty(FROM_DATE_PROPERTY) != null)
			try {
				new SimpleDateFormat(DATE_FORMAT).parse(System.getProperty(FROM_DATE_PROPERTY));
			} catch (ParseException e) {
				errorMessage.append("The property <" + FROM_DATE_PROPERTY + "> is not parseble with the pattern <"
						+ DATE_FORMAT + ">.\n");
			}

		if (System.getProperty(TO_DATE_PROPERTY) != null)
			try {
				new SimpleDateFormat(DATE_FORMAT).parse(System.getProperty(TO_DATE_PROPERTY));
			} catch (ParseException e) {
				errorMessage.append("The property <" + TO_DATE_PROPERTY + "> is not parseble with the pattern <"
						+ DATE_FORMAT + ">.\n");
			}

		if (System.getProperty(AUTHOR_PROPERTY) != null)
			try {
				Pattern.compile(System.getProperty(AUTHOR_PROPERTY));
			} catch (PatternSyntaxException e) {
				errorMessage.append("The property <" + AUTHOR_PROPERTY + "> has not a valid regular expression <"
						+ System.getProperty(AUTHOR_PROPERTY) + ">.\n");
			}

		if (System.getProperty(COMMENT_PROPERTY) != null)
			try {
				Pattern.compile(System.getProperty(COMMENT_PROPERTY));
			} catch (PatternSyntaxException e) {
				errorMessage.append("The property <" + COMMENT_PROPERTY + "> has not a valid regular expression <"
						+ System.getProperty(COMMENT_PROPERTY) + ">.\n");
			}

		if (System.getProperty(MIN_NO_OF_COMMENTS_PROPERTY) != null)
			try {
				Integer.parseInt(System.getProperty(MIN_NO_OF_COMMENTS_PROPERTY));
			} catch (NumberFormatException e) {
				errorMessage.append("The property <" + MIN_NO_OF_COMMENTS_PROPERTY + "> has not a valid integer value <"
						+ System.getProperty(MIN_NO_OF_COMMENTS_PROPERTY) + ">.\n");
			}
		return errorMessage.toString().isEmpty() ? null : errorMessage.toString();
	}

	private static void readPropertyFile(String propertyFile) {
		File file = new File(propertyFile);
		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file)) {
				System.getProperties().load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
