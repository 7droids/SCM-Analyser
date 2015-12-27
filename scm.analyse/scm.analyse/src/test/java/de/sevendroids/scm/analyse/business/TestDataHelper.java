package de.sevendroids.scm.analyse.business;

import java.time.Instant;

import de.sevendroids.scm.analyse.data.FileData;
import de.sevendroids.scm.analyse.data.LogData;

public class TestDataHelper {

	private TestDataHelper() {
		super();
	}

	public static FileData createFileData(String fileName, int numberOfComments) {
		FileData fileData = new FileData(fileName);
		switch (numberOfComments) {
		case 3:
			fileData.addLogData(new LogData("erik", Instant.parse("2015-12-03T12:15:30.00Z"), "FIX: bug removed"));
		case 2:
			fileData.addLogData(
					new LogData("julia", Instant.parse("2015-12-02T11:15:30.00Z"), "CHG: some minor changes"));
		case 1:
			fileData.addLogData(new LogData("huibu", Instant.parse("2015-12-01T10:15:30.00Z"), "ADD: file added"));
		}
		return fileData;
	}

}