package com.util.properties;

/**
 * @implements PropertyHandlerService
 * Properties Handler Impl.
 * Implements property service.
 * Manages all property files parsing
 * Strongly immmutable object
 * Single tone object
 *
 *
 * @author asih
 */

import com.util.genutil.GeneralUtils;
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

	private static final String fileName  = "env.properties";

	/**
	 *  Static factory method.
	 *
	 *            xml file pathname.
	 * @throws Exception
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
			if(f == null)
				parseToJar();
			else
			{
				props.load(is);
			}
		} catch (Exception e) {
			GeneralUtils.handleError("Problem with the property file", e);
		}
		return props;
	}


	@SuppressWarnings({"TryFinallyCanBeTryWithResources", "ConstantConditions"})
	private void parseToJar() throws IOException{
		InputStream configStream = null;
		BufferedReader configReader = null;
		try{
			configStream = getClass().getResourceAsStream(fileName);
			configReader = new BufferedReader(new InputStreamReader(configStream, encodingAction));
			props.load(configReader);
		}
		finally{
			configStream.close();
			configReader.close();
		}
		logger.debug("Parsed to jar finished successfully");
	}

	/**
	 *  Get the proprty object.
	 *  @return  Tthe proprty object
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

		logger.info("Clearing property object finished");
	}

}
