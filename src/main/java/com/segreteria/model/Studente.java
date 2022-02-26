package com.segreteria.model;



import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Studente {

	@NotEmpty
	private String matricola;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String cognome;
	@NotEmpty
	private String dataN;
	@NotEmpty
	private String email;
	@NotEmpty
	private String indirizzo;
	@NotEmpty
	private String citta;
	private Corso corso;
	
}
