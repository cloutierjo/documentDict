package net.jc.documentdict;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

/**
 * File document tests
 */
public class FileDocumentTest 
{
	@Test
	public void DocumentInitializedFromFile() throws Exception {
		FileDocument fileDocument = new FileDocument(new File("src/test/resources/testFiles/f001.xml"));
		assertEquals("Lorem Ipsum",fileDocument.getName());
		assertEquals("1.0",fileDocument.getVersion());
		String expectedData = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		assertEquals(expectedData, fileDocument.getData());
		assertTrue(fileDocument.getPath().endsWith("src/test/resources/testFiles/f001.xml"));
		
	}
	
	@Test
	public void DocumentInitializedFromFileWithCData() throws Exception {
		FileDocument fileDocument = new FileDocument(new File("src/test/resources/testFiles/f002.xml"));
		String expectedData = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
		assertEquals(expectedData, fileDocument.getData());
	}
}
