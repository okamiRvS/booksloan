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
import net.assignment.booksLoan.service.BookService;

@Controller
public class AppController {
	@Autowired
	private BookService service;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Libro> listBooks = service.listAll();
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
	    service.save(book);

	    return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_book");
	    Libro book = service.get(id);
	    mav.addObject("book", book);

	    return mav;
	}

	@RequestMapping("/delete/{id}")
	public String deleteProducte(@PathVariable(name = "id") int id) {
		service.delete(id);

	    return "redirect:/";
	}
}
