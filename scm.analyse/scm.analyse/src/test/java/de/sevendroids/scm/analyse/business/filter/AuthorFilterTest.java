package de.sevendroids.scm.analyse.business.filter;

import static org.testng.Assert.assertEquals;

import java.time.Instant;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.sevendroids.scm.analyse.data.FileData;
import de.sevendroids.scm.analyse.data.LogData;

@Test(groups = "Unit")
public class AuthorFilterTest extends BaseFilterTest {

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
				{ "Null filter don't change the input", createFileData("/path/createdFile.java", 1), null,
						createFileData("/path/createdFile.java", 1) },
				{ "Empty filter don't change the input", createFileData("/path/createdFile.java", 1), "",
						createFileData("/path/createdFile.java", 1) },
				{ "Filter with spaces only don't change the input", createFileData("/path/createdFile.java", 1), "  ",
						createFileData("/path/createdFile.java", 1) },
				{ "Filter with spaces only don't change the input", createFileData("/path/createdFile.java", 1), "  ",
						createFileData("/path/createdFile.java", 1) },
				{ "The filtering with \"julia\" results in one FileData with one comment",
						createFileData("/path/FileWithFixedBug.java", 3), "julia",
						new FileData("/path/FileWithFixedBug.java",
								new LogData("julia", Instant.parse("2015-12-02T11:15:30.00Z"),
										"CHG: some minor changes")) },
				{ "Filtering with no result return a FileData without any comments",
						createFileData("/path/FileWithFixedBug.java", 3), "Wrong author",
						new FileData("/path/FileWithFixedBug.java") },
				//
		};
	}

}
