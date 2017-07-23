package net.jc.documentdict;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Dictionary creation tests
 */
public class DictionaryTest 
{
	private static final String TEST_FILE1_NAME = "name";
	private static final String TEST_FILE1_VERSION = "v1";
	private static final String TEST_FILE1_DATA = "data";
	private static final String TEST_FILE1_DATA_LENGTH = "4";
	private static final String TEST_FILE1_PATH = "path/to/file";
	
	private FileDocument testFile1;
	private FileDocument testFile2;

	@Before
	public void setup() {
		testFile1 = new FileDocument(TEST_FILE1_NAME, TEST_FILE1_VERSION, TEST_FILE1_DATA, TEST_FILE1_PATH);
		testFile2 = new FileDocument("name2", "v2", "data2", "path/to/file2");
	}
	
	@Test
	public void emptyDictionary_produced() throws Exception {
		List<FileDocument> fileDocuments = new ArrayList<>();
		
		Dictionary dictionary = new Dictionary(fileDocuments);
		
		NodeList dictionaryElem = dictionary.getFullXml(true).getElementsByTagName(Dictionary.TAG_DICTIONARY);
		assertFalse(dictionaryElem.item(0).hasChildNodes());
	}
	
	@Test
	public void DictionaryWithTwoDoc_produced() throws Exception {
		List<FileDocument> fileDocuments = new ArrayList<>();
		fileDocuments.add(testFile1);
		fileDocuments.add(testFile2);
		
		Dictionary dictionary = new Dictionary(fileDocuments);
		
		NodeList dictionaryElem = dictionary.getFullXml(true).getElementsByTagName(Dictionary.TAG_DICTIONARY);
		assertEquals(2, dictionaryElem.item(0).getChildNodes().getLength());
	}
	
	@Test
	public void dictionaryWithOneDocument_getClientXML_produced() throws Exception {
		List<FileDocument> fileDocuments = new ArrayList<>();
		fileDocuments.add(testFile1);
		Dictionary dictionary = new Dictionary(fileDocuments);

		Document clientXml = dictionary.getFullXml(false);
		
		NodeList nameElem = clientXml.getElementsByTagName(Dictionary.TAG_DOCUMENT_NAME);
		assertEquals(TEST_FILE1_NAME,nameElem.item(0).getTextContent());
		NodeList versionElem = clientXml.getElementsByTagName(Dictionary.TAG_VERSION);
		assertEquals(TEST_FILE1_VERSION,versionElem.item(0).getTextContent());
		NodeList lengthElem = clientXml.getElementsByTagName(Dictionary.TAG_LENGTH);
		assertEquals(TEST_FILE1_DATA_LENGTH,lengthElem.item(0).getTextContent());
		NodeList pathElem = clientXml.getElementsByTagName(Dictionary.TAG_PATH_TO_FILE);
		assertEquals(0,pathElem.getLength());  // the path must not be present in client file
	}
	
	@Test
	public void dictionaryWithOneDocument_getServerXML_produced() throws Exception {
		List<FileDocument> fileDocuments = new ArrayList<>();
		fileDocuments.add(testFile1);
		Dictionary dictionary = new Dictionary(fileDocuments);

		Document clientXml = dictionary.getFullXml(true);
		
		NodeList nameElem = clientXml.getElementsByTagName(Dictionary.TAG_DOCUMENT_NAME);
		assertEquals(TEST_FILE1_NAME,nameElem.item(0).getTextContent());
		NodeList versionElem = clientXml.getElementsByTagName(Dictionary.TAG_VERSION);
		assertEquals(TEST_FILE1_VERSION,versionElem.item(0).getTextContent());
		NodeList lengthElem = clientXml.getElementsByTagName(Dictionary.TAG_LENGTH);
		assertEquals(TEST_FILE1_DATA_LENGTH,lengthElem.item(0).getTextContent());
		NodeList pathElem = clientXml.getElementsByTagName(Dictionary.TAG_PATH_TO_FILE);
		assertEquals(TEST_FILE1_PATH,pathElem.item(0).getTextContent());
	}
}
