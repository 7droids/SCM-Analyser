/**
 * 
 */
package de.sevendroids.scm.analyse.business.filter;

import de.sevendroids.scm.analyse.data.FileData;

/**
 * This is the general interface to filter a given FileData for any information
 * inside.
 * 
 * @author 7droids
 *
 * @param <K>
 *            generic type to filter for
 */
public interface FileDataFilter<K> {

	/**
	 * The given FileData object should be filtered according to the given
	 * filter
	 * 
	 * @param data
	 *            The data to filter
	 * @param filter
	 *            The object to filter for
	 * @return The filtered element can be null
	 */
	FileData filter(FileData data, K filter);

	/**
	 * An empty filter (either NULL or only white spaces) are treated as no
	 * filter or any elements
	 * 
	 * @param filter
	 *            Filter expression to test
	 * @return TRUE if if is a wildcard filter
	 */
	default boolean isWildCardfilter(String filter) {
		return (filter == null || filter.trim().isEmpty());
	}
}
