package org.apache.commons.configuration2;

public interface ConfigDataInterface {
    /**
     * Returns the stored configuration.
     *
     * @return the configuration
     */
    public Configuration getConfiguration();

    /**
     * Returns the configuration's name.
     *
     * @return the name
     */
    public String getName();

    /**
     * Returns the at position of this configuration.
     *
     * @return the at position
     */
    public String getAt();
}
