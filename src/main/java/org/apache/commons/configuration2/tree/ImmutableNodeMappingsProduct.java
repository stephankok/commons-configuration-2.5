package org.apache.commons.configuration2.tree;


import java.util.Map;
import java.util.HashMap;

public class ImmutableNodeMappingsProduct {
	private final Map<ImmutableNode, ImmutableNode> parentMapping;
	private final Map<ImmutableNode, ImmutableNode> replacementMapping;

	public ImmutableNodeMappingsProduct(Map<ImmutableNode, ImmutableNode> parentMapping,
			Map<ImmutableNode, ImmutableNode> replacements) {
		this.parentMapping = parentMapping;
		this.replacementMapping = replacements;
	}

	public Map<ImmutableNode, ImmutableNode> getParentMapping() {
		return parentMapping;
	}

	public Map<ImmutableNode, ImmutableNode> getReplacementMapping() {
		return replacementMapping;
	}

	/**
	* Returns a copy of the mapping from nodes to their parents.
	* @return   the copy of the parent mapping
	*/
	public Map<ImmutableNode, ImmutableNode> copyParentMapping() {
		return new HashMap<>(parentMapping);
	}

	/**
	* Returns a copy of the map storing the replaced nodes.
	* @return   the copy of the replacement mapping
	*/
	public Map<ImmutableNode, ImmutableNode> copyReplacementMapping() {
		return new HashMap<>(replacementMapping);
	}
}