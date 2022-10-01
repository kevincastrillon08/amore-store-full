package com.project.sena.amorestore.services;

import com.project.sena.amorestore.models.User;
import com.project.sena.amorestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

     public List<User> findAll() {
         return userRepository.findAll();
     }

     public User findOne (Long id) {
         return userRepository.getReferenceById(id);
     }
}
