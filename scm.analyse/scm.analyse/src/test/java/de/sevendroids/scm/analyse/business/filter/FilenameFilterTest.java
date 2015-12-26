/**
 * 
 */
package de.sevendroids.scm.analyse.business.filter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.sevendroids.scm.analyse.data.FileData;

/**
 * @author 7droids
 *
 */
@Test(groups = "Unit")
public class FilenameFilterTest extends BaseFilterTest {

	/**
	 * Test method for
	 * {@link de.sevendroids.scm.analyse.business.filter.FilenameFilter#filter(de.sevendroids.scm.analyse.data.FileData, java.lang.String)}
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
				{ "Null filter don't change the input", createFileData("/path/createdFile.java", 1), null,
						createFileData("/path/createdFile.java", 1) },
				{ "Empty filter don't change the input", createFileData("/path/createdFile.java", 1), "",
						createFileData("/path/createdFile.java", 1) },
				{ "Filter with spaces only don't change the input", createFileData("/path/createdFile.java", 1), "  ",
						createFileData("/path/createdFile.java", 1) },
				{ "The filtering with \".*.java\" results in elements ending with \"java\"",
						createFileData("/path/FileWithFixedBug.java", 3), ".*.java",
						createFileData("/path/FileWithFixedBug.java", 3) },
				{ "Filtering for \".*.c\" (ending with c) returns NULL for a java file",
						createFileData("/path/FileWithFixedBug.java", 3), ".*.c", null },
				//
		};
	}
}
