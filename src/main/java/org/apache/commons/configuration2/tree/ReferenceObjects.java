package org.apache.commons.configuration2.tree;


import java.util.Map;
import java.util.HashMap;

public class ReferenceObjects {
	private Map<ImmutableNode, Object> newReferences;

	public Map<ImmutableNode, Object> getNewReferences() {
		return newReferences;
	}

	/**
	* Returns the map with new reference objects. It is created if necessary.
	* @return  the map with reference objects
	*/
	private Map<ImmutableNode, Object> fetchReferenceMap() {
		if (newReferences == null) {
			newReferences = new HashMap<>();
		}
		return newReferences;
	}

	/**
	* Adds a map with new reference objects. The entries in this map are passed to the  {@code  ReferenceTracker}  during execution of this transaction.
	* @param refs  the map with new reference objects
	*/
	public void addNewReferences(final Map<ImmutableNode, ?> refs) {
		fetchReferenceMap().putAll(refs);
	}

	/**
	* Adds a new reference object for the given node.
	* @param node  the affected node
	* @param ref  the reference object for this node
	*/
	public void addNewReference(final ImmutableNode node, final Object ref) {
		fetchReferenceMap().put(node, ref);
	}
}