package net.assignment.booksLoan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.assignment.booksLoan.model.Amministratore;
import net.assignment.booksLoan.model.Libro;
import net.assignment.booksLoan.model.Utente;
import net.assignment.booksLoan.service.CopieService;
import net.assignment.booksLoan.service.DettagliUtenteImplService;
import net.assignment.booksLoan.service.LibroService;

@Controller
public class UtenteController {

	@Autowired
	private LibroService libroService;
	@Autowired
    private CopieService copieService;
	@Autowired
    private DettagliUtenteImplService utenteService;

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
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int n_tessera = copieService.getN_tessera(username);
		if (ruolo.equalsIgnoreCase("[ROLE_ADMIN]")) {
		    Utente adm = utenteService.findAdmByN_tessera(n_tessera);
		    model.addAttribute("adm", adm);
			return "indexAdmin";
		} else {
			return "indexUtente";
		}
	}

}
