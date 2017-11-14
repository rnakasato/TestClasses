package com.rnakasato.builder;

import org.apache.commons.lang3.StringUtils;

public class SQLBuilder {

	private StringBuilder sql;

	public SQLBuilder() {
		sql = new StringBuilder();
		sql.append(" SELECT p FROM Person p WHERE 1=1 ");
	}

	public SQLBuilder addName(String name) {
		if (StringUtils.isNotEmpty(name)) {
			sql.append(" AND name = :name ");
			// sql.append(name);
		}
		return this;

	}

	public SQLBuilder addLastName(String lastName) {
		if (StringUtils.isNotEmpty(lastName)) {
			sql.append(" AND lastName = :lastName ");
			// sql.append(lastName);
		}
		return this;

	}

	public String getSQL() {
		return sql.toString();
	}

	public static void main(String[] args) {
		BuilderRunnerThread t1;
		BuilderRunnerThread t2;

		// for (int i = 0; i < 10000; i++) {
		// t1 = new BuilderRunnerThread("Rafael", "Nakasato");
		// t2 = new BuilderRunnerThread("Aline", "Silva");
		// t1.start();
		// t2.start();
		// }
		//
		String sql = new SQLBuilder().addName("TESTE").addLastName(null).getSQL();
		System.out.println(sql);

	}

}
