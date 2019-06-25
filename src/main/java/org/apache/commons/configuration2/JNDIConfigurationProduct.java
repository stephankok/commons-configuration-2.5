package org.apache.commons.configuration2;


import javax.naming.Context;
import java.util.Set;
import java.util.HashSet;

public class JNDIConfigurationProduct implements Cloneable {
	private Context context;
	private final Set<String> clearedProperties = new HashSet<>();

	public Context getContext() {
		return context;
	}

	public void setContext2(Context context) {
		this.context = context;
	}

	public Set<String> getClearedProperties() {
		return clearedProperties;
	}

	/**
	* Set the initial context of the configuration.
	* @param context  the context
	*/
	public void setContext(final Context context) {
		clearedProperties.clear();
		this.context = context;
	}

	public Object clone() throws CloneNotSupportedException {
		return (JNDIConfigurationProduct) super.clone();
	}
}