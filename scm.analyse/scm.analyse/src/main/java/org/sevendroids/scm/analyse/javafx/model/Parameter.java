/**
 *
 */
package org.sevendroids.scm.analyse.javafx.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author 7droids
 *
 */
public class Parameter {

	private final StringProperty url;
	private final StringProperty username;
	private final StringProperty password;

	private final ObjectProperty<Date> fromDate;
	private final ObjectProperty<Date> toDate;
	private final StringProperty fileNameFilter;
	private final StringProperty commentFilter;
	private final StringProperty authorFilter;
	private final IntegerProperty minNoOfCommentsFilter;

	/**
	 * Constructor with some initial data and the mandatory values.
	 *
	 * @param url
	 * @param user
	 * @param pw
	 */
	public Parameter(String url, String user, char[] pw) {
		this.url = new SimpleStringProperty(url);
		this.username = new SimpleStringProperty(user);
		this.password = new SimpleStringProperty(new String(pw));

		// Default values
		fromDate = new SimpleObjectProperty<>();
		toDate = new SimpleObjectProperty<>();
		fileNameFilter = new SimpleStringProperty();
		commentFilter = new SimpleStringProperty();
		authorFilter = new SimpleStringProperty();
		minNoOfCommentsFilter = new SimpleIntegerProperty(0);
	}

	/**
	 * @return the url
	 */
	public StringProperty getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url.set(url);
	}

	/**
	 * @return the username
	 */
	public StringProperty getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username.set(user);
	}

	/**
	 * @return the password
	 */
	public StringProperty getPassword() {
		return password;
	}

	public void setPassword(char[] pw) {
		this.password.set(new String(pw));
	}

	/**
	 * @return the fromDate
	 */
	public ObjectProperty<Date> getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date from) {
		fromDate.set(from);
	}

	/**
	 * @return the toDate
	 */
	public ObjectProperty<Date> getToDate() {
		return toDate;
	}

	public void setToDate(Date to) {
		toDate.set(to);
	}

	/**
	 * @return the fileName
	 */
	public StringProperty getFileNameFilter() {
		return fileNameFilter;
	}

	public void setFileNameFilter(String file) {
		fileNameFilter.set(file);
	}

	/**
	 * @return the comment
	 */
	public StringProperty getCommentFilter() {
		return commentFilter;
	}

	public void setCommentFilter(String comment) {
		this.commentFilter.set(comment);
	}

	/**
	 * @return the author
	 */
	public StringProperty getAuthorFilter() {
		return authorFilter;
	}

	public void setAuthorFilter(String author) {
		this.authorFilter.set(author);
	}

	/**
	 * @return the minNoOfComments
	 */
	public IntegerProperty getMinNoOfCommentsFilter() {
		return minNoOfCommentsFilter;
	}

	public void setMinOfCommentsFilter(int min) {
		this.minNoOfCommentsFilter.set(min);
	}
}
