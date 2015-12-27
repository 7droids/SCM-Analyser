/**
 * 
 */
package de.sevendroids.scm.analyse.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.sevendroids.scm.analyse.business.filter.AuthorFilter;
import de.sevendroids.scm.analyse.business.filter.CommentFilter;
import de.sevendroids.scm.analyse.business.filter.FileDataFilter;
import de.sevendroids.scm.analyse.business.filter.FilenameFilter;
import de.sevendroids.scm.analyse.business.filter.MinNumberOfCommentsFilter;
import de.sevendroids.scm.analyse.data.FileData;
import de.sevendroids.scm.analyse.data.FileHandler;

/**
 * In this class the work is done. - Connection to the SCM system - Filtering
 * the data - Rating the data - Sorting the data
 * 
 * @author 7droids
 *
 */
public class SimpleSCMBusiness {
	private LogReader<?> logReader;
	private FileDataFilter<String> fileNameDataFilter = new FilenameFilter();
	private FileDataFilter<String> authorDataFilter = new AuthorFilter();
	private FileDataFilter<String> commentDataFilter = new CommentFilter();
	private FileDataFilter<Integer> nrCommentDataFilter = new MinNumberOfCommentsFilter();

	/**
	 * @param logReader
	 */
	public SimpleSCMBusiness(LogReader<?> logReader) {
		this.logReader = logReader;
	}

	/**
	 * Reading the comments from the SCM system.
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param fromDate
	 *            can be NULL
	 * @param toDate
	 *            can be NULL
	 * @return a container of type FileHandler with all the log information
	 * @throws SCMConnectionException
	 *             is thrown when the connection is not possible
	 * @throws SCMReadLogException
	 *             is thrown if any exceptions occurs during reading a log entry
	 */
	public final FileHandler readLog(String url, String username, char[] password, Date fromDate, Date toDate)
			throws SCMConnectionException, SCMReadLogException {
		logReader.connect(username, password, url);
		return logReader.readLog(fromDate, toDate, new FileHandler());
	}

	/**
	 * In this method the actual filtering is done. The filtering is done in the
	 * following order: # Filename # Author # Comment content # Comment count
	 * 
	 * @param fileHandler
	 *            container with all unfiltered log information per file
	 * @param fileNameFilter
	 *            The pattern to filter the file name, can be NULL
	 * @param authorFiler
	 *            The pattern to filter the authors name, can be NULL
	 * @param commentFilter
	 *            The pattern to filter the comment, can be NULL
	 * @param commentCountFilter
	 *            Minimum number of comments per file. Values less or equal 0
	 *            are interpreted as no filter.
	 * @return
	 */
	public List<FileData> filter(FileHandler fileHandler, String fileNameFilter, String authorFiler,
			String commentFilter, Integer commentCountFilter) {
		List<FileData> listFilteredData = new ArrayList<>();
		for (FileData fileData : fileHandler.getFileDataList()) {
			FileData filterResult = fileNameDataFilter.filter(fileData, fileNameFilter);
			// if the file is still relevant then move on with filtering
			if (filterResult != null) {
				filterResult = authorDataFilter.filter(filterResult, authorFiler);
				filterResult = commentDataFilter.filter(filterResult, commentFilter);
				filterResult = nrCommentDataFilter.filter(filterResult, commentCountFilter);
				// if the file still contains log information then store for
				// next process step
				if (filterResult.getLogCount() > 0)
					listFilteredData.add(filterResult);
			}
		}
		return listFilteredData;
	}
}
