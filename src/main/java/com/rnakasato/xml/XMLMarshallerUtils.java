package com.rnakasato.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XMLMarshallerUtils {
	/**
	 * 
	 * Marshall the XML object to a file
	 * 
	 * @param clazz
	 * @param xmlData
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> File marshall(Class<T> clazz, T dataToMarshall, File file) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		marshaller.marshal(dataToMarshall, file);

		return file;

	}
}
