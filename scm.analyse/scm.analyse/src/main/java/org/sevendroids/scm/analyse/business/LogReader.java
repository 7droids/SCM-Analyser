package org.sevendroids.scm.analyse.business;

import java.util.Date;
import java.util.List;

import org.sevendroids.scm.analyse.data.FileHandler;

public interface LogReader<K> {

	FileHandler readEntries(List<K> svnEntries, FileHandler fileHandler);

	FileHandler readLog(Date fromDate, Date toDate, FileHandler fileHandler) throws SCMReadLogException;

	void disconnect();

	void connect(String username, char[] password, String url) throws SCMConnectionException;

}
