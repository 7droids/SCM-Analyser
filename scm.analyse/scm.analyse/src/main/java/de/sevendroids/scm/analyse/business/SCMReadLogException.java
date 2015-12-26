/**
 * 
 */
package de.sevendroids.scm.analyse.business;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author 7droids
 *
 */
public class SCMReadLogException extends Exception {

	private static final long serialVersionUID = 1820628521731019747L;

	public SCMReadLogException(Date fromDate, Date toDate, Throwable cause) {
		super("An exception occured with a from date = <"
				+ (fromDate != null ? DateFormat.getDateInstance().format(fromDate) : null) + "> and a to date = <"
				+ (toDate != null ? DateFormat.getDateInstance().format(toDate) : null) + ">.", cause);
	}
}
