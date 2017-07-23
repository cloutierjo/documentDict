package net.jc.documentdict;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Dictionary creation
 */
public class Dictionary {

	protected static final String TAG_DICTIONARY = "DICTIONARY";
	protected static final String TAG_DOCUMENT = "DOCUMENT";
	protected static final String TAG_DOCUMENT_NAME = "DOCUMENT_NAME";
	protected static final String TAG_VERSION = "VERSION";
	protected static final String TAG_LENGTH = "LENGTH";
	protected static final String TAG_PATH_TO_FILE = "PATH_TO_FILE";

	private Iterable<FileDocument> fileDocuments;

	public Dictionary(Iterable<FileDocument> fileDocuments) {
		this.fileDocuments = fileDocuments;
	}

	/**
	 * Produce the client dictionary xml file
	 * 
	 * @param path to output the file
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public void saveClientXml(String path) throws ParserConfigurationException, TransformerException {
		saveXml(false, path);
	}


	/**
	 * Produce the server dictionary xml file
	 * 
	 * @param path to output the file
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public void saveServerXml(String path) throws ParserConfigurationException, TransformerException {
		saveXml(true, path);
	}

	private void saveXml(boolean doServerXml, String path) throws ParserConfigurationException, TransformerException{
		Document xml = getFullXml(doServerXml);
		// pretty print, not required, but help if debugging is required
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		DOMSource dom = new DOMSource(xml);
		StreamResult result = new StreamResult(new File(path));

		transformer.transform(dom, result);
	}

	protected Document getFullXml(boolean doServerXml) throws ParserConfigurationException, TransformerException {
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document xml = documentBuilder.newDocument();

		Element dict = xml.createElement(TAG_DICTIONARY);
		xml.appendChild(dict);
		
		for (FileDocument fileDocument : fileDocuments) {
			getDocumentXml(xml, dict, fileDocument, doServerXml);
		}
		
		return xml;
	}

	private void getDocumentXml(Document xml, Element dict, FileDocument fileDocument, boolean doServerXml) {
		Element document = xml.createElement(TAG_DOCUMENT);
		dict.appendChild(document);
		
		Element docName = xml.createElement(TAG_DOCUMENT_NAME);
		docName.appendChild(xml.createTextNode(fileDocument.getName()));
		document.appendChild(docName);
		
		Element docVersion = xml.createElement(TAG_VERSION);
		docVersion.appendChild(xml.createTextNode(fileDocument.getVersion()));
		document.appendChild(docVersion);
		
		Element docLength = xml.createElement(TAG_LENGTH);
		docLength.appendChild(xml.createTextNode(Integer.toString(fileDocument.getData().length())));
		document.appendChild(docLength);
		
		if(doServerXml){
			Element docPath = xml.createElement(TAG_PATH_TO_FILE);
			docPath.appendChild(xml.createTextNode(fileDocument.getPath()));
			document.appendChild(docPath);
		}
	}
}
