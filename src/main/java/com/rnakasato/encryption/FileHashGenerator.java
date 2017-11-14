package com.rnakasato.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHashGenerator {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\Rafael\\Downloads\\Pasted image at 2017_04_04 11_05 AM.png";
		try {
			File tempFile = new File(filePath);

			byte[] arquivoLote = Files.readAllBytes(Paths.get(tempFile.getPath()));

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(arquivoLote, 0, arquivoLote.length);

			byte[] hash = messageDigest.digest();

			StringBuilder sb = new StringBuilder();

			for (byte b : hash) {
				sb.append(String.format("%02X", b));
			}

			String hashArquivo = sb.toString();
			System.out.println(hashArquivo);

		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}
}
