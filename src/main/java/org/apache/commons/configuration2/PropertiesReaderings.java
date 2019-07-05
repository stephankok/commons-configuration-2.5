package org.apache.commons.configuration2;


import org.apache.commons.configuration2.PropertiesConfiguration.PropertiesReader;

public class PropertiesReaderings {
	private PropertiesReaderingsName propertiesReaderingsName = new PropertiesReaderingsName();
	private String propertyValue;
    /** Stores the property separator of the last read property.*/
    /** Constant for the default properties separator.*/
    
    private String propertySeparator = PropertiesConfiguration.DEFAULT_SEPARATOR;
    
    
	public String getPropertyName() {
		return propertiesReaderingsName.getPropertyName();
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	/**
	* Sets the name of the current property. This method can be called by {@code  parseProperty()}  for storing the results of the parse operation. It also ensures that the property key is correctly escaped.
	* @param name  the name of the current property
	* @since  1.7
	*/
	public void initPropertyName(final String name) {
		propertiesReaderingsName.initPropertyName(name);
	}

	/**
	* Performs unescaping on the given property name.
	* @param name  the property name
	* @return  the unescaped property name
	* @since  2.4
	*/
	public String unescapePropertyName(final String name) {
		return propertiesReaderingsName.unescapePropertyName(name);
	}

	/**
	* Sets the value of the current property. This method can be called by {@code  parseProperty()}  for storing the results of the parse operation. It also ensures that the property value is correctly escaped.
	* @param value  the value of the current property
	* @since  1.7
	*/
	public void initPropertyValue(final String value, PropertiesReader propertiesReader) {
		propertyValue = propertiesReader.unescapePropertyValue(value);
	}

	public String getPropertySeparator() {
		return propertySeparator;
	}

	public void setPropertySeparator(String propertySeparator) {
		this.propertySeparator = propertySeparator;
	}
}