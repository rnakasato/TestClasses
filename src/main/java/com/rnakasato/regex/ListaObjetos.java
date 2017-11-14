package com.rnakasato.regex;

import java.util.List;

/**
 * Representa uma estrutura de lista contendo objetos que podem ser listas de objetos ou objetos
 * também deverá ser armazenado o relacionamento com FK de um Objeto pai e do ObjetoPrincipal
 * @author Rafael
 *
 */
public class ListaObjetos extends Objeto{
	List<Objeto> objetos;

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

}
