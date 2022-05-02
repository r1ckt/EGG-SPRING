package edu.egg.spring.service;


import edu.egg.spring.entity.Book;
import edu.egg.spring.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Transactional
    public void create(Book bookDTO)  {
        Book book = new Book();

        book.setIsbn(bookDTO.getIsbn());
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        book.setYear(bookDTO.getYear());
        book.setPublisher(bookDTO.getPublisher());
        book.setCopies(bookDTO.getCopies());
        book.setBorrowedCopies(bookDTO.getBorrowedCopies());
        book.setRemainedCopies(bookDTO.getCopies() - bookDTO.getBorrowedCopies());

        bookRepository.save(book);
    }

    @Transactional
    public void update(Book bookDTO) {
        Book book = bookRepository.findById(bookDTO.getId()).get();

        book.setIsbn(bookDTO.getIsbn());
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        book.setYear(bookDTO.getYear());
        book.setPublisher(bookDTO.getPublisher());
        book.setCopies(bookDTO.getCopies());
        book.setBorrowedCopies(bookDTO.getBorrowedCopies());
        book.setRemainedCopies(bookDTO.getCopies() - bookDTO.getBorrowedCopies());
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book getById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}


