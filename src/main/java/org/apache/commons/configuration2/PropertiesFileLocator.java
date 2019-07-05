package org.apache.commons.configuration2;


import org.apache.commons.configuration2.io.FileLocator;
import org.apache.commons.configuration2.ex.ConfigurationException;
import java.net.URL;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.io.FileLocatorUtils;

public class PropertiesFileLocator implements Cloneable {
	private FileLocator locator;

	public void setLocator(FileLocator locator) {
		this.locator = locator;
	}

	/**
	* Helper method for loading an included properties file. This method is called by  {@code  load()}  when an  {@code  include}  property is encountered. It tries to resolve relative file names based on the current base path. If this fails, a resolution based on the location of this properties file is tried.
	* @param fileName  the name of the file to load
	* @param optional  whether or not the  {@code  fileName}  is optional
	* @throws ConfigurationException  if loading fails
	*/
	public void loadIncludeFile(final String fileName, final boolean optional,
			PropertiesConfiguration propertiesConfiguration) throws ConfigurationException {
		if (locator == null) {
			throw new ConfigurationException(
					"Load operation not properly " + "initialized! Do not call read(InputStream) directly,"
							+ " but use a FileHandler to load a configuration.");
		}
		URL url = locateIncludeFile(locator.getBasePath(), fileName);
		if (url == null) {
			final URL baseURL = locator.getSourceURL();
			if (baseURL != null) {
				url = locateIncludeFile(baseURL.toString(), fileName);
			}
		}
		if (optional && url == null) {
			return;
		}
		if (url == null) {
			throw new ConfigurationException("Cannot resolve include file " + fileName);
		}
		final FileHandler fh = new FileHandler(propertiesConfiguration);
		fh.setFileLocator(locator);
		final FileLocator orgLocator = locator;
		try {
			fh.load(url);
		} finally {
			locator = orgLocator;
		}
	}

	/**
	* Tries to obtain the URL of an include file using the specified (optional) base path and file name.
	* @param basePath  the base path
	* @param fileName  the file name
	* @return  the URL of the include file or <b>null</b> if it cannot be resolved
	*/
	public URL locateIncludeFile(final String basePath, final String fileName) {
		final FileLocator includeLocator = FileLocatorUtils.fileLocator(locator).sourceURL(null).basePath(basePath)
				.fileName(fileName).create();
		return FileLocatorUtils.locate(includeLocator);
	}

	public Object clone() {
		try {
			return (PropertiesFileLocator) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}
}