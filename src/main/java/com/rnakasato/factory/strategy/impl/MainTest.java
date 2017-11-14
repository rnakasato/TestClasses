package com.rnakasato.factory.strategy.impl;

import java.util.List;

import com.rnakasato.strategy.IStrategy;

public class MainTest {
	public static void main(String[] args) {
		List<IStrategy> strategyList = FactoryStrategy.build(new Integer(1), "SAVE");
		for (IStrategy iStrategy : strategyList) {
			iStrategy.process(null);
		}
		strategyList = FactoryStrategy.build("TESTE", "SAVE");
		for (IStrategy iStrategy : strategyList) {
			iStrategy.process(null);
		}
	}
}
