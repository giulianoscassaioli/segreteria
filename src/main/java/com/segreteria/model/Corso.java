package com.segreteria.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Corso {
	
	@NotEmpty
	private String codice;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String indirizzo;
	@Min(value = 10)
	private int numeroEsami;

}
