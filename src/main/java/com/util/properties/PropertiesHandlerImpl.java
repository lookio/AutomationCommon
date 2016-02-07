package com.util.properties;

/**
 * @implements PropertyHandlerService
 * Properties Handler Impl.
 * Implements property service.
 * Manages all property files parsing
 * Strongly immutable object
 * Single tone object
 *
 *
 * @author asih
 */

import com.util.jaxb.GeneralUtils;
import com.util.log.ColoredLog;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * @author Asaf
 *
 */

public class PropertiesHandlerImpl implements PropertyHandlerService
{

	private final Properties props = new Properties();

	private static final Logger logger = Logger.getLogger(PropertiesHandlerImpl.class);

	private static final String encodingAction  = "UTF-8";

	private static final String fileName  = "environment/env.properties";

	/**
	 *  Static factory method.
	 *
	 *            xml file pathname.
	 */

	public static PropertiesHandlerImpl getInstance() {
		return new PropertiesHandlerImpl();
	}

	/**
	 * Parse the input property file.
	 *
	 * @param propFilePath
	 *            property file pathname.
	 *@return the parsed properties
	 */

	@SuppressWarnings("unused")
	@Override
	public synchronized final Properties parse(String propFilePath)
	{
		logger.debug("Inside property parse");
		File f = new File(propFilePath);
		try (InputStream is = new FileInputStream(f);)
		{
//			if(f == null)
//				parseToJar();
//			else
//			{
			props.load(is);
//			}
		} catch (Exception e) {
			GeneralUtils.handleError("Problem with the property file", e);
		}
		return props;
	}


	@SuppressWarnings({"TryFinallyCanBeTryWithResources", "ConstantConditions"})
	public Properties parseFromJar(String fileName){
		InputStream configStream = null;
		BufferedReader configReader = null;
		try{
			configStream = getClass().getResourceAsStream(fileName);
			configReader = new BufferedReader(new InputStreamReader(configStream, encodingAction));
			props.load(configReader);
			return props;
		}catch (IOException ioe){
			GeneralUtils.handleError("Error parsing property file", ioe);
		}
		finally{
			try{
				configStream.close();
				configReader.close();
			}catch (IOException ioe){
				GeneralUtils.handleError("Error closing resources", ioe);
			}
		}
		return props;
	}

	public InputStream parseStreamFromJar(String fileName) throws IOException{
		return getClass().getResourceAsStream(fileName);
	}

	/**
	 *  Get the property object.
	 *  @return  The property object
	 *
	 */

	@Override
	public final Properties getPropData()
	{
		return props;
	}

	/**
	 * Parse xml file denoted by this pathname.
	 *
	 *  Clean the proprty object.
	 *
	 */

	@Override
	public final void cleanProp()
	{
		try {
			props.clear();
		} catch (Throwable t) {
			GeneralUtils.handleError("Problem with clearing property object", t);
		}

		ColoredLog.printMessage(ColoredLog.LogLevel.INFO, "Clearing property object finished");
	}

}
