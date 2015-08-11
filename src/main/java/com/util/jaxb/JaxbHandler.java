package com.util.jaxb; /**
 * Jaxb Handler.
 * Provides unmarshaling of xml data to JAXB objects
 *
 *
 *
 * @author asih
 */

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//import org.apache.log4j.Logger;
//import com.feex.crosscutting.genUtil.GeneralUtils;


public class JaxbHandler {

	private static final Logger logger = Logger.getLogger(JaxbHandler.class);



	public static <T> T unmarshal(String dataFileName, Class<T> clazz) {
		logger.info("Parsing Jaxb objects");
		File file = null;
		try {
			file = new File(dataFileName);
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			logger.info("Parsing Jaxb objects completed");
			return u.unmarshal(new StreamSource(file), clazz).getValue();
		} catch (JAXBException ex) {
			logger.error("in parsing locators data", ex);
		}
		return null;
	}

	public static <T> void marshal(String dataFileName, T data, Class<T> clazz) {
		try{
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(data, new FileOutputStream(dataFileName));
		} catch (JAXBException | FileNotFoundException e) {
			logger.error("in parsing locators data", e);
		}
	}




}
