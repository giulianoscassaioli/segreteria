package com.segreteria.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.segreteria.model.Corso;
import com.segreteria.model.Studente;

@Component
public class DataBaseLoader implements CommandLineRunner{
	
	@Autowired
	ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {
		DataBase db = ctx.getBean(DataBase.class);
		valorizzaDataBase(db);

	}

	public void valorizzaDataBase(DataBase db) {
		Corso corso = new Corso();
		Corso corso1 = new Corso();
		Corso corso2 = new Corso();
		corso.setCodice("1a");
		corso.setIndirizzo("via roma 12");
		corso.setNome("Informatica");
		corso.setNumeroEsami(15);
		corso1.setCodice("2a");
		corso1.setIndirizzo("via livorno 15");
		corso1.setNome("Matematica");
		corso1.setNumeroEsami(15);
		corso2.setCodice("3a");
		corso2.setIndirizzo("via milano 19");
		corso2.setNome("Finanza");
		corso2.setNumeroEsami(15);
		db.aggiungiCorso(corso);
		db.aggiungiCorso(corso1);
		db.aggiungiCorso(corso2);
		Studente s1= new Studente();
		Studente s2= new Studente();
		Studente s3= new Studente();
		s1.setDataN("1996-12-11");
		s1.setCitta("roma");
		s1.setCorso(corso1);
		s1.setCognome("Mari");
		s1.setEmail("studente1@email.com");
		s1.setMatricola("AA45");
		s1.setNome("Roberto");
		s1.setIndirizzo("via Roma 14");
		
		s2.setDataN("2005-11-08");
		s2.setCitta("milano");
		s2.setCorso(corso2);
		s2.setCognome("Rossi");
		s2.setEmail("studente2@email.com");
		s2.setMatricola("ABC54");
		s2.setNome("Giovanni");
		s2.setIndirizzo("via Milano 17");
		
		s3.setDataN("2013-16-05");
		s3.setCitta("bari");
		s3.setCorso(corso);
		s3.setCognome("Verdi");
		s3.setEmail("studente3@email.com");
		s3.setMatricola("CC45A");
		s3.setNome("Mario");
		s3.setIndirizzo("via bari 18");
		
		db.aggiungiStudente(s1);
		db.aggiungiStudente(s2);
		db.aggiungiStudente(s3);
		
		
	}


}
