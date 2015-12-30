/**
 *
 */
package org.sevendroids.scm.analyse.business;

import java.util.Comparator;

import org.sevendroids.scm.analyse.data.FileData;

/**
 * @author 7droids
 *
 */
public class NoOfCommentComperator implements Comparator<FileData> {

	@Override
	public int compare(FileData o1, FileData o2) {
		return o1.getLogCount() - o2.getLogCount();
	}
}
