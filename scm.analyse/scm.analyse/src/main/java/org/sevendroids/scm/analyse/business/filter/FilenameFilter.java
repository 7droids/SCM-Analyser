/**
 * 
 */
package org.sevendroids.scm.analyse.business.filter;

import java.util.regex.Pattern;

import org.sevendroids.scm.analyse.data.FileData;

/**
 * @author 7droids
 *
 */
public class FilenameFilter implements FileDataFilter<String> {

	@Override
	public FileData filter(FileData data, String filter) {
		if (isWildCardfilter(filter) || Pattern.matches(filter, data.getFileName()))
			return data;
		return null;
	}
}
