package com.rnakasato.regex;

/**
 * 
 * Tipos de dados armazenáveis em colunas(ex: data, numero, byte[])
 * @author Rafael
 *
 */
public class Atributo {
	private String valor;
	private String tipo;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
