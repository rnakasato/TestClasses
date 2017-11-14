package com.rnakasato.instance;

import java.util.HashMap;
import java.util.Map;

import com.rnakasato.builder.DummyClassFirst;

public class MapObjectInstanceReference {
	public static void main(String[] args) {
		Map<Integer,Map<Integer,DummyClassFirst>> map = new HashMap<>();
		for(Integer i = 0; i < 100; i ++){
			Map<Integer,DummyClassFirst> entityMap = new HashMap<>();
			for(Integer j = 0; j < 100; j ++){
				DummyClassFirst dcf = new DummyClassFirst();
				dcf.setNome(String.valueOf(j));
				entityMap.put(j, dcf);
			}
			map.put(i, entityMap);
		}
		DummyClassFirst dummy = map.get(5).get(5);
		System.out.println(dummy.getNome());		
		dummy.setNome("HUEHUE");
		
		
		DummyClassFirst dummy2 = map.get(5).get(5);
		System.out.println(dummy2.getNome());
		
		
		
		
		
		
		
	}
}
