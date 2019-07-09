package org.apache.commons.configuration2;

import java.io.IOException;
import org.apache.commons.configuration2.PropertiesConfiguration.PropertiesWriter;

public class PropertiesWriterSeperator {
	
    private PropertiesWriterSeperatorProduct propertiesWriterSeperatorProduct = new PropertiesWriterSeperatorProduct();

	/** Constant for the platform specific line separator.*/
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
	private String lineSeparator;

	public String getCurrentSeparator() {
		return propertiesWriterSeperatorProduct.getCurrentSeparator();
	}

	public void setCurrentSeparator(String currentSeparator) {
		propertiesWriterSeperatorProduct.setCurrentSeparator(currentSeparator);
	}

	public String getGlobalSeparator() {
		return propertiesWriterSeperatorProduct.getGlobalSeparator();
	}

	public void setGlobalSeparator(String globalSeparator) {
		propertiesWriterSeperatorProduct.setGlobalSeparator(globalSeparator);
	}

	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

	/**
	* Returns the separator to be used for the given property. This method is called by  {@code  writeProperty()} . The string returned here is used as separator between the property key and its value. Per default the method checks whether a global separator is set. If this is the case, it is returned. Otherwise the separator returned by {@code  getCurrentSeparator()}  is used, which was set by the associated layout object. Derived classes may implement a different strategy for defining the separator.
	* @param key  the property key
	* @param value  the value
	* @return  the separator to be used
	* @since  1.7
	*/
	public String fetchSeparator(final String key, final Object value) {
		return propertiesWriterSeperatorProduct.fetchSeparator(key, value);
	}

	/**
	* Returns the line separator.
	* @return  the line separator
	* @since  1.7
	*/
	public String getLineSeparator() {
		return (lineSeparator != null) ? lineSeparator : LINE_SEPARATOR;
	}

	/**
	* Helper method for writing a line with the platform specific line ending.
	* @param s  the content of the line (may be <b>null</b>)
	* @throws IOException  if an error occurs
	* @since  1.3
	*/
	public void writeln(final String s, PropertiesWriter propertiesWriter) throws IOException {
		if (s != null) {
			propertiesWriter.write(s);
		}
		propertiesWriter.write(getLineSeparator());
	}
}