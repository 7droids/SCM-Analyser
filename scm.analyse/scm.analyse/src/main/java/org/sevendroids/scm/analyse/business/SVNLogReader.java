/**
 * 
 */
package org.sevendroids.scm.analyse.business;

import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.sevendroids.scm.analyse.data.FileHandler;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;

/**
 * @author 7droids
 *
 */
public class SVNLogReader implements LogReader<SVNLogEntry> {
	private SVNRepository repository = null;

	@Override
	public void connect(String username, char[] password, String url) throws SCMConnectionException {
		DAVRepositoryFactory.setup();
		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
			ISVNAuthenticationManager authManager = BasicAuthenticationManager.newInstance(username, password);
			repository.setAuthenticationManager(authManager);
		} catch (SVNException e) {
			throw new SCMConnectionException(url, username, e);
		}
	}

	@Override
	public void disconnect() {
		if (repository != null)
			repository.closeSession();
	}

	@Override
	public FileHandler readLog(Date fromDate, Date toDate, FileHandler fileHandler) throws SCMReadLogException {
		try {
			long startRevision = 0;
			long endRevision = -1; // Head
			if (fromDate != null)
				startRevision = repository.getDatedRevision(fromDate);
			if (toDate != null)
				endRevision = repository.getDatedRevision(toDate);
			return readEntries((List<SVNLogEntry>) repository.log(new String[] { "" }, null, startRevision, endRevision,
					true, true), fileHandler);
		} catch (SVNException e) {
			throw new SCMReadLogException(fromDate, toDate, e);
		}
	}

	@Override
	public FileHandler readEntries(List<SVNLogEntry> svnEntries, FileHandler fileHandler) {
		for (SVNLogEntry logEntry : svnEntries) {
			String author = logEntry.getAuthor();
			Instant changeDate = logEntry.getDate().toInstant();
			String comment = logEntry.getMessage();

			if (logEntry.getChangedPaths().size() > 0) {
				Set<String> changedPathsSet = logEntry.getChangedPaths().keySet();

				for (Iterator<String> changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
					SVNLogEntryPath entryPath = logEntry.getChangedPaths().get(changedPaths.next());
					fileHandler.add(entryPath.getPath(), author, changeDate, comment);
				}
			}
		}
		return fileHandler;
	}
}
