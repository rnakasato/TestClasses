package com.rnakasato.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.rnakasato.xml.UserXMLData.Address;

public class XMLTest {

	public static void main(String[] args) throws JAXBException, IOException {
		UserXMLData user = new UserXMLData();
		user.setAge(18);
		user.setFirstName("Rafael");
		user.setLastName("Nakasato");
		user.setUsername("Test");

		UserXMLData.Address address = new Address();
		address.setCep("00000-000");
		address.setNeighborhood("Pindorama");
		address.setNumber("S/N");
		address.setStreet("somewhere");
		user.setAddress(address);

		File file = new File("User.xml");
		XMLMarshallerUtils.marshall(UserXMLData.class, user, file);

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = null;

		StringBuilder data = new StringBuilder();
		while ((line = br.readLine()) != null) {
			data.append(line);
			System.out.println(line);
		}
		br.close();

		UserXMLData userData = XMLUnmarshallerUtils.unmarshall(UserXMLData.class, data.toString());
		System.out.println(userData.getFirstName());

	}

}
