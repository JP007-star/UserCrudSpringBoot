package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired  private UserRepository repository;

    public List<User> listAll(){
        return (List<User>) repository.findAll();
    }

    public void save(User user) {
       repository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> user=repository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException("Could not found User with ID");
    }

    public void delete(Integer userId) {
        repository.deleteById(userId);
    }
}
