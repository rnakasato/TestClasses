package com.rnakasato.factory.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rnakasato.factory.strategy.IEntityStrategyFactory;
import com.rnakasato.strategy.IStrategy;
import com.rnakasato.strategy.RandomStringStrategy;

public class StringStrategyFactory implements IEntityStrategyFactory {

	@Override
	public Map<String, Map<String, List<IStrategy>>> buildEntityRules() {
		Map<String, Map<String, List<IStrategy>>> stringRulesMap = new HashMap<>();
		Map<String, List<IStrategy>> stringOperationMap = new HashMap<>();

		List<IStrategy> rnsSave = new ArrayList<>();
		List<IStrategy> rnsUpdate = new ArrayList<>();
		List<IStrategy> rnsFind = new ArrayList<>();
		List<IStrategy> rnsDelete = new ArrayList<>();

		rnsSave.add(new RandomStringStrategy());

		stringOperationMap.put("SAVE", rnsSave);
		stringOperationMap.put("UPDATE", rnsUpdate);
		stringOperationMap.put("DELETE", rnsDelete);
		stringOperationMap.put("FIND", rnsFind);

		stringRulesMap.put(String.class.getName(), stringOperationMap);

		return stringRulesMap;
	}

}
