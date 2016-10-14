package com.dgp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Basic implementation of {@link LoggerAware} but setting the logger
 * & name as attributes rather than calling the factory with every invocation.</p>
 * @author pap, jeremy.grand
 *
 */
public abstract class LoggerSupport implements LoggerAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String name = getClass().getSimpleName();
	
	public String getName() {
		return name;
	}

	public void setName(String in_name) {
		name = in_name;
	}

	public Logger getLogger() {
		return logger;
	}
}
