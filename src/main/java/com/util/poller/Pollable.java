package com.util.poller;

/**
 * Created by asih on 16/08/2015.
 */
public interface Pollable {

    boolean until () throws Exception;

}
