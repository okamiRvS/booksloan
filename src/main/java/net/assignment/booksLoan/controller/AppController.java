package net.assignment.booksLoan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.assignment.booksLoan.model.Autore;
import net.assignment.booksLoan.model.Copia;
import net.assignment.booksLoan.model.Libro;
import net.assignment.booksLoan.model.Prestito;
import net.assignment.booksLoan.service.AutoreService;
import net.assignment.booksLoan.service.CopieService;
import net.assignment.booksLoan.service.LibroService;
import net.assignment.booksLoan.service.PrestitoService;

@Controller
public class AppController {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private CopieService copieService;
	@Autowired
	private PrestitoService prestitoService;
	@Autowired
	private AutoreService autoreService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = { "", "/", "/index" })
	public String index(Model model, @RequestParam(value="error", required=false) String param) {
		Boolean error = false;
		if(param != null) {
			error = true;
		}
		model.addAttribute("error", error);

		List<Libro> listaLibri = libroService.listAll();
		model.addAttribute("listaLibri", listaLibri);

		// Get ruolo dell'utente in sessione
		String ruolo = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		if (ruolo.equalsIgnoreCase("[ROLE_ADMIN]")) {
			return "indexAdmin";
		} else {
			return "indexUtente";
		}
	}

	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public String salvaLibro(@ModelAttribute("libro") Libro libro) {
		libroService.save(libro);
		return "redirect:/";
	}

	@RequestMapping(value = "/salva_copia", method = RequestMethod.POST)
	public String salvaCopia(@ModelAttribute("copia") Copia copia) {
		if(copieService.existsById(copia.getIsbn())) {
			return "redirect:/nuova_copia/" + copia.getLibro().getId() + "?error";
		}
		System.out.println(copia.toString());
	    copieService.setCopia(copia);
	    return "redirect:/";
	}

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
	// ok
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

	// ok
	@RequestMapping("/modifica/{id}")
	public ModelAndView mostraModificaLibro(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("modifica_libro");
		Libro l = libroService.get(id);
		mav.addObject("libro", l);
		return mav;
	}

	@RequestMapping("/copie/{id}")
	public String mostraCopie(Model model, @PathVariable(name = "id") int id) {
		Libro libro = libroService.get(id);
		model.addAttribute("libro", libro);

		return "copie";
	}

	@RequestMapping("/prenota/{isbn}")
    public String prenotaCopia(Model model, @PathVariable(name = "isbn") Long isbn) {
        copieService.prenota(isbn);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int n_tessera = copieService.getN_tessera(username);
        copieService.setUtentePrestito(n_tessera, isbn);
        model.addAttribute("username", username);
        return "prenota";

    }

	// ok
	@RequestMapping("/restituisci/{isbn}")
    public String restituisciCopia(Model model, @PathVariable(name = "isbn") Long isbn) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        copieService.restituisciISBN(isbn);
        prestitoService.delete(isbn);
        model.addAttribute("username", username);
        return "restituisci";

    }

	// ok
	@RequestMapping("/prenotazioni/")
	public String prenotazioni(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int n_tessera = copieService.getN_tessera(username);
		List<Prestito> listaPrenotazioni = prestitoService.ElencoPrestiti(n_tessera);
		model.addAttribute("listaPrenotazioni", listaPrenotazioni);
        return "prenotazioni";
    }

	// ok
	@RequestMapping("/nuovo_libro")
	public String mostraAggiungiLibro(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		return "nuovo_libro";
	}

	// ok
	@RequestMapping("/nuova_copia/{id}")
	public String mostraAggiungiCopia(Model model, @RequestParam(value="error", required=false) String param, @PathVariable(name = "id") int id) {
		Boolean errore = false;
		if(param != null) {
			errore = true;
		}
		Copia copia = new Copia();
	    copia.setLibro(libroService.get(id));
	    model.addAttribute("copia", copia);
	    model.addAttribute("error", errore);
	    return "nuova_copia";
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
	
	// ok
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
	
	@RequestMapping("/elimina_copia/{isbn}")
	public String eliminaCopia(@PathVariable(name = "isbn") long isbn) {
		copieService.delete(isbn);
		return "redirect:/";
	}

	@RequestMapping("/elimina_autore/{id_autore}")
	public String eliminaAutore(@PathVariable(name = "id_autore") int id_autore) {
		autoreService.deleteScritto(id_autore);
		return "redirect:/";
	}

	@RequestMapping("/elimina_sequel/{id}")
    public String eliminaSequel(@PathVariable(name = "id") int id) {
        libroService.deleteSequel(id);
        return "redirect:/";
    }
	
	// ok
	@RequestMapping("/copieAdm/{id}")
	public String aggiungiCopia(Model model, @PathVariable(name = "id") int id) {
		Libro libro = libroService.get(id);
		model.addAttribute("libro", libro);
		
        return "copieAdm";
	}
	
	// ok
	@RequestMapping("/autoriAdm/{id}")
	public String aggiungiAutore(Model model, @PathVariable(name = "id") int id) {
		Libro libro = libroService.get(id);
		model.addAttribute("libro", libro);
		
        return "autoriAdm";
	}
	
	// ok
	@RequestMapping("/sequelAdm/{id}")
	public String aggiungiSequel(Model model, @PathVariable(name = "id") int id) {
		Libro libro = libroService.get(id);
		model.addAttribute("libro", libro);
		
        return "sequelAdm";
	}
}
