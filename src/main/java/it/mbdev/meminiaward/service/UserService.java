package it.mbdev.meminiaward.service;

import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String username){
       return userRepository.findByUsername(username);
    }

    public void saveUser(User u){
        userRepository.save(u);
    }

    public List<User> listUsers() {

        return userRepository.findAll();
    }
}
