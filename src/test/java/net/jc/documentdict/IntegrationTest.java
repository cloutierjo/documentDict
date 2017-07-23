package net.jc.documentdict;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests
 */
public class IntegrationTest 
{
	
	private Dictionary dictionary;
	@Before
	public void setup() throws FileNotFoundException {
		FileDocuments fileDocuments = new FileDocuments("src/test/resources/testFiles");
		dictionary = new Dictionary(fileDocuments);
	}
	@Test
	public void generatedServerFile_producedGoodResult() throws Exception {
		File targetFile = new File("target/it/serverDict.xml");
		targetFile.getParentFile().mkdirs();

		dictionary.saveServerXml(targetFile.getAbsolutePath());
		
		checkFileHash("A76097C51348695E55AC1018ABEB2ADB", targetFile);
	}
	
	@Test
	public void generatedClientFile_producedGoodResult() throws Exception {
		File targetFile = new File("target/it/clientDict.xml");
		targetFile.getParentFile().mkdirs();

		dictionary.saveClientXml(targetFile.getAbsolutePath());
		
		checkFileHash("2C28E7F8AEF9D45774F0C6B505EC3CF1", targetFile);
	}
	
	private void checkFileHash(String expected, File targetFile) throws IOException, NoSuchAlgorithmException {
		// This is not a really flexible way to test the file produce, 
		// but it's clear when the file has changed from the last validated format. 
		byte[] xmlProduced = Files.readAllBytes(targetFile.toPath());
		byte[] hash = MessageDigest.getInstance("MD5").digest(xmlProduced);
		assertEquals(expected, DatatypeConverter.printHexBinary(hash));
	}
}
