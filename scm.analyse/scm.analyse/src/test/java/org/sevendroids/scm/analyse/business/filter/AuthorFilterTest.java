package org.sevendroids.scm.analyse.business.filter;

import static org.testng.Assert.assertEquals;

import java.time.Instant;

import org.sevendroids.scm.analyse.business.TestDataHelper;
import org.sevendroids.scm.analyse.business.filter.AuthorFilter;
import org.sevendroids.scm.analyse.data.FileData;
import org.sevendroids.scm.analyse.data.LogData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = "Unit")
public class AuthorFilterTest {

	@Test(dataProvider = "commentTestCases")
	public final void testFilter(String testdescription, FileData input, String filter, FileData expectedResult)
			throws Exception {
		AuthorFilter commentFilter = new AuthorFilter();
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
				{ "Filter with spaces only don't change the input",
						TestDataHelper.createFileData("/path/createdFile.java", 1), "  ",
						TestDataHelper.createFileData("/path/createdFile.java", 1) },
				{ "The filtering with \"julia\" results in one FileData with one comment",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3), "julia",
						new FileData("/path/FileWithFixedBug.java",
								new LogData("julia", Instant.parse("2015-12-02T11:15:30.00Z"),
										"CHG: some minor changes")) },
				{ "Filtering with no result return a FileData without any comments",
						TestDataHelper.createFileData("/path/FileWithFixedBug.java", 3), "Wrong author",
						new FileData("/path/FileWithFixedBug.java") },
				//
		};
	}

}
