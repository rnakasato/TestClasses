package com.rnakasato.regex;

import java.util.List;

/**
 * Objeto que representa um registro em uma tabela, os atributos deverão ter a FK do objeto que representam
 * e no banco o objeto terá de possuir a FK do Objeto pai dele ou do ObjetoPrincipal, caso queira deixar mais generico
 * pode ser utilizado apenas o Objeto pai e fazer com q ObjetoPrincipal seja apenas um Objeto
 * @author Rafael
 *
 */
public class Objeto {
	List<Atributo> atributos;
	List<Objeto> objeto;
	String nomeObjeto;

	public List<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	public List<Objeto> getObjeto() {
		return objeto;
	}

	public void setObjeto(List<Objeto> objeto) {
		this.objeto = objeto;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

}
