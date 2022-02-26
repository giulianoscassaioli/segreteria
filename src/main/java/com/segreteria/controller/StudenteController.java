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

import com.segreteria.model.Studente;
import com.segreteria.util.DataBase;

@Controller
public class StudenteController {
	
	@Autowired
	ApplicationContext ctx;
    
	@GetMapping("/studenti")
	public ModelAndView viewStudenti() {
		DataBase database= ctx.getBean(DataBase.class);
		
		return new ModelAndView("studentigest","database",database);
		
	}
	
	@GetMapping("/homestud")
	public String home(){
		
		return "index";
	}
	
	@GetMapping("/eliminaStudente/{matricola}")
	public String elimina(Model model,@PathVariable String matricola){
		DataBase database = ctx.getBean(DataBase.class);
		for(int i=0;i<database.getStudenti().size();i++) {
			if(database.getStudenti().get(i).getMatricola().equals(matricola)) {
				database.getStudenti().remove(database.getStudenti().get(i));
				return "redirect:/studenti";
			}
		}
		
		return "errorView";
	}
	
	@GetMapping("/aggiornaStudente/{matricola}")
	public ModelAndView aggiorna(@PathVariable String matricola){
		DataBase database= ctx.getBean(DataBase.class);
		Studente s= database.getStudentibyMatricola(matricola);
		ModelAndView model=new ModelAndView("aggiornastudente");
		model.addObject("matricola", matricola);
		model.addObject("corsi",database.getCorsi());
		model.addObject("studente", s);
		
		return model;
	}
	
	@PostMapping("/aggiornaStudente2/{matricola}")
	public String aggiorna2(@Valid Studente studente,BindingResult result,Model model, @PathVariable String matricola){
		DataBase database= ctx.getBean(DataBase.class);
		studente.setMatricola(matricola);
		for(int i=0; i<database.getStudenti().size(); i++) {
			if(database.getStudenti().get(i).getMatricola().equals(studente.getMatricola())) {
				int index= database.getStudenti().lastIndexOf(database.getStudenti().get(i));
				database.getStudenti().set(index, studente);
				
				return "redirect:/studenti";
			}
		}
		
		return "errorView";
	}
	
	@GetMapping("/studenteAggiungi")
	public String aggiungiStudente(Model model){
		DataBase database= ctx.getBean(DataBase.class);
		model.addAttribute("corsi", database.getCorsi());
		model.addAttribute("studente", new Studente());
		return "aggiungistudente";
	}
	
	@PostMapping("/aggiungiStudente2")
	public String aggiungiStudente2(@Valid Studente studente,BindingResult result,Model model){
		if(result.hasErrors()) {
			return "errorView";
		}
		DataBase database= ctx.getBean(DataBase.class);
		if(database.checkMatricola(studente.getMatricola())) {
			return "studentegiaesistente";
		}
		database.aggiungiStudente(studente);
		
		return "redirect:/studenti";
	}
	
	
	
		
	
	

}
