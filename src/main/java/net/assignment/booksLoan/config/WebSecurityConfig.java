package net.assignment.booksLoan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.assignment.booksLoan.service.DettagliUtenteImplService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	//Necessario per impedire che la sicurezza venga applicata alle risorse
    //Come il CSS, immagini, js
    String[] resources = new String[]{
            "/include/**",
            "/css/**",
            "/icons/**",
            "/img/**",
            "/js/**",
            "/layer/**"
    };
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        //.antMatchers("/","/login").permitAll()
	        .antMatchers("/login").permitAll()
	        // .access("hasRole('USER') or hasRole('ADMIN')")	        
	        .antMatchers(
	        		"/indexAdmin*",
	        		"/copieAdm/*",
	        		"/sequelAdm/*",
	        		"/autoriAdm/*",
	        		"/modifica/*",
	        		"/nuova_copia/*",
	        		"/nuovo_autore/*",
	        		"/nuovo_libro/*",
	        		"/nuovo_sequel/*").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true")
                .usernameParameter("n_tessera")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
    
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //Crea il codificatore di password
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		// Il numero 4 rappresenta la potenza desiderata per la crittografia.
		// Può essere compreso tra 4 e 31.
		// Se non inserisci un numero, il programma ne userà uno ogni volta
		// che avvii l'applicazione, quindi le tue password crittografate non funzioneranno bene
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    DettagliUtenteImplService userDetailsService;
	
    //Registrare il servizio per gli utenti e il codificatore di password
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
}