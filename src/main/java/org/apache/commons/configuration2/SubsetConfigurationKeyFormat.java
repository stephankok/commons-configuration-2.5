package org.apache.commons.configuration2;


public class SubsetConfigurationKeyFormat implements Cloneable {
	private String prefix;
	private String delimiter;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	* Return the key in the parent configuration associated to the specified key in this subset.
	* @param key  The key in the subset.
	* @return  the key as to be used by the parent
	*/
	public String getParentKey(final String key) {
		if ("".equals(key) || key == null) {
			return prefix;
		}
		return delimiter == null ? prefix + key : prefix + delimiter + key;
	}

	/**
	* Return the key in the subset configuration associated to the specified key in the parent configuration.
	* @param key  The key in the parent configuration.
	* @return  the key in the context of this subset configuration
	*/
	public String getChildKey(final String key) {
		if (!key.startsWith(prefix)) {
			throw new IllegalArgumentException("The parent key '" + key + "' is not in the subset.");
		}
		String modifiedKey = null;
		if (key.length() == prefix.length()) {
			modifiedKey = "";
		} else {
			final int i = prefix.length() + (delimiter != null ? delimiter.length() : 0);
			modifiedKey = key.substring(i);
		}
		return modifiedKey;
	}

	public Object clone() throws CloneNotSupportedException {
		return (SubsetConfigurationKeyFormat) super.clone();
	}
}