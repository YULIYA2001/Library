package com.golubovich.library.spring.service;

import com.golubovich.library.spring.model.Genre;
import com.golubovich.library.spring.repository.GenreRepository;
import com.golubovich.library.spring.service.api.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre add(Genre genre) throws ServiceException {
        validateGenre(genre);
        return genreRepository.save(genre);
    }

    @Override
    public Genre changeDescription(long id, String description) throws ServiceException {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre != null) {
            genre.setDescription(description);
        } else {
            throw new ServiceException("Id is not found");
        }
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> showAll() throws ServiceException {
        return genreRepository.findAllWithCustomOrderBy(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Genre takeById(Long id) throws ServiceException {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre != null) {
            return genre;
        } else {
            throw new ServiceException("No genre with id");
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre != null) {
            genreRepository.delete(genre);
        } else {
            throw new ServiceException("Can't delete genre. Id is not found");
        }
    }

    private void validateGenre(Genre genre) throws ServiceException {
        if (genre.getName() == null) {
            throw new ServiceException("Not valid data");
        }
        if (genre.getDescription() == null) {
            genre.setDescription("");
        }
    }
}
