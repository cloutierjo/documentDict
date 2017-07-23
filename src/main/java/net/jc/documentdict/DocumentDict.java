package net.jc.documentdict;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.transform.TransformerException;

/**
 * Entry point of document dict
 */
public class DocumentDict 
{
    public static void main( String[] args )
    {
    	if(args.length==3){
	    	String sourcePath = getValidePath(args[0]);
	    	String clientDictPath = getValideFilePath(args[1]);
	    	String ServerDictPath = getValideFilePath(args[2]);
	    	
	    	produceDictionnary(sourcePath, clientDictPath, ServerDictPath);
    	} else {
    		System.err.println("The 3 arguments are required.");
    		printHelp();
    	}
    }

	private static void produceDictionnary(String sourcePath, String clientDictPath, String serverDictPath) {
		try {
			FileDocuments fileDocuments = new FileDocuments(sourcePath);
			Dictionary dictionary = new Dictionary(fileDocuments);
			dictionary.saveClientXml(clientDictPath);
			dictionary.saveServerXml(serverDictPath);
		} catch (FileNotFoundException e) {
			// already checked, it's probably a bug so logging the full stack
			e.printStackTrace();
		} catch (TransformerException e) {
			// something bad happened when writing the dictionary, 
			// logging the full stack to help the user understand it, the first level message isn't always clear
			e.printStackTrace();
		}
	}

	private static String getValidePath(String path) {
		if(new File(path).exists()){
			return path;
		}
		
		System.err.println("Path '" + path + "' is invalid");
		printHelp();
		return "";
	}

	private static String getValideFilePath(String path) {
		File parentPath = new File(path).getAbsoluteFile().getParentFile();
		if(parentPath.exists()){
			return path;
		}
		
		System.err.println("Path '" + parentPath.getPath() + "' is invalid");
		printHelp();
		return "";
	}

	private static void printHelp() {
		System.out.println("documentDict SOURCE CLIENT_OUT SERVER_OUT \n");
		System.out.println("SOURCE - The source folder for all the files");
		System.out.println("CLIENT_OUT - The destination for the client dictionary");
		System.out.println("SERVER_OUT - The destination for the server dictionary");
		System.exit(1);
	}
}
