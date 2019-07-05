package org.apache.commons.configuration2;


import org.apache.commons.text.StringEscapeUtils;

public class PropertiesReaderingsName {
	private String propertyName;

	public String getPropertyName() {
		return propertyName;
	}

	/**
	* Sets the name of the current property. This method can be called by  {@code   parseProperty()}   for storing the results of the parse operation. It also ensures that the property key is correctly escaped.
	* @param name   the name of the current property
	* @since   1.7
	*/
	public void initPropertyName(final String name) {
		propertyName = unescapePropertyName(name);
	}

	/**
	* Performs unescaping on the given property name.
	* @param name   the property name
	* @return   the unescaped property name
	* @since   2.4
	*/
	public String unescapePropertyName(final String name) {
		return StringEscapeUtils.unescapeJava(name);
	}
}