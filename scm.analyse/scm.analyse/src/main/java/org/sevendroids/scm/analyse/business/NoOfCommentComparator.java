/**
 *
 */
package org.sevendroids.scm.analyse.business;

import java.util.Comparator;

import org.sevendroids.scm.analyse.data.FileData;

/**
 * The list of FileData can be sorted with this class. As the first comparator
 * the number of comments is used. If these are identical then the file name is
 * used as a second comparator
 *
 * @author 7droids
 *
 */
public class NoOfCommentComparator implements Comparator<FileData> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(FileData o1, FileData o2) {
		int diff = o1.getLogCount() - o2.getLogCount();
		if (diff == 0)
			return o1.getFileName().compareTo(o2.getFileName());
		return diff;
	}
}
