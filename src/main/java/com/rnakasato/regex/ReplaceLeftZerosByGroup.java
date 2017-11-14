package com.rnakasato.regex;

public class ReplaceLeftZerosByGroup {
	public static void main(String[] args) {
		String ipAddress = "192.168.1.1";
		String ipAddress2 = "192.068.01.01";
		String ipAddress3= "192.168.10.1";
		
		System.out.println(ipAddress.replaceAll("(^|\\.)0+(\\d)", "$1$2"));
		System.out.println(ipAddress2.replaceAll("(^|\\.)0+(\\d)", "$1$2"));
		System.out.println(ipAddress3.replaceAll("(^|\\.)0+(\\d)", "$1$2"));
		
		
	}
}
