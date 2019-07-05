package org.apache.commons.configuration2.builder.combined;


import org.apache.commons.configuration2.ex.ConfigurationException;

public class BaseConfigurationBuilderProviderNames {
	private final String builderClass;
	private final String reloadingBuilderClass;
	/** Stores the name of the configuration class to be created. */
    private final String configurationClass;

	public BaseConfigurationBuilderProviderNames(String bldrCls, String reloadBldrCls, String configCls) {
		builderClass = bldrCls;
		reloadingBuilderClass = reloadBldrCls;
		configurationClass = configCls;
	}

	public String getBuilderClass() {
		return builderClass;
	}

	public String getReloadingBuilderClass() {
		return reloadingBuilderClass;
	}
	
    public String getConfigurationClass()
    {
        return configurationClass;
    }

	/**
	* Determines the name of the class to be used for a new builder instance. This implementation selects between the normal and the reloading builder class, based on the passed in  {@code  ConfigurationDeclaration} . If a reloading builder is desired, but this provider has no reloading support, an exception is thrown.
	* @param decl  the current  {@code  ConfigurationDeclaration}
	* @return  the name of the builder class
	* @throws ConfigurationException  if the builder class cannot be determined
	*/
	public String determineBuilderClass(final ConfigurationDeclaration decl) throws ConfigurationException {
		if (decl.isReload()) {
			if (reloadingBuilderClass == null) {
				throw new ConfigurationException("No support for reloading for builder class " + builderClass);
			}
			return reloadingBuilderClass;
		}
		return builderClass;
	}
}