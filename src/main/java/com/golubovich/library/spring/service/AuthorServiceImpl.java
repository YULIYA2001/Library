package com.golubovich.library.spring.service;

import com.golubovich.library.spring.model.Author;
import com.golubovich.library.spring.repository.AuthorRepository;
import com.golubovich.library.spring.service.api.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author add(Author author) throws ServiceException {
        validateAuthor(author);
        return authorRepository.save(author);
    }

    @Override
    public Author changeInfo(long id, String info) throws ServiceException {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            author.setInfo(info);
        } else {
            throw new ServiceException("Id is not found");
        }
        return authorRepository.save(author);
    }

    @Override
    public List<Author> showAll() throws ServiceException {
        return authorRepository.showAll();
    }

    @Override
    public Author takeById(Long id) throws ServiceException {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            return author;
        } else {
            throw new ServiceException("No author with id");
        }
    }


    private void validateAuthor(Author author) throws ServiceException {
        if (author.getName() == null) {
            throw new ServiceException("Not valid data");
        }
        if (author.getInfo() == null) {
            author.setInfo("");
        }
    }
}
