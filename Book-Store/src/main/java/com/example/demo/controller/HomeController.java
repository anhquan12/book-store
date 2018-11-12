package com.example.demo.controller;


import com.example.demo.entites.Book;
import com.example.demo.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;

@Controller
public class HomeController {
	private static String UPLOADED_FOLDER = "target/classes/static/uploaded/";

	@Autowired
	private BookDAO bookDAO;
//
//
//	private String emailAdmin = "_";
//	private String emailUser = "_";
//	private String password = "_";

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping(path = "/book/create", method = RequestMethod.GET)
	public String createBook(@ModelAttribute Book book) {
		return "book-form";
	}

	@RequestMapping(path = "/book/create", method = RequestMethod.POST)
	public String saveBook(@Valid Book book, BindingResult result,
						   @RequestParam("myFile") MultipartFile myFile) {
		book.setImgUrl("_");
//		new ProductValidator().validate(book, result);
		if (result.hasErrors()) {
			return "book-form";
		}
		try {
			Path path = Paths.get(UPLOADED_FOLDER + myFile.getOriginalFilename());
			Files.write(path, myFile.getBytes());
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		book.setImgUrl("/uploaded/" + myFile.getOriginalFilename());
		bookDAO.save(book);
		return "redirect:/book/list";
	}

	@RequestMapping(path = "/book/edit/{id}", method = RequestMethod.GET)
	public String editBook(int id, Model model) {
		Optional<Book> optionalProduct = bookDAO.findById(id);
		if (optionalProduct.isPresent()) {
			model.addAttribute("book", optionalProduct.get());
			return "book-form";
		} else {
			return "not-found";
		}
	}

	@RequestMapping(path = "/book/delete/{id}", method = RequestMethod.POST)
	public String deleteBook(int id) {
		Optional<Book> optionalProduct = bookDAO.findById(id);
		if (optionalProduct.isPresent()) {
//			optionalProduct.get().setStatus(0);
			bookDAO.delete(optionalProduct.get());
			return "redirect:/book/list";
		} else {
			return "not-found";
		}
	}

//	@RequestMapping(path = "/pay/book/{id}", method = RequestMethod.POST)
//	public String payBook(int id) {
//		Optional<Book> optionalProduct = bookRepository.findById(id);
//		if (optionalProduct.isPresent()) {
//			optionalProduct.get().setStatus(0);
//			bookRepository.delete(optionalProduct.get());
//			return "redirect:/book/list";
//		} else {
//			return "not-found";
//		}
//	}

//	@RequestMapping(path = "/book/list", method = RequestMethod.GET)
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getListBooks(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "4") int limit) {
		Page<Book> pagination = bookDAO.findBooksByStatus(1, PageRequest.of(page - 1, limit));
		model.addAttribute("pagination", pagination);
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		model.addAttribute(" ", Calendar.getInstance().getTime());
		return "home";
	}
}
