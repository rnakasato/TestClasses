package com.rnakasato.xml;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Utilitário para realização de Unmarshall de XML. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 25 de mai de 2017 - @author Rafael - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
public class XMLUnmarshallerUtils {

	/**
	 * 
	 * Unmarshall the XML String to the defined class
	 * 
	 * @param clazz
	 * @param xmlData
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshall(Class<T> clazz, String xmlData) throws JAXBException {
		T xmlClass = null;
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jc.createUnmarshaller();

		StringReader stringReader = new StringReader(xmlData);
		xmlClass = (T) unmarshaller.unmarshal(stringReader);

		return xmlClass;

	}
}
