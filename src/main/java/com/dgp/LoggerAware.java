package com.dgp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Convenience interface that basically does the same as LoggerSupport
 * while removing the need for inheritance.
 * 
 * (Note that the default getLogger() calls the factory with every invocation)
 * @author jeremy.grand
 *
 */
public interface LoggerAware {
    
    default Logger getLogger(){
        return LoggerFactory.getLogger(getClass());
    }
    
    default String getName(){
        return getClass().getSimpleName();
    }
    
    default void logDeb(String in_msg, Object... params) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(in_msg, params);
        }
    }

    default void logInf(String in_msg, Object... params) {
        if (getLogger().isInfoEnabled()) {
            getLogger().info(in_msg, params);
        }
    }
    
    default void logWarn(String in_format, Object... in_args){
        if(getLogger().isWarnEnabled()){
            getLogger().warn(in_format, in_args);
        }
    }
    
    default void logErr(String in_format, Object... in_args){
        if(getLogger().isErrorEnabled()){
            getLogger().error(in_format, in_args);
        }
    }

    default void logError(Throwable in_throwable) {
        getLogger().error("Unexpected Error", in_throwable);
    }

}
