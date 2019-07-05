package org.apache.commons.configuration2.tree;


import java.util.List;
import java.util.Collections;
import org.apache.commons.configuration2.tree.ImmutableNode.Builder;
import java.util.LinkedList;
import java.util.Collection;
import java.util.ArrayList;

public class ImmutatbleNodeChildren {
	private final List<ImmutableNode> directChildren;
	private List<ImmutableNode> children;

	public ImmutatbleNodeChildren(List<ImmutableNode> dirChildren) {
		directChildren = dirChildren;
	}

	public void setChildren(List<ImmutableNode> children) {
		this.children = children;
	}

	/**
	* Creates a list with the children of the newly created node. The list returned here is always immutable. It depends on the way this builder was populated.
	* @return  the list with the children of the new node
	*/
	public List<ImmutableNode> createChildren() {
		if (directChildren != null) {
			return directChildren;
		}
		if (children != null) {
			return Collections.unmodifiableList(children);
		}
		return Collections.emptyList();
	}

	/**
	* Adds a child node to this builder. The passed in node becomes a child of the newly created node. If it is <b>null</b>, it is ignored.
	* @param c  the child node (must not be <b>null</b>)
	* @return  a reference to this object for method chaining
	*/
	public void addChild(final ImmutableNode c) {
		if (c != null) {
			ensureChildrenExist();
			children.add(c);
		}		
	}

	/**
	* Ensures that the collection for the child nodes exists. It is created on demand.
	*/
	public void ensureChildrenExist() {
		if (children == null) {
			children = new LinkedList<>();
		}
	}

	/**
	* Adds multiple child nodes to this builder. This method works like {@link #addChild(ImmutableNode)} , but it allows setting a number of child nodes at once.
	* @param children  a collection with the child nodes to be added
	* @return  a reference to this object for method chaining
	*/
	public void addChildren(final Collection<? extends ImmutableNode> children) {
		if (children != null) {
			ensureChildrenExist();
			this.children.addAll(Builder.filterNull(children));
		}		
	}

	/**
	* Creates the collection for child nodes based on the expected number of children.
	* @param childCount  the expected number of new children
	*/
	public void initChildrenCollection(final int childCount) {
		if (childCount > 0) {
			children = new ArrayList<>(childCount);
		}
	}
}