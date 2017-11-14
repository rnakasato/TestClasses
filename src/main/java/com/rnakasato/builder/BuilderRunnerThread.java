package com.rnakasato.builder;

public class BuilderRunnerThread extends Thread {
	private String name;
	private String lastName;

	public BuilderRunnerThread(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}

	@Override
	public void run() {
		String nome = new SQLBuilder().addName(name).addLastName(lastName).getSQL();
		System.out.println(nome);
		super.run();
	}

}
