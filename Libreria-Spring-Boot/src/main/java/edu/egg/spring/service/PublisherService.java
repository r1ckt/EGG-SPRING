package edu.egg.spring.service;

import edu.egg.spring.entity.Publisher;

import edu.egg.spring.repository.PublisherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublisherService {
    
    @Autowired
    PublisherRepository publisherRepository;

    @Transactional
    public void create(Publisher publisherDTO)  {

        Publisher publisher = new Publisher();
        publisher.setName(publisherDTO.getName());
        publisher.setStatus(publisherDTO.getStatus());

        publisherRepository.save(publisher);
    }

    @Transactional
    public void update(Publisher publisherDTO) {
        Publisher publisher = publisherRepository.findById(publisherDTO.getId()).get();

        publisher.setName(publisherDTO.getName());

        publisherRepository.save(publisher);
    }

    @Transactional(readOnly = true)
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Publisher getById(Long id) {
        return publisherRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Long id) { publisherRepository.deleteById(id);
    }
}
