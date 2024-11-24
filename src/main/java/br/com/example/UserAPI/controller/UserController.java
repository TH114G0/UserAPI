package br.com.example.UserAPI.controller;

import br.com.example.UserAPI.entity.UserEntity;
import br.com.example.UserAPI.exception.UserException;
import br.com.example.UserAPI.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<UserEntity> create(@RequestBody UserEntity userEntity) {
        UserEntity createUser = userService.createUSer(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @GetMapping
    List<UserEntity> list() {
        return userService.listUser();
    }

    @PutMapping("{id'}")
    ResponseEntity<UserEntity> update(@PathVariable("id") Long id, @RequestBody UserEntity userEntity) {
        try {
            userEntity.setId(id);
            UserEntity updateEntity = userService.updateUser(userEntity);
            return ResponseEntity.ok(updateEntity);
        } catch (UserException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}