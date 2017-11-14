package com.rnakasato.enums.test;

public enum TestEnum implements IEnumOrigem {
	COD_201("201", "CODIGO 201"), COD_202("202", "CODIGO 202");

	private String origem;
	private String status;

	private TestEnum(String origem, String status) {
		this.origem = origem;
		this.status = status;
	}

	@Override
	public String getOrigem() {
		// TODO Auto-generated method stub
		return origem;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

}
