package de.sevendroids.scm.analyse.business;

import java.util.Date;
import java.util.List;

import de.sevendroids.scm.analyse.data.FileHandler;

public interface LogReader<K> {

	FileHandler readEntries(List<K> svnEntries, FileHandler fileHandler);

	FileHandler readLog(Date fromDate, Date toDate, FileHandler fileHandler) throws SCMReadLogException;

	void disconnect();

	void connect(String username, String password, String url) throws SCMConnectionException;

}
