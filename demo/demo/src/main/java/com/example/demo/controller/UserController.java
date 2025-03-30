package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService serv;
    @PostMapping
    public ResponseEntity<?> insertUser(@RequestBody User u)
    {
        String validationError = serv.validateUser(u);
        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        User savedUser = serv.createUser(u);
        return ResponseEntity.ok(savedUser);
    }
    @GetMapping
    public List<User>getUser()
    {
        return serv.getAllUser();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id)
    {
        serv.getById(id);
        Optional<User> user = serv.getById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User with ID " + id + " not found.");
        }
    }
    @DeleteMapping("/{id}")
    public String DeleteEmployee(@PathVariable Long id)
    {
        return serv.DeleteById(id);
    }
    @PutMapping("/{id}")
    public User UpdateUser(@PathVariable Long id,@RequestBody User u)
    {
        return serv.UpdateUser(id,u);
    }
    @GetMapping("/page")
    public Page<User>getByPage(@RequestParam (defaultValue="0") int page,@RequestParam (defaultValue = "5") int size)
    {
        return serv.getUserByPage(page, size);
    }
    @GetMapping("/sort")
    public List<User>sortByUser()
    {
        return serv.sortByUser();
    }
    @GetMapping("/jpql/{name}")
    public List<User>getUsername(@PathVariable String name)
    {
        return serv.getByQuery(name);
    }
    @GetMapping("/custom/{email}")
    public List<User> findByEmail(@PathVariable String email)
    {
        return serv.getByEmail(email);
    }
}