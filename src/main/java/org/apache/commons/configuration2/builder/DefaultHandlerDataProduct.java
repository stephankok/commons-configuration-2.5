package org.apache.commons.configuration2.builder;


public class DefaultHandlerDataProduct {
	private final DefaultParametersHandler<?> handler;
	private final Class<?> startClass;

	public DefaultHandlerDataProduct(DefaultParametersHandler<?> h, Class<?> startCls) {
		handler = h;
		startClass = startCls;
	}

	public DefaultParametersHandler<?> getHandler() {
		return handler;
	}

	public Class<?> getStartClass() {
		return startClass;
	}

	/**
	* Tests whether this instance refers to the specified occurrence of a {@code  DefaultParametersHandler} .
	* @param h  the handler to be checked
	* @param startCls  the start class
	* @return  <b>true</b> if this instance refers to this occurrence, <b>false</b> otherwise
	*/
	public boolean isOccurrence(final DefaultParametersHandler<?> h, final Class<?> startCls) {
		return h == handler && (startCls == null || startCls.equals(startClass));
	}
}