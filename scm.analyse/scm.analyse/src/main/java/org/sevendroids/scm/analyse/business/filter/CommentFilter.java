/**
 * 
 */
package org.sevendroids.scm.analyse.business.filter;

import java.util.List;
import java.util.regex.Pattern;

import org.sevendroids.scm.analyse.data.FileData;
import org.sevendroids.scm.analyse.data.LogData;

/**
 * With this class filtering on SCM comments can be done. The filter uses
 * regular expressions to filter.
 * 
 * @author 7droids
 *
 */
public class CommentFilter implements FileDataFilter<String> {

	/**
	 * @see org.sevendroids.scm.analyse.business.filter.FileDataFilter#filter(FileData,
	 *      Object)
	 * @param data
	 *            The data containing the comments
	 * @param filter
	 *            A regular expression to search for in the comments. Empty
	 *            filter (NULL or only spaces) are treated as no filter.
	 * @return Returns NULL if no comments match the given regular expression
	 */
	@Override
	public FileData filter(FileData data, String filter) {
		if (isWildCardfilter(filter))
			return data;
		Pattern pattern = Pattern.compile(filter);
		List<LogData> logs = data.getLogs();
		FileData result = new FileData(data.getFileName());
		for (LogData logData : logs) {
			if (pattern.matcher(logData.getComment()).matches())
				result.addLogData(logData);
		}
		return result;
	}
}
