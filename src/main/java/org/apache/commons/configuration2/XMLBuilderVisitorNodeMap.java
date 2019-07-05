package org.apache.commons.configuration2;


import java.util.Map;
import org.w3c.dom.Node;
import org.apache.commons.configuration2.tree.ReferenceNodeHandler;
import org.w3c.dom.Element;

public class XMLBuilderVisitorNodeMap {
	private final Map<Node, Node> elementMapping;

	public XMLBuilderVisitorNodeMap(XMLDocumentHelper docHelper) {
		elementMapping = docHelper.getElementMapping();
	}

	public Map<Node, Node> getElementMapping() {
		return elementMapping;
	}

	/**
	* Updates the current XML document regarding removed nodes. The elements associated with removed nodes are removed from the document.
	* @param refHandler  the  {@code  ReferenceNodeHandler}
	*/
	public void handleRemovedNodes(final ReferenceNodeHandler refHandler) {
		for (final Object ref : refHandler.removedReferences()) {
			if (ref instanceof Node) {
				final Node removedElem = (Node) ref;
				removeReference((Element) elementMapping.get(removedElem));
			}
		}
	}

	/**
	* Updates the associated XML elements when a node is removed.
	* @param element  the element to be removed
	*/
	public void removeReference(final Element element) {
		final org.w3c.dom.Node parentElem = element.getParentNode();
		if (parentElem != null) {
			parentElem.removeChild(element);
		}
	}
}