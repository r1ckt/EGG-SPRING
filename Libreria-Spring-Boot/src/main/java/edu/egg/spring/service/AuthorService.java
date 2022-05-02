package edu.egg.spring.service;

import edu.egg.spring.entity.Author;

import edu.egg.spring.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public void create(Author authorDTO)  {
        Author author = new Author();
        author.setName(authorDTO.getName());
        authorRepository.save(author);
    }

    @Transactional
    public void update(Author authorDTO) {
        Author author = authorRepository.findById(authorDTO.getId()).get();
        author.setName(authorDTO.getName());
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Author getById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Long id) { authorRepository.deleteById(id);
    }
}