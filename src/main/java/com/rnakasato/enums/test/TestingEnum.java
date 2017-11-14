package com.rnakasato.enums.test;

public class TestingEnum {
	public static void main(String[] args) {
		printEnum(TestEnum.COD_201);
		printEnum(Test2Enum.COD_203);
	}

	public static void printEnum(IEnumOrigem origem) {
		System.out.println("Status: " + origem.getStatus());
		System.out.println("Origem: " + origem.getOrigem());
	}

}
