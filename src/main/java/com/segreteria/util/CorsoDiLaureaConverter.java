package com.segreteria.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.segreteria.model.Corso;

@Component
public class CorsoDiLaureaConverter implements Converter<String, Corso> {
 
	@Autowired
	ApplicationContext ctx;
	
	
	@Override
	public Corso convert(String codice) {
		
	    DataBase database= ctx.getBean(DataBase.class) ;
	    for(Corso c: database.getCorsi()) {
	    	if(c.getCodice().equals(codice)) {
	    		return c;
	    	}
	    }
		return null;
		
	}

}
