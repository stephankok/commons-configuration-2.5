/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.configuration2.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An internally used helper class for storing information about the managed
 * node structure. An instance of this class represents the current tree. It
 * stores the current root node and additional information which is not part
 * of the {@code ImmutableNode} class.
 *
 * @since 2.0
 */
class TreeData extends AbstractImmutableNodeHandler implements ReferenceNodeHandler
{
    private ImmutableNodeMappings immutableNodeMappings;

	/** The root node of the tree. */
    private final ImmutableNode root;

    /** The node tracker. */
    private final NodeTracker nodeTracker;

    /** The reference tracker. */
    private final ReferenceTracker referenceTracker;

    /**
     * Creates a new instance of {@code TreeData} and initializes it with all
     * data to be stored.
     *
     * @param root the root node of the current tree
     * @param parentMapping the mapping to parent nodes
     * @param replacements the map with the nodes that have been replaced
     * @param tracker the {@code NodeTracker}
     * @param refTracker the {@code ReferenceTracker}
     */
    public TreeData(final ImmutableNode root,
            final Map<ImmutableNode, ImmutableNode> parentMapping,
            final Map<ImmutableNode, ImmutableNode> replacements,
            final NodeTracker tracker, final ReferenceTracker refTracker)
    {
		this.root = root;
		this.immutableNodeMappings = new ImmutableNodeMappings(parentMapping, replacements);        
        nodeTracker = tracker;
        referenceTracker = refTracker;
    }

    @Override
    public ImmutableNode getRootNode()
    {
        return root;
    }

    /**
     * Returns the {@code NodeTracker}
     *
     * @return the {@code NodeTracker}
     */
    public NodeTracker getNodeTracker()
    {
        return nodeTracker;
    }

    /**
     * Returns the {@code ReferenceTracker}.
     *
     * @return the {@code ReferenceTracker}
     */
    public ReferenceTracker getReferenceTracker()
    {
        return referenceTracker;
    }

    /**
     * Returns the parent node of the specified node. Result is <b>null</b>
     * for the root node. If the passed in node cannot be resolved, an
     * exception is thrown.
     *
     * @param node the node in question
     * @return the parent node for this node
     * @throws IllegalArgumentException if the node cannot be resolved
     */
    @Override
    public ImmutableNode getParent(final ImmutableNode node)
    {
        if (node == getRootNode())
        {
            return null;
        }
        return immutableNodeMappings.getParent(node);
    }

    /**
     * Returns a copy of the mapping from nodes to their parents.
     *
     * @return the copy of the parent mapping
     */
    public Map<ImmutableNode, ImmutableNode> copyParentMapping()
    {
        return immutableNodeMappings.copyParentMapping();
    }

    /**
     * Returns a copy of the map storing the replaced nodes.
     *
     * @return the copy of the replacement mapping
     */
    public Map<ImmutableNode, ImmutableNode> copyReplacementMapping()
    {
        return immutableNodeMappings.copyReplacementMapping();
    }

    /**
     * Creates a new instance which uses the specified {@code NodeTracker}.
     * This method is called when there are updates of the state of tracked
     * nodes.
     *
     * @param newTracker the new {@code NodeTracker}
     * @return the updated instance
     */
    public TreeData updateNodeTracker(final NodeTracker newTracker)
    {
        return new TreeData(root, immutableNodeMappings.getParentMapping(), immutableNodeMappings.getReplacementMapping(),
                newTracker, referenceTracker);
    }

    /**
     * Creates a new instance which uses the specified {@code ReferenceTracker}.
     * All other information are unchanged. This method is called when there
     * updates for references.
     *
     * @param newTracker the new {@code ReferenceTracker}
     * @return the updated instance
     */
    public TreeData updateReferenceTracker(final ReferenceTracker newTracker)
    {
        return new TreeData(root, immutableNodeMappings.getParentMapping(), immutableNodeMappings.getReplacementMapping(),
                nodeTracker, newTracker);
    }

    /**
     * {@inheritDoc} This implementation delegates to the reference tracker.
     */
    @Override
    public Object getReference(final ImmutableNode node)
    {
        return getReferenceTracker().getReference(node);
    }

    /**
     * {@inheritDoc} This implementation delegates to the reference tracker.
     */
    @Override
    public List<Object> removedReferences()
    {
        return getReferenceTracker().getRemovedReferences();
    }
}
