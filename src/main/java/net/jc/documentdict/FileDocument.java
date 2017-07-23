package net.jc.documentdict;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *	Information from file representing a documents
 */
public class FileDocument {
	private static final String TAG_DOCUMENT_NAME = "DOCUMENT_NAME";
	private static final String TAG_VERSION = "VERSION";
	private static final String TAG_DATA = "DATA";

	private String name;
	private String version;
	private String data;

	/**
	 * @param file representing a document
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public FileDocument(File file) throws FileNotFoundException, IOException {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xml = documentBuilder.parse(file);
			xml.getDocumentElement().normalize();
			name = xml.getElementsByTagName(TAG_DOCUMENT_NAME).item(0).getTextContent();
			version = xml.getElementsByTagName(TAG_VERSION).item(0).getTextContent();
			data = xml.getElementsByTagName(TAG_DATA).item(0).getTextContent();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getData() {
		return data;
	}
}
