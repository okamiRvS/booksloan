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
import net.assignment.booksLoan.model.Utente;
import net.assignment.booksLoan.service.BookService;
import net.assignment.booksLoan.service.UserService;

@Controller
public class AppController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;


	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Libro> listBooks = bookService.listAll();
		model.addAttribute("listBooks", listBooks);

		return "index";
	}

	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    Libro book = new Libro();
	    model.addAttribute("book", book);

	    return "new_book";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("book") Libro book) {
	    bookService.save(book);

	    return "redirect:/";
	}
	
	
	@RequestMapping(value = "/accedi", method = RequestMethod.POST)
	public String checkUser(@ModelAttribute("book") Utente user) {
	    Utente a = userService.accedi(user.getN_tessera());
	    System.out.println(a.getCognome());

	    return "redirect:/";
	}
	
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_book");
	    Libro book = bookService.get(id);
	    mav.addObject("book", book);

	    return mav;
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
}
