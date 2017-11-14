package com.rnakasato.regex;

import java.util.List;

/**
 * Representa a estrutura principal de um JSON ex:
 * (Objeto JSON)
 *		 {
 * 			teste:"hahaha"
 * 		 }
 * @author Rafael
 *
 */
public class ObjetoPrincipal {
	private List<Objeto> objetos;

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

}
