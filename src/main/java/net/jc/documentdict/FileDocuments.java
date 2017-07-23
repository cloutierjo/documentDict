package net.jc.documentdict;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * Files representing documents
 */
public class FileDocuments implements Iterable<FileDocument> {

	private File[] files;

	/**
	 * @param path to the files
	 * @throws FileNotFoundException when no file is found
	 */
	public FileDocuments(String path) throws FileNotFoundException {
		File file = new File(path);
		if(!file.exists()){
			throw new FileNotFoundException();
		}
		files = file.listFiles();
	}

	@Override
	public Iterator<FileDocument> iterator() {
		return new FileDocumentsIterator();
	}

	public class FileDocumentsIterator implements Iterator<FileDocument> {
		private int position = 0;
		
		public boolean hasNext() {
			return files.length > position;
		}

		public FileDocument next() {
			while(files.length > position){
				try {
					FileDocument document = new FileDocument(files[position]);
					++position;
					return document;
				} catch (IOException e) {
					System.err.println("Couldn't read " + files[position].getAbsolutePath() + " : " + e.getMessage());
				}
			}
			return null;
		}
	}
}
