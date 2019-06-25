package org.apache.commons.configuration2.tree;


import java.util.Map;
import org.apache.commons.configuration2.tree.ImmutableNode.Builder;
import java.util.HashMap;

public class ImmutableNodeAttribute {
	private Map<String, Object> attributes;

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/**
	* Adds an attribute to this builder. The passed in attribute key and value are stored in an internal map. If there is already an attribute with this name, it is overridden.
	* @param name  the attribute name
	* @param value  the attribute value
	* @return  a reference to this object for method chaining
	*/
	public void addAttribute(final String name, final Object value) {
		ensureAttributesExist();
		attributes.put(name, value);		
	}

	/**
	* Ensures that the map for the attributes exists. It is created on demand.
	*/
	public void ensureAttributesExist() {
		if (attributes == null) {
			attributes = new HashMap<>();
		}
	}

	/**
	* Adds all attributes of the given map to this builder. This method works like  {@link #addAttribute(String,Object)} , but it allows setting multiple attributes at once.
	* @param attrs  the map with attributes to be added (may be <b>null</b>
	* @return  a reference to this object for method chaining
	*/
	public void addAttributes(final Map<String, ?> attrs) {
		if (attrs != null) {
			ensureAttributesExist();
			attributes.putAll(attrs);
		}	
	}
}