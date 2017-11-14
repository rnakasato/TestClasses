package com.rnakasato.generator;

import java.security.SecureRandom;

/**
 * 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Geração de chaves randomicas em hexadecimal. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 11/08/2016 - @author rnakasato - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
public class CSPRNGUtils {
	// Tamanho em bytes
	public static final int SIZE_16 = 16;

	/**
	 * 
	 * Gera chave hexadecimal de 128 bits
	 * 
	 * @param byteSize
	 * @return
	 */
	public static String generateHexKey(int byteSize) {
		final SecureRandom secureRandom = new SecureRandom();
		final byte[] bytes = new byte[byteSize];
		secureRandom.nextBytes(bytes);
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02X", b));
		}
		String key = sb.toString();
		return key;
	}

	/**
	 * 
	 * Gera chave numérica com o valor máximo informado
	 * 
	 * @param byteSize
	 * @return
	 */
	public static String generateNumber(int maxValue) {
		String number = null;
		SecureRandom sr = new SecureRandom();
		Integer num = sr.nextInt(maxValue);
		number = num.toString();
		return number;
	}

	/**
	 * 
	 * Gera um numero aleatório grande
	 * 
	 * @return numero aleatorio string
	 */

	public static String generateLongNumber(int digits) {
		String number = null;
		SecureRandom sr = new SecureRandom();
		Long num = 0L;

		while (num <= 0) {
			num = sr.nextLong();
		}

		number = num.toString();

		while (number.length() != digits) {
			if (number.length() < digits) {
				number = "0" + number;
			} else if (number.length() > digits) {
				number = number.substring(0, number.length() - 1);
			}
		}
		return number;
	}

	public static String generateLongNumber() {
		String number = null;
		SecureRandom sr = new SecureRandom();
		Long num = sr.nextLong();
		number = num.toString();
		return number;
	}

	public static void main(String[] args) {
		System.out.println(CSPRNGUtils.generateHexKey(SIZE_16));
	}
}
