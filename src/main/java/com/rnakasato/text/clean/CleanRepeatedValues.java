package com.rnakasato.text.clean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CleanRepeatedValues {
	private static final String PATH = "ARQUIVO";

	public static void main(String[] args) throws IOException {
		File file = new File(PATH);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		Set<String> codeSet = new HashSet<>();

		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			codeSet.add(line);
		}

		bufferedReader.close();
		for (String string : codeSet) {
			System.out.println(string);
		}

	}
}
