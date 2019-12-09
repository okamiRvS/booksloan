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

import net.assignment.booksLoan.model.Book;
import net.assignment.booksLoan.service.BookService;

@Controller
public class AppController {
	@Autowired
	private BookService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Book> listBooks = service.listAll();
		model.addAttribute("listBooks", listBooks);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    Book book = new Book();
	    model.addAttribute("book", book);
	     
	    return "new_book";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("book") Book book) {
	    service.save(book);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{isbn}")
	public ModelAndView showEditProductPage(@PathVariable(name = "isbn") int isbn) {
	    ModelAndView mav = new ModelAndView("edit_book");
	    Book book = service.get(isbn);
	    mav.addObject("book", book);
	     
	    return mav;
	}
	
	@RequestMapping("/delete/{isbn}")
	public String deleteProducte(@PathVariable(name = "isbn") int isbn) {
		service.delete(isbn);
	     
	    return "redirect:/";
	}
}
