/**
 * 
 */
package org.sevendroids.scm.analyse.data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author 7droids
 *
 */
public class FileHandler {
	private final Map<String, FileData> fileMap = new HashMap<>();

	/**
	 * @param filename
	 *            Not NULL
	 * @param author
	 *            Not NULL
	 * @param changeDate
	 *            Not NULL
	 * @param comment
	 *            Not NULL
	 */
	public void add(String filename, String author, Instant changeDate, String comment) {
		LogData logData = new LogData(author, changeDate, comment);
		if (fileMap.containsKey(filename))
			fileMap.get(filename).addLogData(logData);
		else
			fileMap.put(filename, new FileData(filename, logData));
	}

	/**
	 * @param fileData
	 *            Not NULL
	 */
	public void add(FileData fileData) {
		List<LogData> logs = fileData.getLogs();
		for (LogData logData : logs) {
			add(fileData.getFileName(), logData.getAuthor(), logData.getCommentDate(), logData.getComment());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "FileHandler [fileMap=" + toString(fileMap, maxLen) + "]";
	}

	private String toString(Map<String, FileData> map, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext() && i < maxLen; i++) {
			String fileName = iterator.next();
			if (i > 0)
				builder.append(", ");
			builder.append("Filename=" + fileName + ", #comments=" + map.get(fileName).getLogs().size() + "\n");
		}
		builder.append("]");
		return builder.toString();
	}

	public final List<FileData> getFileDataList() {
		return new ArrayList<>(fileMap.values());
	}
}
