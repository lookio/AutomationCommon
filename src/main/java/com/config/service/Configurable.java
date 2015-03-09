package com.config.service;

import java.util.List;

/**
 * Created by asih on 05/02/2015.
 */

@SuppressWarnings({"TypeParameterHidesVisibleType"})
public interface Configurable<T> {

     abstract <E> void create(List<E> ConfigItem);

     abstract <E> void modify(List<E> ConfigItem);

     abstract <E> void delete(List<E> ConfigItem);


}
