/**
 * 
 */
package org.sevendroids.scm.analyse.business.filter;

import static org.testng.Assert.assertEquals;

import org.sevendroids.scm.analyse.business.TestDataHelper;
import org.sevendroids.scm.analyse.business.filter.FilenameFilter;
import org.sevendroids.scm.analyse.data.FileData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author 7droids
 *
 */
@Test(groups = "Unit")
public class FilenameFilterTest {

	/**
	 * Test method for
	 * {@link org.sevendroids.scm.analyse.business.filter.FilenameFilter#filter(org.sevendroids.scm.analyse.data.FileData, java.lang.String)}
	 * .
	 */

	@Test(dataProvider = "fileNameTestCases")
	public final void testFilter(String testdescription, FileData input, String filter, FileData expectedResult)
			throws Exception {
		FilenameFilter commentFilter = new FilenameFilter();
		assertEquals(commentFilter.filter(input, filter), expectedResult, testdescription);
	}

	@DataProvider(name = "fileNameTestCases")
	protected Object[][] commentTestCases() {
		return new Object[][] {
				//
				{ "Null filter don't change the input", TestDataHelper.createFileData("/path/createdFile.java", 1),
						null, TestDataHelper.createFileData("/path/createdFile.java", 1) },
				{ "Empty filter don't change the input", TestDataHelper.createFileData("/path/createdFile.java", 1), "",
						TestDataHelper.createFileData("/path/createdFile.java", 1) },
				{ "Filter with spaces only don't change the input",
						TestDataHelper.createFileData("/path/createdFile.java", 1), "  ",
						TestDataHelper.createFileData("/path/createdFile.java", 1) },
				{ "The filtering with \".*.java\" results in elements ending with \"java\"",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3), ".*.java",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3) },
				{ "Filtering for \".*.c\" (ending with c) returns NULL for a java file",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3), ".*.c", null },
				//
		};
	}
}
