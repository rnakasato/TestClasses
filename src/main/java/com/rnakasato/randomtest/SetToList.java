package com.rnakasato.randomtest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetToList {
	public static void main(String[] args) {
		Set<String> strings = new HashSet<>();
		strings.add("A");
		strings.add("A");
		strings.add("B");
		strings.add("C");
		
		List<String> listaStrings = new ArrayList<>(strings);
		for (String string : listaStrings) {
			System.out.println(string);
		}
		
	}
}
