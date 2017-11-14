package com.rnakasato.encryption;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.DecoderException;
import org.bouncycastle.util.encoders.Hex;

public class ProtectedConfigFile {

	
	public static void main(String[] args) throws Exception {
		String password = "TEST";
		if (password == null) {
			throw new IllegalArgumentException("Run with -Dpassword=<password>");
		}

		// The salt (probably) can be stored along with the encrypted data
		byte[] salt = new String("DESCRIPTOGRAFA ISSO ").getBytes();

		// Decreasing this speeds down startup time and can be useful during
		// testing, but it also makes it easier for brute force attackers
		int iterationCount = 40000;
		// Other values give me java.security.InvalidKeyException: Illegal key
		// size or default parameters
		int keyLength = 128;
		SecretKeySpec key = createSecretKey("HAHA".toCharArray(), salt, iterationCount, keyLength);

		String originalPassword = "TEST";
		System.out.println("Original password: " + originalPassword);
		String encryptedPassword = encrypt(originalPassword, key);
		System.out.println("Encrypted password: " + encryptedPassword);
//		String decryptedPassword = decrypt(encryptedPassword, key);
//		System.out.println("Decrypted password: " + decryptedPassword);
		
		File file = new File("C:\\Meus Documentos\\text.chave");
//		file.createNewFile();
//		saveKey(key, file);
		SecretKeySpec skey = (SecretKeySpec) loadKey(file);
		System.out.println(new String(skey.getEncoded(),"UTF-8"));

//		String originalPassword = "TESTA";
//		System.out.println("Original password: " + originalPassword);
//		String encryptedPassword = encrypt(originalPassword, skey);
//		System.out.println("Encrypted password: " + encryptedPassword);
		String decryptedPassword = decrypt(encryptedPassword, skey);
		System.out.println("Decrypted password: " + decryptedPassword);

	}

	private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
		SecretKey keyTmp = keyFactory.generateSecret(keySpec);
		return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	}

	private static String encrypt(String property, SecretKeySpec key)
			throws GeneralSecurityException, UnsupportedEncodingException {
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key);
		AlgorithmParameters parameters = pbeCipher.getParameters();
		IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
		byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
		byte[] iv = ivParameterSpec.getIV();
		return base64Encode(iv) + ":" + base64Encode(cryptoText);
	}

	private static String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	private static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
		String iv = string.split(":")[0];
		String property = string.split(":")[1];
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
		return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	}

	private static byte[] base64Decode(String property) throws IOException {
		return Base64.getDecoder().decode(property);
	}

	public static SecretKey generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256); // 128 default; 192 and 256 also possible
		return keyGenerator.generateKey();
	}

	public static void saveKey(SecretKey key, File file) throws IOException {
		String encodedHex = new String(Hex.encode(key.getEncoded()),"UTF-8");
		writeStringToFile(file, encodedHex);
	}

	public static SecretKey loadKey(File file) throws IOException {
		String data = new String(Files.readAllBytes(file.toPath()),"UTF-8");
		byte[] encoded;
		try {
			encoded = Hex.decode(data);
		} catch (DecoderException e) {
			e.printStackTrace();
			return null;
		}
		return new SecretKeySpec(encoded, "AES");
	}

	public static void writeStringToFile(File file, String text) throws IOException{
		FileWriter writer = new FileWriter(file);
		writer.write(text);
		writer.close();
	}

	
	public static byte[] decodeHex(String hex){
		byte[] bytes = Hex.decode(hex);
		return bytes;
	}
}
