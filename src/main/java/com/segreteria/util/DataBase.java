package com.segreteria.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.segreteria.model.Corso;
import com.segreteria.model.Studente;

import lombok.Data;

@Component
@Data
public class DataBase {

	private List<Corso> corsi = new ArrayList<>();
	private List<Studente> studenti = new ArrayList<>();

	public boolean checkMatricola(String matricola) {

		Studente s = getStudentibyMatricola(matricola);
		if (s != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkCodice(String codice) {

		Corso c= getCorsiByCodice(codice);
		if (c != null) {
			return true;
		} else {
			return false;
		}
	}

	public void aggiungiCorso(Corso corso) {
		corsi.add(corso);
	}

	public void aggiungiStudente(Studente studente) {
		studenti.add(studente);
	}

	public Studente getStudentibyMatricola(String matricola) {

		for (Studente s : studenti) {
			if (s.getMatricola().equalsIgnoreCase(matricola)) {
				return s;
			}
		}
		return null;
	}

	public Corso getCorsiByCodice(String codice) {

		for (Corso c : corsi) {
			if (c.getCodice().equalsIgnoreCase(codice)) {
				return c;
			}
		}
		return null;
	}

}
