/**
 *
 */
package org.sevendroids.scm.analyse.util;

import java.util.Properties;

/**
 * This class is an extentsion of the java.util.Properties class. It returns
 * NULL instead of empty strings
 * 
 * @author 7droids
 *
 */
public class NoEmptyProperties extends Properties {

	private static final long serialVersionUID = 8502212333016112260L;

	/**
	 *
	 */
	public NoEmptyProperties() {
		super();
	}

	/**
	 * @param defaults
	 */
	public NoEmptyProperties(Properties defaults) {
		super(defaults);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Properties#getProperty(java.lang.String)
	 */
	@Override
	public String getProperty(String key) {
		String prop = super.getProperty(key);
		if (prop != null && prop.trim().isEmpty())
			return null;
		return prop;
	}

}
