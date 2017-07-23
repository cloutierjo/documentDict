package net.jc.documentdict;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * File documents tests
 */
public class FileDocumentsTest 
{
	@Test
	public void hasDocument() throws Exception {
		FileDocuments fileDocuments = new FileDocuments("src/test/resources/testFiles");
		assertTrue(fileDocuments.iterator().hasNext());
	}
	
	@Test(expected=FileNotFoundException.class)
	public void fileNotFoundThrowException() throws Exception {
		new FileDocuments("test/resources/notExistingFile");
	}
	
	@Test
	public void documentCountIsGood() throws Exception {
		int documentCount=0;
		FileDocuments fileDocuments = new FileDocuments("src/test/resources/testFiles");
		for (FileDocument fileDocument : fileDocuments) {
			++documentCount;
		}
		assertEquals(5, documentCount);
	}
	
	@Test
	public void documentAreFiled() throws Exception {
		FileDocuments fileDocuments = new FileDocuments("src/test/resources/testFiles");
		for (FileDocument fileDocument : fileDocuments) {
			assertTrue(!fileDocument.getName().isEmpty());
		}
	}
}
