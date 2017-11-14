package com.rnakasato.enums.test;

public enum Test2Enum implements IEnumOrigem {
	COD_203("203", "CODIGO 203"), COD_204("204", "CODIGO 204");

	private String origem;
	private String status;

	private Test2Enum(String origem, String status) {
		this.origem = origem;
		this.status = status;
	}

	@Override
	public String getOrigem() {
		return origem;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
}
