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
public class CommentFilterTest {

	@Test(dataProvider = "commentTestCases")
	public final void testFilter(String testdescription, FileData input, String filter, FileData expectedResult)
			throws Exception {
		CommentFilter commentFilter = new CommentFilter();
		assertEquals(commentFilter.filter(input, filter), expectedResult, testdescription);
	}

	@DataProvider(name = "commentTestCases")
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
				{ "The filtering with \"ADD.*\" results in elements with comments containing \"ADD\" at the beginning",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3), "ADD.*",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 1) },
				{ "Filtering with no result returns a FileData with no comments",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3), "Wrong author",
						new FileData("/path/FileWithFixedBug.java") },
				//
		};
	}
}
