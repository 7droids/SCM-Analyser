/**
 * 
 */
package de.sevendroids.scm.analyse.business;

/**
 * @author 7droids
 *
 */
public class SCMConnectionException extends Exception {
	private static final long serialVersionUID = 8902884057148312585L;

	/**
	 * @param message
	 * @param cause
	 */
	public SCMConnectionException(String url, String username, Throwable cause) {
		super("The connection to <" + url+ "> username <" + username + "> failed.", cause);
	}
}
