/**
 * 
 */
package de.sevendroids.scm.analyse.business.filter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.sevendroids.scm.analyse.business.TestDataHelper;
import de.sevendroids.scm.analyse.data.FileData;

/**
 * @author 7droids
 *
 */
@Test(groups = "Unit")
public class MinNumberOfCommentsFilterTest {

	/**
	 * Test method for
	 * {@link de.sevendroids.scm.analyse.business.filter.MinNumberOfCommentsFilter#filter(de.sevendroids.scm.analyse.data.FileData, java.lang.Integer)}
	 * .
	 */

	@Test(dataProvider = "noCommentTestCases")
	public final void testFilter(String testdescription, FileData input, Integer filter, FileData expectedResult)
			throws Exception {
		MinNumberOfCommentsFilter commentFilter = new MinNumberOfCommentsFilter();
		assertEquals(commentFilter.filter(input, filter), expectedResult, testdescription);
	}

	@DataProvider(name = "noCommentTestCases")
	protected Object[][] commentTestCases() {
		return new Object[][] {
				//
				{ "Null filter don't change the input", TestDataHelper.createFileData("/path/createdFile.java", 1),
						null, TestDataHelper.createFileData("/path/createdFile.java", 1) },
				{ "Negativ filter don't change the input", TestDataHelper.createFileData("/path/createdFile.java", 1),
						-1, TestDataHelper.createFileData("/path/createdFile.java", 1) },
				{ "Filtering with \"3*\" returns only FileData elements with at least \"3\" comments",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3), 3,
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3) },
				{ "Filtering with \"3*\" returns only FileData elements with at least \"3\" comments",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 2), 3,
						new FileData("/path/FileWithFixedBug.java") },
				//
		};
	}
}
