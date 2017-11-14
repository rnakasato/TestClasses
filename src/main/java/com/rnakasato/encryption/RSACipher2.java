package com.rnakasato.encryption;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSACipher2 {

	private PublicKey publicKey;
	private PrivateKey privateKey;

	public static final String texto = "Teste criptografia";

	private static char[] Base64Map = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '+', '/', '=' };

	public RSACipher2(PublicKey publicKey, PrivateKey privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public static void main(String[] args)
			throws NoSuchProviderException, SignatureException, CMSException, NoSuchAlgorithmException,
			InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, IOException {

		KeyPair kp = genKeyPair();
		RSACipher2 cipher = new RSACipher2(kp.getPublic(), kp.getPrivate());

		// Assina o texto com a chave privada
		byte[] sigBytes = cipher.signData(RSACipher2.texto);

		boolean valid = cipher.verify(texto, sigBytes);
		System.out.println(valid);

		// cipher.getSignedData(Base64.getEncoder().encode(texto.trim().getBytes()),
		// sigBytes);

	}

	public void doSomething() {

	}

	public static KeyPair genKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider());
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair keyPair = generator.generateKeyPair();

		return keyPair;
	}

	public byte[] signData(String texto)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		byte[] signedBytes = null;
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(texto.getBytes());
		signedBytes = signature.sign();

		return signedBytes;
	}

	public boolean verify(String texto, byte[] sigBytes)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		boolean isValid = false;

		byte[] sigDataBytes = texto.getBytes();
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(sigDataBytes);

		isValid = signature.verify(sigBytes);

		return isValid;
	}

	public byte[] unsignData(byte[] signedDataBytes, byte[] signedBytes) throws CMSException {
		byte[] decodedData = null;

		return decodedData;
	}

	public void getSignedData(byte[] data, byte[] signatureBytes) throws IOException, CMSException {

	}

	private static final boolean isBase64Encoded(byte[] data) {
		Arrays.sort(Base64Map);
		for (int i = 0; i < data.length; i++) {
			// System.out.println("data[" + i + "] " + (char)data[i]) ;
			if (Arrays.binarySearch(Base64Map, (char) data[i]) < 0 && !Character.isWhitespace((char) data[i]))
				return false;
		}
		return true;
	}

	public byte[] encrypt(String string) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(string.getBytes());
	}

	public byte[] decrypt(byte[] cripted) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
			IOException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] decriptedText = cipher.doFinal(cripted);
		return decriptedText;
	}

	// Getters e Setters
	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
}