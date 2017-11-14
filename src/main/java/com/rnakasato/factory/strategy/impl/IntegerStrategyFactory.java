package com.rnakasato.factory.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rnakasato.factory.strategy.IEntityStrategyFactory;
import com.rnakasato.strategy.IStrategy;
import com.rnakasato.strategy.RandomIntegerStrategy;

public class IntegerStrategyFactory implements IEntityStrategyFactory {

	@Override
	public Map<String, Map<String, List<IStrategy>>> buildEntityRules() {
		Map<String, Map<String, List<IStrategy>>> rulesMap = new HashMap<>();
		Map<String, List<IStrategy>> operationMap = new HashMap<>();

		List<IStrategy> rnsSave = new ArrayList<>();
		List<IStrategy> rnsUpdate = new ArrayList<>();
		List<IStrategy> rnsFind = new ArrayList<>();
		List<IStrategy> rnsDelete = new ArrayList<>();

		rnsSave.add(new RandomIntegerStrategy());

		operationMap.put("SAVE", rnsSave);
		operationMap.put("UPDATE", rnsUpdate);
		operationMap.put("DELETE", rnsDelete);
		operationMap.put("FIND", rnsFind);

		rulesMap.put(Integer.class.getName(), operationMap);

		return rulesMap;
	}

}
