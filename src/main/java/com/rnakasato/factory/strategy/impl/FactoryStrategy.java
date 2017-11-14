package com.rnakasato.factory.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rnakasato.factory.strategy.IEntityStrategyFactory;
import com.rnakasato.strategy.IStrategy;

public class FactoryStrategy {
	private static Map<String, Map<String, List<IStrategy>>> rns;
	private static List<IEntityStrategyFactory> factoryList = new ArrayList<>();

	public static List<IStrategy> build(Object entity, String operation) {
		if (rns == null) {
			initStrategies();
		}
		List<IStrategy> operationRules = new ArrayList<>();
		Map<String, List<IStrategy>> entityRules = rns.get(entity.getClass().getName());
		if (entityRules != null) {
			operationRules = entityRules.get(operation);
		}
		return operationRules;
	}

	private static void initStrategies() {
		factoryList.add(new StringStrategyFactory());
		factoryList.add(new IntegerStrategyFactory());

		rns = new HashMap<>();

		for (IEntityStrategyFactory iEntityStrategyFactory : factoryList) {
			rns.putAll(iEntityStrategyFactory.buildEntityRules());
		}
	}
}
