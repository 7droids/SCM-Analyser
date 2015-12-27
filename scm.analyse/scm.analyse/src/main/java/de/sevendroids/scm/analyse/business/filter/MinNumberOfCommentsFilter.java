/**
 * 
 */
package de.sevendroids.scm.analyse.business.filter;

import de.sevendroids.scm.analyse.data.FileData;

/**
 * @author 7droids
 *
 */
public class MinNumberOfCommentsFilter implements FileDataFilter<Integer> {

	@Override
	public FileData filter(FileData data, Integer filter) {
		if (filter == null || filter <= 0)
			return data;
		if (data.getLogCount() >= filter)
			return data;
		return new FileData(data.getFileName());
	}
}
