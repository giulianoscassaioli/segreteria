package com.segreteria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.segreteria.model.Corso;
import com.segreteria.model.Studente;
import com.segreteria.util.DataBase;

@Controller
public class CorsoController {
    
	@Autowired
	ApplicationContext ctx;
    
	@GetMapping("/corsi")
	public ModelAndView viewCorsi() {
		DataBase database= ctx.getBean(DataBase.class);
		return new ModelAndView("corsigest","database",database);
		
	}
	
	@GetMapping("/homecorso")
	public String home(){
		
		return "index";
	}
	
	@GetMapping("/eliminaCorso/{codice}")
	public String elimina(Model model,@PathVariable String codice){
		DataBase database = ctx.getBean(DataBase.class);
		for(int i=0;i<database.getCorsi().size();i++) {
			if(database.getCorsi().get(i).getCodice().equals(codice)) {
				database.getCorsi().remove(database.getCorsi().get(i));
				return "redirect:/corsi";
			}
		}
		return "erroreView";
	}
	
	@GetMapping("/aggiornaCorso/{codice}")
	public ModelAndView aggiorna(@PathVariable String codice){
	
		DataBase database= ctx.getBean(DataBase.class);
		Corso c= database.getCorsiByCodice(codice);
		ModelAndView model=new ModelAndView("aggiornacorso");
		model.addObject("codice", codice);
		model.addObject("corso", c);
		
		return model;
	}
	
	@PostMapping("/aggiornaCorso2/{codice}")
	public String aggiorna2(@Valid Corso corso,BindingResult result,Model model, @PathVariable String codice){
		DataBase database= ctx.getBean(DataBase.class);
		corso.setCodice(codice);
		if(result.hasErrors()) {
			return "corsoerroreesami";
		}
		
		for(int i=0; i<database.getCorsi().size(); i++) {
			if(database.getCorsi().get(i).getCodice().equals(corso.getCodice())) {
				int index= database.getCorsi().lastIndexOf(database.getCorsi().get(i));
				database.getCorsi().set(index, corso);
				return "redirect:/corsi";
			}
		}
		
		return "errorView";
	}
	
	@GetMapping("/corsoAggiungi")
	public String aggiungiCorso(Model model){
		DataBase database= ctx.getBean(DataBase.class);
		model.addAttribute("corso", new Corso());
		return "aggiungicorso";
	}
	
	@PostMapping("/aggiungiCorso2")
	public String aggiungiCorso2(@Valid Corso corso,BindingResult result,Model model){
		if(result.hasErrors()) {
			return "corsoerroreesami";
		}
		DataBase database= ctx.getBean(DataBase.class);
		if(database.checkCodice(corso.getCodice())) {
			return "corsogiaesistente";
		}
		database.aggiungiCorso(corso);
		return "redirect:/corsi";
	}
	
}
