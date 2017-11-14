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
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSACipher {

	private PublicKey publicKey;
	private PrivateKey privateKey;

	public static final String texto = "Teste criptografia";

	public static void main(String[] args)
	 throws NoSuchProviderException, SignatureException, CMSException,
	 NoSuchAlgorithmException,
	 InvalidKeyException, NoSuchPaddingException, BadPaddingException,
	 IllegalBlockSizeException, IOException {
	
	 // Gera chaves pública e privada
	 KeyPair kp = genKeyPair();
	 RSACipher cipher = new RSACipher(kp.getPublic(), kp.getPrivate());
	
	 // Criptografa o texto
	 byte[] cripted = cipher.encrypt(texto);
	
	 // Exibe o texto criptografado utilizando encode Base64
	 String printcript = new String(Base64.getEncoder().encode(cripted));
	 System.out.println("Criptografado: ");
	 System.out.println(printcript);
	 System.out.println();
	
	 // Decriptografa o texto
	 byte[] decripted = cipher.decrypt(cripted);
	
	 // Exibe o texto descriptografado
	 String printDecrypt = new String(decripted);
	 System.out.println("Decriptografado: ");
	 System.out.println(printDecrypt);
	 System.out.println();
	
	 // O bloco anterior realizava a criptografia e descriptografia, porém o
	 // que queremos
	 // é recuperar um conteúdo assinado pela privateKey, a forma que é
	 // realizada a criptografia é diferente
	
	 // Assina o texto com a chave privada
	 byte[] sigBytes = cipher.signData(RSACipher.texto);
	
	 // Valida se o texto assinado foi gerado a partir do texto passado como
	 // parâmetro
	 // Esse retorno será true, ou seja o texto é válido
	 boolean valid = cipher.verify(texto, sigBytes);
	 System.out.println(valid);
	
	 // O problema é que durante os testes do algoritmo para conseguir o
	 // texto que foi assinado
	 // eu fiz o mesmo método para verificar se a assinatura é válida
	 // passando os bytes dos cnpjs concatenados e a chave pública como
	 // parâmetro, o valor equivalente ao sigBytes
	
	 // Se realizar o encode dos bytes assinados para base64, o tamanho da
	 // string gerada é a mesma
	 // da assinatura fornecida pelo Leandro
	 String txtSigBytes = new String(Base64.getEncoder().encode(sigBytes));
	 System.out.println(new String(txtSigBytes));
	
	 // Durante os meus testes eu recuperei os bytes da assinatura utilizando
	 // assinatura.getBytes();
	 // eu li em um post dizendo que os bytes quando transformados em String
	 // podem ser alterados
	 // por não terem uma representação textual e estarem com encode base64,
	 // portanto fiz o seguinte teste:
	
	 // utilizando o texto transformado em String eu recuperei os bytes
	 // fazendo o .getBytes() e em seguida realizando o decode de Base64
	 // resumindo, fiz o processo contrário, o que deveria me retornar os
	 // mesmos bytes
	 // em seguida comparei byte a byte dos bytes gerados na assinatura e os
	 // bytes recuperados
	 // do texto, a comparação foi feita entre bytes de mesma posição em
	 // ambos os arrays
	 byte[] newSigBytes = Base64.getDecoder().decode(txtSigBytes.getBytes());
	 boolean sameBytes = cipher.sameBytes(sigBytes, newSigBytes);
	 System.out.println(sameBytes);
	 // Como pode ser observado, foi retornado true, o que acredito que me
	 // garante que os bytes não foram alterados pelas conversões
	 System.out.println(cipher.verify(texto, newSigBytes));
	
	 // A partir desse momento surgiu um problema, utilizando os dados
	 // fornecidos pelo Leandro, recuperei os bytes da assinatura e fiz o
	 // mesmo processo de validação de assinatura, porém não retornou true
	 // fiz algumas suposições nesse momento:
	 // - Existe a posibilidade de o texto que foi assinado não ser apenas os
	 //dois cnpjs concatenados
	 // - Existe a possiblidade de a assinatura ter sido realizada com a chave
	 //publica
	
	 // A outra possibilidade é que o algoritmo de assinatura seja diferente
	 //da que utilizei no teste,
	 // porém eu utilizei o algoritmo que identifiquei na chave pública que
	 //dizia que o algoritmo de assinatura
	 // era o SHA256withRSA
	
	
	 // Ainda me falta o passo de conseguir retornar o texto que foi assinado,
	 //	 mas preciso
	 // ter certeza se as suposições que fiz são corretas
	
	
	
	 }

	public boolean sameBytes(byte[] firstArray, byte[] secondArray) {
		boolean same = false;
		if (firstArray.length == secondArray.length) {
			for (int i = 0; i < firstArray.length; i++) {
				if (firstArray[i] == secondArray[i]) {
					same = true;
				} else {
					same = false;
					break;
				}
			}

		} else {
			same = false;
		}

		return same;
	}

	public RSACipher(PublicKey publicKey, PrivateKey privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public static KeyPair genKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider());
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
		generator.initialize(2048);
		KeyPair keyPair = generator.generateKeyPair();
		
		return keyPair;
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

	public byte[] signData(String texto)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		byte[] signedBytes = null;
		Signature signature = Signature.getInstance("SHA256withRSA", "BC");
		signature.initSign(privateKey);
		signature.update(texto.getBytes());
		signedBytes = signature.sign();

		return signedBytes;
	}

	public boolean verify(String texto, byte[] sigBytes)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		boolean isValid = false;

		byte[] sigDataBytes = texto.getBytes();
		Signature signature = Signature.getInstance("SHA256withRSA", "BC");
		signature.initVerify(publicKey);
		signature.update(sigDataBytes);

		isValid = signature.verify(sigBytes);

		return isValid;
	}

	public byte[] unsignData(byte[] signedDataBytes, byte[] signedBytes) throws CMSException {
		byte[] decodedData = null;

		return decodedData;
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