/**
 * 
 */
package de.sevendroids.scm.analyse.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a file from the SCM.
 * 
 * @author 7droids
 *
 */
public class FileData {
	private final String fileName;
	private final List<LogData> logs = new ArrayList<>();

	public FileData(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @param fileName
	 * @param logData
	 */
	public FileData(String fileName, LogData logData) {
		this(fileName);
		addLogData(logData);
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the logs
	 */
	public List<LogData> getLogs() {
		return logs;
	}

	/**
	 * @return The number of logs.
	 */
	public int getLogCount() {
		return logs.size();
	}

	/**
	 * @param logData
	 */
	public void addLogData(LogData logData) {
		if (logData != null)
			logs.add(logData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "FileData [fileName=" + fileName + "\nlogs=" + logs.subList(0, Math.min(logs.size(), maxLen)) + "]";
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
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((logs == null) ? 0 : logs.hashCode());
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
		FileData other = (FileData) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (logs == null) {
			if (other.logs != null)
				return false;
		} else if (!logs.equals(other.logs))
			return false;
		return true;
	}
}
