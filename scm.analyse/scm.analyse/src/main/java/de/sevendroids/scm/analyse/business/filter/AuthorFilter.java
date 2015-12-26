/**
 * 
 */
package de.sevendroids.scm.analyse.business.filter;

import java.util.List;
import java.util.regex.Pattern;

import de.sevendroids.scm.analyse.data.FileData;
import de.sevendroids.scm.analyse.data.LogData;

/**
 * @author 7droids
 *
 */
public class AuthorFilter implements FileDataFilter<String> {

	@Override
	public FileData filter(FileData data, String filter) {
		if (isWildCardfilter(filter))
			return data;
		Pattern pattern = Pattern.compile(filter);
		List<LogData> logs = data.getLogs();
		FileData result = new FileData(data.getFileName());
		for (LogData logData : logs) {
			if (pattern.matcher(logData.getAuthor()).matches())
				result.addLogData(logData);
		}
		return result;
	}
}
