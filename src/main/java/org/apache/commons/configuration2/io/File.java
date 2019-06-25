package org.apache.commons.configuration2.io;

import java.net.URL;

public class File {
	
	/** The file name. */
    private String fileName;

    /** The base path. */
    private String basePath;

    /** The source URL. */
    private URL sourceURL;

    /** The encoding. */
    private String encoding;

    /** The file system. */
    private FileSystem fileSystem;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public FileSystem getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	public URL getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(URL sourceURL) {
		this.sourceURL = sourceURL;
	}
    
  

}
