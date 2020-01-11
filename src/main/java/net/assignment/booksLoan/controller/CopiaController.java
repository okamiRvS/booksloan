package net.assignment.booksLoan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.assignment.booksLoan.model.Amministratore;
import net.assignment.booksLoan.model.Copia;
import net.assignment.booksLoan.model.Libro;
import net.assignment.booksLoan.model.Prestito;
import net.assignment.booksLoan.service.CopieService;
import net.assignment.booksLoan.service.DettagliUtenteImplService;
import net.assignment.booksLoan.service.LibroService;
import net.assignment.booksLoan.service.PrestitoService;

@Controller
public class CopiaController {

	@Autowired
	private CopieService copieService;
	@Autowired
	private LibroService libroService;
	@Autowired
	private PrestitoService prestitoService;
	@Autowired
    private DettagliUtenteImplService utenteService;
	
	@RequestMapping(value = "/salva_copia", method = RequestMethod.POST)
	public String salvaCopia(@ModelAttribute("copia") Copia copia) {
		if(copieService.existsById(copia.getIsbn())) {
			return "redirect:/nuova_copia/" + copia.getLibro().getId() + "?error";
		}
		System.out.println(copia.toString());
	    copieService.setCopia(copia);
	    return "redirect:/";
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

	@RequestMapping("/elimina_copia/{isbn}")
	public String eliminaCopia(@PathVariable(name = "isbn") long isbn) {
		copieService.delete(isbn);
		return "redirect:/";
	}

	@RequestMapping("/copieAdm/{id}")
	public String aggiungiCopia(Model model, @PathVariable(name = "id") int id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int n_tessera = copieService.getN_tessera(username);
        Amministratore adm = utenteService.findAdmByN_tessera(n_tessera);
	    model.addAttribute("adm", adm);
		Libro libro = libroService.get(id);
		model.addAttribute("libro", libro);
		
        return "copieAdm";
	}

}
