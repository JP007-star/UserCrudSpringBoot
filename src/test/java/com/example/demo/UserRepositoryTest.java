package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repository;
    @Test
    public void testAddUser(){
        User user=new User();

        user.setEmail("jay@gmail.com");
        user.setPassword("password123");
        user.setFirstName("jp");
        user.setLastName("prasad");
        User savedUser=repository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users=repository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user :users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer usedId=1;
        Optional<User> optionalUser=repository.findById(usedId);
        User user= optionalUser.get();
        user.setPassword("logashwari");
        repository.save(user);

        User updatedUser=repository.findById(usedId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("logashwari");

    }
    @Test
    public void testGet(){
        Integer userId=1;
        Optional<User> optionalUser=repository.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId=5;
        repository.deleteById(userId);
        Optional<User> optionalUser=repository.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
