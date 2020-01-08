package net.assignment.booksLoan.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.assignment.booksLoan.model.Utente;
import net.assignment.booksLoan.repository.UtenteRepository;

@Service
public class DettagliUtenteImplService implements UserDetailsService {

	@Autowired
	UtenteRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String n_tessera) throws UsernameNotFoundException {
		// Cerca l'utente con il repository e, se non esiste, avvia un'eccezione
		Utente user = userRepository.findUserByN_tessera(n_tessera);

		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}

		// Creiamo l'oggetto UserDetails che sarà in sessione e lo restituiamo
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				Collections.singleton(
						new SimpleGrantedAuthority(user.getRuolo())));
	}

	public Utente findAdmByN_tessera(int n_tessera) {

	    return userRepository.findAdmByN_tessera(n_tessera);
	}

}