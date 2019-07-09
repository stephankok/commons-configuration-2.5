package org.apache.commons.configuration2.builder.combined;


import org.apache.commons.configuration2.builder.XMLBuilderParametersImpl;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.builder.FileBasedBuilderProperties;
import org.apache.commons.configuration2.builder.ConfigurationBuilder;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import java.net.URL;

public class CurrentXMLParametersBuilder {
	private XMLBuilderParametersImpl currentXMLParameters;

	public XMLBuilderParametersImpl getCurrentXMLParameters() 
	{
		return currentXMLParameters;
	}

	public void setCurrentXMLParameters(XMLBuilderParametersImpl currentXMLParameters) 
	{
		this.currentXMLParameters = currentXMLParameters;
	}

	/**
	* Sets up an XML parameters object which is used to store properties related to XML and file-based configurations during creation of the result configuration. The properties stored in this object can be inherited to child configurations.
	* @throws ConfigurationException  if an error occurs
	*/
	public void setUpCurrentXMLParameters(CombinedBuilderParametersImpl thisCurrentParameters,
			CombinedConfigurationBuilder combinedConfigurationBuilder) throws ConfigurationException 
	{
		currentXMLParameters = new XMLBuilderParametersImpl();
		initDefaultBasePath(thisCurrentParameters, combinedConfigurationBuilder);
	}

	/**
	* Initializes a parameters object for a file-based configuration with properties already set for this parent builder. This method handles properties like a default file system or a base path.
	* @param params  the parameters object
	*/
	public void initChildFileBasedParameters(final FileBasedBuilderProperties<?> params) 
	{
		params.setBasePath(getBasePath());
		params.setFileSystem(currentXMLParameters.getFileHandler().getFileSystem());
	}

	/**
	* Returns the current base path of this configuration builder. This is used for instance by all file-based child configurations.
	* @return  the base path
	*/
	public String getBasePath() 
	{
		return currentXMLParameters.getFileHandler().getBasePath();
	}

	/**
	* Initializes the default base path for all file-based child configuration sources. The base path can be explicitly defined in the parameters of this builder. Otherwise, if the definition builder is a file-based builder, it is obtained from there.
	* @throws ConfigurationException  if an error occurs
	*/
	public void initDefaultBasePath(CombinedBuilderParametersImpl thisCurrentParameters,
			CombinedConfigurationBuilder combinedConfigurationBuilder) throws ConfigurationException 
	{
		assert thisCurrentParameters != null : "Current parameters undefined!";
		if (thisCurrentParameters.getBasePath() != null) {
			currentXMLParameters.setBasePath(thisCurrentParameters.getBasePath());
		} else {
			final ConfigurationBuilder<? extends HierarchicalConfiguration<?>> defBuilder = combinedConfigurationBuilder
					.getDefinitionBuilder();
			if (defBuilder instanceof FileBasedConfigurationBuilder) {
				@SuppressWarnings("rawtypes")
				final FileBasedConfigurationBuilder fileBuilder = (FileBasedConfigurationBuilder) defBuilder;
				final URL url = fileBuilder.getFileHandler().getURL();
				currentXMLParameters
						.setBasePath((url != null) ? url.toExternalForm() : fileBuilder.getFileHandler().getBasePath());
			}
		}
	}
}