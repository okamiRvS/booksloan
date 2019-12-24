package net.assignment.booksLoan.controller;

import java.util.ArrayList;
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
	private LibroService bookService;
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
		
		List<Libro> listBooks = bookService.listAll();
		List<Autore> listAutore = new ArrayList<Autore>();
		List<Libro> libriSuccessivi = new ArrayList<Libro>();
		for (Libro libro : listBooks) {			
			try {
				listAutore = autoreService.trovaAutoreScritto(libro.getId());
				libro.setListAutore(listAutore);
			} catch (Exception e) {
				System.out.println("Non ha autori questo libro");
			}
			
			try {
				libriSuccessivi = bookService.trovaSequel(libro.getId());
				libro.setLibriSuccessivi(libriSuccessivi);
			} catch (Exception e2) {
				System.out.println("Non ha sequel questo libro");
			}
		}
		model.addAttribute("listBooks", listBooks);

		// Get ruolo dell'utente in sessione
		String ruolo = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		if (ruolo.equalsIgnoreCase("[ROLE_ADMIN]")) {
			return "indexAdmin";
		} else {
			return "indexUtente";
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("book") Libro book) {
		bookService.save(book);
		return "redirect:/";
	}

	@RequestMapping(value = "/save_copia", method = RequestMethod.POST)
	public String saveCopia(@ModelAttribute("copia") Copia copia) {
		if(copieService.existsById(copia.getIsbn())) {
			return "redirect:/nuova_copia/" + copia.getId() + "?error";
		}
	    copieService.setCopia(copia.getIsbn(), copia.getId(), copia.getDisponibilita());
	    return "redirect:/";
	}

	@RequestMapping(value = "/save_autore", method = RequestMethod.POST)
    public String saveAutore(@ModelAttribute("autore") Autore autore, int id_libro) {
	    autoreService.setAutoreScritto(autore, id_libro);
        return "redirect:/autoriAdm/" + id_libro;
    }
	
	@RequestMapping(value = "/save_autore/{id}/{id_autore}", method = RequestMethod.GET)
    public String saveAutoreID(@PathVariable(name = "id") int id, @PathVariable(name = "id_autore") int id_autore) {
	    autoreService.setAutoreSoloSuScritto(id, id_autore);
        return "redirect:/autoriAdm/" + id;
    }
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_book");
		Libro book = bookService.get(id);
		mav.addObject("book", book);
		return mav;
	}

	@RequestMapping("/nuovo_libro")
	public String showAggiungiLibroPage(Model model) {
		Libro libro = new Libro();
		model.addAttribute("book", libro);
		return "nuovo_libro";
	}

	@RequestMapping("/delete/{id}")
	public String deleteProducte(@PathVariable(name = "id") int id) {
		try {
			bookService.delete(id);
		} catch(Exception e) {
			return "redirect:/?error";
		}

		return "redirect:/";
	}

	@RequestMapping("/ind2")
	public String showBoot(Model model) {
		return "ind2";
	}

	@RequestMapping("/copie/{id}")
	public String showProductCopies(Model model, @PathVariable(name = "id") int id) {
		List<Copia> listCopie = copieService.CopieId(id);
		model.addAttribute("listCopie", listCopie);
		String book = copieService.TitoloId(id);
		model.addAttribute("book", book);

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

	@RequestMapping("/restituisci/{isbn}")
    public String restituisciCopia(Model model, @PathVariable(name = "isbn") Long isbn) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int n_tessera = copieService.getN_tessera(username);
        copieService.restituisciISBN(isbn);
        prestitoService.delete(isbn, n_tessera);
        model.addAttribute("username", username);
        return "restituisci";

    }

	@RequestMapping("/prenotazioni/")
	public String prenotazioni(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int n_tessera = copieService.getN_tessera(username);
		List<Prestito> listaPrenotazioni = prestitoService.ElencoPrestiti(n_tessera);
		model.addAttribute("listaPrenotazioni", listaPrenotazioni);
        return "prenotazioni";
    }


	@RequestMapping("/copieAdm/{id}")
	public String aggiungiCopia(Model model, @PathVariable(name = "id") int id) {
		List<Copia> listCopie = copieService.CopieId(id);
        model.addAttribute("listCopie", listCopie);
        String book = copieService.TitoloId(id);
        model.addAttribute("book", book);
        return "copieAdm";
	}

	@RequestMapping("/nuova_copia/{id}")
	public String showAggiungiCopiaPage(Model model, @RequestParam(value="error", required=false) String param, @PathVariable(name = "id") int id) {
		Boolean error = false;
		if(param != null) {
			error = true;
		}
		Copia copia = new Copia();
	    copia.setId(id);
	    model.addAttribute("copia", copia);
	    model.addAttribute("error", error);
	    return "nuova_copia";
	}

	@RequestMapping("/autoriAdm/{id}")
	public String aggiungiAutore(Model model, @PathVariable(name = "id") int id) {
		List<Autore> listAutori = autoreService.trovaAutoreScritto(id);
        model.addAttribute("listAutori", listAutori);
        String book = copieService.TitoloId(id);
        model.addAttribute("book", book);
        return "autoriAdm";
	}
	
	@RequestMapping("/nuovo_autore/{id}")
    public String showAggiungiAutoriPage(Model model, @PathVariable(name = "id") int id) {
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
	
	@RequestMapping("/elimina_copia/{isbn}")
	public String eliminaCopia(@PathVariable(name = "isbn") long isbn) {
		copieService.delete(isbn);
		return "redirect:/";
	}

	@RequestMapping("/sequelAdm/{id}")
	public String aggiungiSequel(Model model, @PathVariable(name = "id") int id) {
		List<Libro> listLibro = bookService.trovaSequel(id);
        model.addAttribute("listLibro", listLibro);
        String book = copieService.TitoloId(id);
        model.addAttribute("book", book);
        return "sequelAdm";
	}
	
	@RequestMapping("/nuovo_sequel/{id}")
    public String showAggiungiSequel(Model model, @PathVariable(name = "id") int id) {
		List<Libro> listLibri = bookService.trovaLibriDiversi(id);
        Libro libro = new Libro();
        model.addAttribute("listLibri", listLibri);
        model.addAttribute("libro", libro);
        model.addAttribute("id_libro", id);
        return "nuovo_sequel";
    }
	
	@RequestMapping(value = "/save_sequel", method = RequestMethod.POST)
    public String saveSequel(@ModelAttribute("autore") Libro libro, int id_libro) {
	    bookService.setSequel(libro, id_libro);
        return "redirect:/sequelAdm/" + id_libro;
    }
	
	@RequestMapping(value = "/save_sequel/{id}/{id_2}", method = RequestMethod.GET)
    public String saveSequelID(@PathVariable(name = "id") int id, @PathVariable(name = "id_2") int id_2) {
		System.out.println(id);
		System.out.println("*****");
		System.out.println(id_2);
		bookService.setLibroSoloSuSequel(id, id_2);
        return "redirect:/sequelAdm/" + id;
    }
}
