package com.rnakasato.encryption;

public class Decrypt {
	// static final String STORENAME =
	// "C:\\Users\\Rafael\\Downloads\\ChavePublica.cer";
	// static final String STOREPASS = "password";
	// static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
	// static final String END_CERT = "-----END CERTIFICATE-----";
	//
	// public static final DERObjectIdentifier CNPJ = new
	// DERObjectIdentifier("2.16.76.1.3.3");
	//
	// public static void main(String[] args) throws Exception {
	// // Chave publica 
	//// String chavePublica =""
	// 
	// 
	//
	// String assinatura =
	// "";
	// String textoPlano = "0632716000012612235784000388";
	//
	// X509Certificate certificate = (X509Certificate)
	// getCertificate(chavePublica);
	//
	// PublicKey key = certificate.getPublicKey();
	//
	// System.out.println("Algoritmo de assinatura do certificado: " +
	// certificate.getSigAlgName());
	//
	// System.out.println("Validação de assinatura: " + verify(textoPlano,
	// assinatura, key));
	// System.out.println();
	//
	//
	// loadCertificate(new File(STORENAME));
	//
	// }
	//
	// public static boolean verify(String plainText, String signature,
	// PublicKey publicKey) throws Exception {
	// Security.addProvider(new BouncyCastleProvider());
	// Signature publicSignature = Signature.getInstance("SHA256withRSA", "BC");
	//
	// publicSignature.initVerify(publicKey);
	// publicSignature.update(plainText.getBytes());
	//
	// byte[] signatureBytes = Base64.getDecoder().decode(signature.getBytes());
	//
	// return publicSignature.verify(signatureBytes);
	// }
	//
	// private static Certificate loadCertificate ( File cerFile ) throws
	// FileNotFoundException , CertificateException , IOException ,
	// KeyStoreException {
	// InputStream fis = new FileInputStream( cerFile );
	// BufferedInputStream bis = new BufferedInputStream( fis );
	//
	// Certificate cert = null;
	// try {
	// CertificateFactory cerFactory = CertificateFactory.getInstance( "X.509"
	// );
	// cert = cerFactory.generateCertificate( bis );
	// } finally {
	// bis.close();
	// fis.close();
	// }
	//
	// return cert;
	// }
	//
	//
	// public static Certificate getCertificate(String certificateData) throws
	// CertificateException, IOException {
	// Certificate certificate = null;
	// CertificateFactory cf;
	//
	// if (!certificateData.contains(BEGIN_CERT)) {
	// StringBuilder sb = new StringBuilder();
	// sb.append(BEGIN_CERT);
	// sb.append("\n");
	// sb.append(certificateData);
	// sb.append("\n");
	// sb.append(END_CERT);
	// certificateData = sb.toString();
	// }
	//
	// cf = CertificateFactory.getInstance("X.509");
	// InputStream stream2 = new
	// ByteArrayInputStream(certificateData.getBytes());
	// certificate = cf.generateCertificate(stream2);
	// stream2.close();
	//
	// return certificate;
	// }
	//
	// public static void decryptRSA256(byte[] decodedText, PublicKey key)
	// throws IllegalBlockSizeException, BadPaddingException,
	// NoSuchAlgorithmException, NoSuchPaddingException,
	// InvalidKeyException, NoSuchProviderException, CMSException {
	//
	// Security.addProvider(new BouncyCastleProvider());
	// Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
	//
	// cipher.init(Cipher.DECRYPT_MODE, key);
	// // cipher.update(decodedText, 0, decodedText.length);
	// byte[] decryptedText = cipher.doFinal(decodedText, 0,
	// decodedText.length);
	//
	// byte[] base64Decripted = Base64.getEncoder().encode(decryptedText);
	//
	// String plainText = new String(base64Decripted);
	// System.out.println("RSA 256:");
	// System.out.println(plainText);
	// System.out.println();
	// }

}
