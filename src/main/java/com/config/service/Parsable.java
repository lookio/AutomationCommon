package com.config.service;

import java.util.List;

/**
 * Created by asih on 05/03/2015.
 */
public interface Parsable {

     <T, E> List<E> parseObjects(Class<? extends T> entityClass);

}
