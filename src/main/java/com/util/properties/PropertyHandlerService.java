package com.util.properties;

/**
 * Property Handler Service.
 * 
 * 
 * @author asih
 */

import java.util.Properties;

// move selenium activities and property service to util layer

public interface PropertyHandlerService {


     Properties parse(String propFilePath);

     Properties getPropData();

     void cleanProp();

}
