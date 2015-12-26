/**
 * 
 */
package de.sevendroids.scm.analyse.data;

import java.time.Instant;

/**
 * An immutable instance for the comment information of the SCM
 * 
 * @author 7droids
 *
 */
public class LogData {
	private final String author;
	private final Instant commentDate;
	private final String comment;

	/**
	 * @param author
	 * @param commentDate
	 * @param comment
	 */
	public LogData(String author, Instant commentDate, String comment) {
		this.author = author;
		this.commentDate = commentDate;
		this.comment = comment;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the commentDate
	 */
	public Instant getCommentDate() {
		return commentDate;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LogData [author=" + author + ", commentDate=" + commentDate + ", comment=" + comment + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((commentDate == null) ? 0 : commentDate.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogData other = (LogData) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (commentDate == null) {
			if (other.commentDate != null)
				return false;
		} else if (!commentDate.equals(other.commentDate))
			return false;
		return true;
	}

}
