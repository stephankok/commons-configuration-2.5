package org.apache.commons.configuration2.interpol;


import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;

public class RegisteredLookupObjects {
	private final Map<String, Lookup> prefixLookups;

	public RegisteredLookupObjects() {
		prefixLookups = new ConcurrentHashMap<>();
	}

	public Map<String, Lookup> getPrefixLookups() {
		return prefixLookups;
	}

	/**
	* Deregisters the  {@code  Lookup}  object for the specified prefix at this instance. It will be removed from this instance.
	* @param prefix  the variable prefix
	* @return  a flag whether for this prefix a lookup object had been registered
	*/
	public boolean deregisterLookup(final String prefix) {
		return prefixLookups.remove(prefix) != null;
	}

	/**
	* Returns a map with the currently registered  {@code  Lookup}  objects and their prefixes. This is a snapshot copy of the internally used map. So modifications of this map do not effect this instance.
	* @return  a copy of the map with the currently registered  {@code  Lookup} objects
	*/
	public Map<String, Lookup> getLookups() {
		return new HashMap<>(prefixLookups);
	}

	/**
	* Returns an unmodifiable set with the prefixes, for which  {@code  Lookup} objects are registered at this instance. This means that variables with these prefixes can be processed.
	* @return  a set with the registered variable prefixes
	*/
	public Set<String> prefixSet() {
		return Collections.unmodifiableSet(prefixLookups.keySet());
	}

	/**
	* Registers the given  {@code  Lookup}  object for the specified prefix at this instance. From now on this lookup object will be used for variables that have the specified prefix.
	* @param prefix  the variable prefix (must not be <b>null</b>)
	* @param lookup  the  {@code  Lookup}  object to be used for this prefix (must not be <b>null</b>)
	* @throws IllegalArgumentException  if either the prefix or the {@code  Lookup}  object is <b>null</b>
	*/
	public void registerLookup(final String prefix, final Lookup lookup) {
		if (prefix == null) {
			throw new IllegalArgumentException("Prefix for lookup object must not be null!");
		}
		if (lookup == null) {
			throw new IllegalArgumentException("Lookup object must not be null!");
		}
		prefixLookups.put(prefix, lookup);
	}

	/**
	* Registers all  {@code  Lookup}  objects in the given map with their prefixes at this  {@code  ConfigurationInterpolator} . Using this method multiple {@code  Lookup}  objects can be registered at once. If the passed in map is <b>null</b>, this method does not have any effect.
	* @param lookups  the map with lookups to register (may be <b>null</b>)
	* @throws IllegalArgumentException  if the map contains <b>entries</b>
	*/
	public void registerLookups(final Map<String, ? extends Lookup> lookups) {
		if (lookups != null) {
			prefixLookups.putAll(lookups);
		}
	}

	/**
	* Creates a new instance based on the properties in the given specification object.
	* @param spec  the  {@code  InterpolatorSpecification}
	* @return  the newly created instance
	*/
	public static ConfigurationInterpolator createInterpolator(final InterpolatorSpecification spec) {
		final ConfigurationInterpolator ci = new ConfigurationInterpolator();
		ci.addDefaultLookups(spec.getDefaultLookups());
		ci.registerLookups(spec.getPrefixLookups());
		ci.setParentInterpolator(spec.getParentInterpolator());
		return ci;
	}
}