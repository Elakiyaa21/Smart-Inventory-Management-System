package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;



@Service
public class UserService {
    @Autowired
    UserRepository userrepo;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public UserService(UserRepository userrepo) {
        this.userrepo = userrepo;
    }

    public String validateUser(User user) {
        if (user.getId() <= 0) {
            return "ID must be a positive number.";
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return "Username cannot be empty.";
        }

        if (userrepo.existsByUsername(user.getUsername())) {
            return "Username already exists.";
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return "Email cannot be empty.";
        }

        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            return "Invalid email format.";
        }

        if (userrepo.existsByEmail(user.getEmail())) {
            return "Email already exists.";
        }

        return null; // No validation errors
    }
    public User createUser(User u)
    {
        return userrepo.save(u);
    }
    public List<User>getAllUser()
    {
        return userrepo.findAll();
    }
    public Optional<User> getById(long id)
    {
        return userrepo.findById(id);
    }
    public User UpdateUser(Long id,User u)
    {
        u.setId(id);
        return userrepo.save(u);
    }
    public String DeleteById(long id)
    {
        userrepo.deleteById(id);
        return "Success";
    }
    public Page<User> getUserByPage(int page,int size)
    {
        Pageable pageable=PageRequest.of(page, size);
        return userrepo.findAll(pageable);
    }
    public List<User>sortByUser()
    {
       // return userrepo.findAll(Sort.by(Sort.Direction.ASC, properties:"username"));
       return userrepo.findAll(Sort.by(Sort.Direction.ASC, "username"));
    }
    public List<User>getByQuery(String name)
    {
        return userrepo.findByUsername(name);
    }
    public List<User> getByEmail(String email)
    {
        return userrepo.findByemail(email);
    }
    
}