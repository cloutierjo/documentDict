package net.jc.documentdict;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

/**
 * File documents tests
 */
public class FileDocumentsTest 
{
	private FileDocuments fileDocuments;

	@Before
	public void setup() throws FileNotFoundException{
		fileDocuments = new FileDocuments("src/test/resources/testFiles");
	}
	
	@Test
	public void hasDocument() throws Exception {
		assertTrue(fileDocuments.iterator().hasNext());
	}
	
	@Test(expected=FileNotFoundException.class)
	public void fileNotExisting_ThrowException() throws Exception {
		new FileDocuments("test/resources/notExistingFile");
	}
	
	@Test
	public void documentCountIsGood() throws Exception {
		int documentCount=0;
		// FileDocuments doesn't have a size method
		for (@SuppressWarnings("unused") FileDocument fileDocument : fileDocuments) {
			++documentCount;
		}
		assertEquals(5, documentCount);
	}
	
	@Test
	public void documentAreFiled() throws Exception {
		for (FileDocument fileDocument : fileDocuments) {
			assertTrue(!fileDocument.getName().isEmpty());
		}
	}
}
