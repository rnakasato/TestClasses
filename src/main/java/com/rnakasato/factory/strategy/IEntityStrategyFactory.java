package com.rnakasato.factory.strategy;

import java.util.List;
import java.util.Map;

import com.rnakasato.strategy.IStrategy;

public interface IEntityStrategyFactory {
	public Map<String, Map<String, List<IStrategy>>> buildEntityRules();
}
