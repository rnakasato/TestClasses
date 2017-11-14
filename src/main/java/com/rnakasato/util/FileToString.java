package com.rnakasato.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileToString {
	private static final String FILE = "file";

	public static void main(String[] args) throws IOException {
		File file = new File(FILE);

		String content = fileToStringBreakLine(file);
		System.out.println(content);

	}

	public static String fileToStringBreakLine(File file) throws IOException {
		String fileContent = null;
		BufferedReader fileReader = null;

		fileReader = new BufferedReader(new FileReader(file));
		StringBuilder contentBuilder = new StringBuilder();

		String currentLine = fileReader.readLine();
		while (currentLine != null) {
			contentBuilder.append(currentLine);
			contentBuilder.append("\n");
			currentLine = fileReader.readLine();
		}

		fileReader.close();
		fileContent = contentBuilder.toString();

		return fileContent;
	}

}
