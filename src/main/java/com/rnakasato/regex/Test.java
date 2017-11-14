package com.rnakasato.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class Test {
	public static void main ( String[] args ) throws InstantiationException, IllegalAccessException {
        Map<Class, Object> map = new HashMap < Class , Object >();
        map.put( ListaObjetos.class , "String" );
        map.put( Objeto.class , 1 );
        
        ListaObjetos listaObj = new ListaObjetos();
        Objeto obj = new Objeto();
        Atributo atb = new Atributo();
        atb.setValor("A");
        
        
        
        Set<Entry < Class , Object >> entries = map.entrySet();
        
        for ( Entry < Class , Object > entry : entries ) {            
            Class clazz = entry.getKey();
            System.out.println( clazz.cast(entry.getValue()).getClass() );
        }
	}
        
}
