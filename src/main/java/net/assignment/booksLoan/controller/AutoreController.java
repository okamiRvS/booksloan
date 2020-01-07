package net.assignment.booksLoan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.assignment.booksLoan.model.Autore;
import net.assignment.booksLoan.model.Libro;
import net.assignment.booksLoan.service.AutoreService;
import net.assignment.booksLoan.service.LibroService;

@Controller
public class AutoreController {


	@Autowired
	private AutoreService autoreService;
	@Autowired
	private LibroService libroService;
	
	@RequestMapping(value = "/salva_autore", method = RequestMethod.POST)
    public String salvaAutore(@ModelAttribute("autore") Autore autore, int id_libro) {
	    autoreService.setAutoreScritto(autore, id_libro);
        return "redirect:/autoriAdm/" + id_libro;
    }

	@RequestMapping(value = "/salva_autore/{id}/{id_autore}", method = RequestMethod.GET)
    public String salvaAutoreID(@PathVariable(name = "id") int id, @PathVariable(name = "id_autore") int id_autore) {
	    autoreService.setAutoreSoloSuScritto(id, id_autore);
        return "redirect:/autoriAdm/" + id;
    }
	
	@RequestMapping("/nuovo_autore/{id}")
    public String mostraAggiungiAutore(Model model, @PathVariable(name = "id") int id) {
		List<Autore> listAutori = autoreService.trovaAutoriDiversi(id);
        Autore autore = new Autore();
        model.addAttribute("listAutori", listAutori);
        model.addAttribute("autore", autore);
        model.addAttribute("id_libro", id);
        return "nuovo_autore";
    }
	
	@RequestMapping("/elimina_autore/{id_autore}")
	public String eliminaAutore(@PathVariable(name = "id_autore") int id_autore) {
		autoreService.deleteScritto(id_autore);
		return "redirect:/";
	}
	
	@RequestMapping("/autoriAdm/{id}")
	public String aggiungiAutore(Model model, @PathVariable(name = "id") int id) {
		Libro libro = libroService.get(id);
		model.addAttribute("libro", libro);
		
        return "autoriAdm";
	}
	

}
