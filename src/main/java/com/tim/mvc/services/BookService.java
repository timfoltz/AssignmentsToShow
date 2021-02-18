package com.tim.mvc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.tim.mvc.models.Book;
import com.tim.mvc.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRespository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRespository = bookRepository;
	}
	
	public List<Book> allBooks(){
		return bookRespository.findAll();
	}
	
	public Book createBook(Book b) {
		return bookRespository.save(b);
	}
	
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRespository.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	public Book updateBook(Long id, 
							String title, 
							String desc, 
							String lang, 
							String numOfPages) {
		Book book = findBook(id);
		book.setTitle(title);
		book.setDescription(desc);
		book.setLanguage(lang);
		book.setNumberOfPages(numOfPages);
		return bookRespository.save(book);
	}
	
	public Book deleteBook(Long id) {
		Book book = findBook(id);
		bookRespository.deleteById(id);
		return book;
	}

	public void updateBook(@Valid Book book) {
		bookRespository.save(book);
	}

}
