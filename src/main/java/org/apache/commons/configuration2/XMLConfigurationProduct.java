package org.apache.commons.configuration2;


import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.EntityResolver;
import org.apache.commons.configuration2.resolver.DefaultEntityResolver;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;

public class XMLConfigurationProduct implements Cloneable {
	private DocumentBuilder documentBuilder;
	private boolean validating;
	private boolean schemaValidation;
	private EntityResolver entityResolver = new DefaultEntityResolver();

	public DocumentBuilder getDocumentBuilder() {
		return documentBuilder;
	}

	public void setDocumentBuilder(DocumentBuilder documentBuilder) {
		this.documentBuilder = documentBuilder;
	}

	public boolean getValidating() {
		return validating;
	}

	public boolean getSchemaValidation() {
		return schemaValidation;
	}

	public EntityResolver getEntityResolver() {
		return entityResolver;
	}

	public void setEntityResolver(EntityResolver entityResolver) {
		this.entityResolver = entityResolver;
	}

	/**
	* Sets the value of the validating flag. This flag determines whether DTD/Schema validation should be performed when loading XML documents. This flag is evaluated only if no custom  {@code  DocumentBuilder}  was set.
	* @param validating  the validating flag
	* @since  1.2
	*/
	public void setValidating(final boolean validating) {
		if (!schemaValidation) {
			this.validating = validating;
		}
	}

	/**
	* Sets the value of the schemaValidation flag. This flag determines whether DTD or Schema validation should be used. This flag is evaluated only if no custom  {@code  DocumentBuilder}  was set. If set to true the XML document must contain a schemaLocation definition that provides resolvable hints to the required schemas.
	* @param schemaValidation  the validating flag
	* @since  1.7
	*/
	public void setSchemaValidation(final boolean schemaValidation) {
		this.schemaValidation = schemaValidation;
		if (schemaValidation) {
			this.validating = true;
		}
	}

	/**
	* Creates the  {@code  DocumentBuilder}  to be used for loading files. This implementation checks whether a specific {@code  DocumentBuilder}  has been set. If this is the case, this one is used. Otherwise a default builder is created. Depending on the value of the validating flag this builder will be a validating or a non validating  {@code  DocumentBuilder} .
	* @return  the  {@code  DocumentBuilder}  for loading configuration files
	* @throws ParserConfigurationException  if an error occurs
	* @since  1.2
	*/
	public DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
		if (documentBuilder != null) {
			return documentBuilder;
		}
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		if (validating) {
			factory.setValidating(true);
			if (schemaValidation) {
				factory.setNamespaceAware(true);
				factory.setAttribute(XMLConfiguration.JAXP_SCHEMA_LANGUAGE, XMLConfiguration.W3C_XML_SCHEMA);
			}
		}
		final DocumentBuilder result = factory.newDocumentBuilder();
		result.setEntityResolver(this.entityResolver);
		if (validating) {
			result.setErrorHandler(new DefaultHandler() {
				@Override
				public void error(final SAXParseException ex) throws SAXException {
					throw ex;
				}
			});
		}
		return result;
	}

	public Object clone() {
		try {
			return (XMLConfigurationProduct) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}
}