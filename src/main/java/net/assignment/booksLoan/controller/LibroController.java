package net.assignment.booksLoan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.assignment.booksLoan.model.Libro;
import net.assignment.booksLoan.service.LibroService;

@Controller
public class LibroController {

	@Autowired
	private LibroService libroService;

	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public String salvaLibro(@ModelAttribute("libro") Libro libro) {
		libroService.save(libro);
		return "redirect:/";
	}

	@RequestMapping(value = "/salva_sequel", method = RequestMethod.POST)
    public String saveSequel(@ModelAttribute("autore") Libro libro, int id_libro) {
	    libroService.setSequel(libro, id_libro);
        return "redirect:/sequelAdm/" + id_libro;
    }

	@RequestMapping(value = "/salva_sequel/{id}/{id_2}", method = RequestMethod.GET)
    public String saveSequelID(@PathVariable(name = "id") int id, @PathVariable(name = "id_2") int id_2) {
		libroService.setLibroSoloSuSequel(id, id_2);
        return "redirect:/sequelAdm/" + id;
    }

	@RequestMapping("/modifica/{id}")
	public ModelAndView mostraModificaLibro(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("modifica_libro");
		Libro l = libroService.get(id);
		mav.addObject("libro", l);
		return mav;
	}

	@RequestMapping("/nuovo_libro")
	public String mostraAggiungiLibro(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		return "nuovo_libro";
	}

	@RequestMapping("/nuovo_sequel/{id}")
    public String mostraAggiungiSequel(Model model, @PathVariable(name = "id") int id) {
		List<Libro> listLibri = libroService.trovaLibriDiversi(id);
        Libro libro = new Libro();
        model.addAttribute("listLibri", listLibri);
        model.addAttribute("libro", libro);
        model.addAttribute("id_libro", id);
        return "nuovo_sequel";
    }

	@RequestMapping("/elimina/{id}")
	public String eliminaId(@PathVariable(name = "id") int id) {
		try {
			libroService.delete(id);
		} catch(Exception e) {
			return "redirect:/?error";
		}

		return "redirect:/";
	}

	@RequestMapping("/elimina_sequel/{id}")
    public String eliminaSequel(@PathVariable(name = "id") int id) {
        libroService.deleteSequel(id);
        return "redirect:/";
    }

	@RequestMapping("/sequelAdm/{id}")
	public String aggiungiSequel(Model model, @PathVariable(name = "id") int id) {
		Libro libro = libroService.get(id);
		model.addAttribute("libro", libro);

        return "sequelAdm";
	}
	
}
