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
	public String index(Model model) {
		
		List<Libro> listBooks = bookService.listAll();
		List<Autore> listAutore = new ArrayList<Autore>();
		for (Libro libro : listBooks) {
			try {
				listAutore = autoreService.trovaAutoreScritto(libro.getId());
				libro.setListAutore(listAutore);
			} catch (Exception e) {
				System.out.println("non ha autori questo libro");
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
		bookService.delete(id);

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
		System.out.println("Ital");
		copieService.setUtentePrestito(n_tessera, isbn);
		model.addAttribute("username", username);
		return "prenota";

	}

	@RequestMapping("/prenotazioni/")
	public String prenotazioni(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int n_tessera = copieService.getN_tessera(username);
		List<Prestito> listaPrenotazioni = prestitoService.ElencoPrestiti(n_tessera);
		model.addAttribute("listaPrenotazioni", listaPrenotazioni);
		return "prenotazioni";
	}
}
