package org.apache.commons.configuration2.tree;


import java.util.Map;
import java.util.HashMap;

public class ImmutableNodeMappings {
	 /** A map that associates the parent node to each node. */
    private final Map<ImmutableNode, ImmutableNode> parentMapping;

    /**
     * Stores information about nodes which have been replaced by
     * manipulations of the structure. This map is used to avoid that the
     * parent mapping has to be updated after each change.
     */
    private final Map<ImmutableNode, ImmutableNode> replacementMapping;

    /** An inverse replacement mapping. */
    private final Map<ImmutableNode, ImmutableNode> inverseReplacementMapping;

	public ImmutableNodeMappings(Map<ImmutableNode, ImmutableNode> parentMapping,
			Map<ImmutableNode, ImmutableNode> replacements) {
		this.parentMapping = parentMapping;
		this.replacementMapping = replacements;
		inverseReplacementMapping = createInverseMapping(replacements);
	}

	public Map<ImmutableNode, ImmutableNode> getParentMapping() {
		return parentMapping;
	}

	public Map<ImmutableNode, ImmutableNode> getReplacementMapping() {
		return replacementMapping;
	}

	/**
	* Returns a copy of the mapping from nodes to their parents.
	* @return  the copy of the parent mapping
	*/
	public Map<ImmutableNode, ImmutableNode> copyParentMapping() {
		return new HashMap<>(parentMapping);
	}

	/**
	* Returns a copy of the map storing the replaced nodes.
	* @return  the copy of the replacement mapping
	*/
	public Map<ImmutableNode, ImmutableNode> copyReplacementMapping() {
		return new HashMap<>(replacementMapping);
	}
	
	public ImmutableNode getParent(final ImmutableNode node) {
		final ImmutableNode org = handleReplacements(node, inverseReplacementMapping);

        final ImmutableNode parent = getParentMapping().get(org);
        if (parent == null)
        {
            throw new IllegalArgumentException("Cannot determine parent! "
                    + node + " is not part of this model.");
        }
        return handleReplacements(parent, getReplacementMapping());
	}
	
	/**
     * Checks whether the passed in node is subject of a replacement by
     * another one. If so, the other node is returned. This is done until a
     * node is found which had not been replaced. Updating the parent
     * mapping may be expensive for large node structures. Therefore, it
     * initially remains constant, and a map with replacements is used. When
     * querying a parent node, the replacement map has to be consulted
     * whether the parent node is still valid.
     *
     * @param replace the replacement node
     * @param mapping the replacement mapping
     * @return the corresponding node according to the mapping
     */
    private static ImmutableNode handleReplacements(final ImmutableNode replace,
            final Map<ImmutableNode, ImmutableNode> mapping)
    {
        ImmutableNode node = replace;
        ImmutableNode org;
        do
        {
            org = mapping.get(node);
            if (org != null)
            {
                node = org;
            }
        } while (org != null);
        return node;
    }
	
	/**
     * Creates the inverse replacement mapping.
     *
     * @param replacements the original replacement mapping
     * @return the inverse replacement mapping
     */
    private Map<ImmutableNode, ImmutableNode> createInverseMapping(
            final Map<ImmutableNode, ImmutableNode> replacements)
    {
        final Map<ImmutableNode, ImmutableNode> inverseMapping =
                new HashMap<>();
        for (final Map.Entry<ImmutableNode, ImmutableNode> e : replacements
                .entrySet())
        {
            inverseMapping.put(e.getValue(), e.getKey());
        }
        return inverseMapping;
    }
}