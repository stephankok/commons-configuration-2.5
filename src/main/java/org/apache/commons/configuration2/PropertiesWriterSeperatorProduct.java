package org.apache.commons.configuration2;


import org.apache.commons.lang3.StringUtils;

public class PropertiesWriterSeperatorProduct {
	private String currentSeparator;
	private String globalSeparator;

	public String getCurrentSeparator() {
		return currentSeparator;
	}

	public void setCurrentSeparator(String currentSeparator) {
		this.currentSeparator = currentSeparator;
	}

	public String getGlobalSeparator() {
		return globalSeparator;
	}

	public void setGlobalSeparator(String globalSeparator) {
		this.globalSeparator = globalSeparator;
	}

	/**
	* Returns the separator to be used for the given property. This method is called by   {@code   writeProperty()}  . The string returned here is used as separator between the property key and its value. Per default the method checks whether a global separator is set. If this is the case, it is returned. Otherwise the separator returned by  {@code   getCurrentSeparator()}   is used, which was set by the associated layout object. Derived classes may implement a different strategy for defining the separator.
	* @param key   the property key
	* @param value   the value
	* @return   the separator to be used
	* @since   1.7
	*/
	public String fetchSeparator(final String key, final Object value) {
		return (globalSeparator != null) ? globalSeparator : StringUtils.defaultString(currentSeparator);
	}
}