package com.joe.accountservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joe.accountservice.model.User;
import com.joe.accountservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public List<User> find(){
    	return userService.find();
    }
    
    @GetMapping("/{username}/password/{password}")
    public ResponseEntity<?> get(@PathVariable String username,@PathVariable String password){
    	User user = userService.get(username, password);
    	if(user!=null){
    		return ResponseEntity.ok(user);
    	}else{
    		return ResponseEntity.notFound().build();    		
    	}
    }
    
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
    	User u = userService.createBuyer(user);
        return ResponseEntity.ok(u);
    }
}
