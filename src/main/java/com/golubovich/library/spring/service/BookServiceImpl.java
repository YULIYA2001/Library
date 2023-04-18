package com.golubovich.library.spring.service;


import com.golubovich.library.spring.model.Book;
import com.golubovich.library.spring.model.Person;
import com.golubovich.library.spring.repository.BookRepository;
import com.golubovich.library.spring.repository.PersonRepository;
import com.golubovich.library.spring.service.api.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements ItemService<Book> {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public BookServiceImpl(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Book add(Book book) throws ServiceException {
        validateBook(book);
        return bookRepository.save(book);
    }

    @Override
    public Book takeById(Long id) throws ServiceException {
        Optional<Book> oBook = bookRepository.findById(id);
        if (oBook.isEmpty()) {
            throw new ServiceException("No book with id");
        }
        return oBook.get();
    }

    @Override
    public Book changeReader(long id, long person_id) throws ServiceException {
        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            if (person_id == 0) {
                book.setPerson(null);
                return bookRepository.save(book);
            }

            Person person = personRepository.findById(person_id).orElse(null);

            if (person == null) {
                throw new ServiceException("Person id is not found");
            }

            book.setPerson(person);
        } else {
            throw new ServiceException("Id is not found");
        }
        return bookRepository.save(book);
    }

    @Override
    public List<Book> showAll() throws ServiceException {
        return bookRepository.findAll();
    }

    @Override
    public void delete(Long id) throws ServiceException {
        Book book = (Book) bookRepository.findById(id).orElse(null);
        if (book != null) {
            bookRepository.delete(book);
        } else {
            throw new ServiceException("Can't delete book. Id is not found");
        }
    }

    private void validateBook(Book book) throws ServiceException {
        if (book.getTitle() == null) {
            throw new ServiceException("Not valid data. No book title");
        }
        if (book.getLanguage() == null) {
            book.setLanguage("");
        }
        if (book.getPerson() != null) {
            book.setPerson(null);
        }
        if (book.getGenre() == null) {
            throw new ServiceException("Not valid data. No book genre");
        }
    }
}

