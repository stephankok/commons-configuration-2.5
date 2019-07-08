package org.apache.commons.configuration2;


import org.apache.commons.configuration2.PropertiesConfiguration.IOFactory;

public class IOFactoryProduct implements Cloneable {
	private IOFactory ioFactory;

	/**
	* Returns the  {@code  IOFactory}  to be used for creating readers and writers when loading or saving this configuration.
	* @return  the  {@code  IOFactory}
	* @since  1.7
	*/
	public IOFactory getIOFactory() {
		return (ioFactory != null) ? ioFactory : PropertiesConfiguration.DEFAULT_IO_FACTORY;
	}

	/**
	* Sets the  {@code  IOFactory}  to be used for creating readers and writers when loading or saving this configuration. Using this method a client can customize the reader and writer classes used by the load and save operations. Note that this method must be called before invoking one of the  {@code  load()}  and  {@code  save()}  methods. Especially, if you want to use a custom  {@code  IOFactory}  for changing the  {@code  PropertiesReader} , you cannot load the configuration data in the constructor.
	* @param ioFactory  the new  {@code  IOFactory}  (must not be <b>null</b>)
	* @throws IllegalArgumentException  if the  {@code  IOFactory}  is <b>null</b>
	* @since  1.7
	*/
	public void setIOFactory(final IOFactory ioFactory) {
		if (ioFactory == null) {
			throw new IllegalArgumentException("IOFactory must not be null!");
		}
		this.ioFactory = ioFactory;
	}

	public Object clone() {
		try {
			return (IOFactoryProduct) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}
}