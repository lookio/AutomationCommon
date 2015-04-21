package com.config.service;

/**
 * Created by asih on 05/03/2015.
 */
public interface ISite {

     <T> T getSite(Class<T> site);

     abstract boolean isAllreadyContains();

}
