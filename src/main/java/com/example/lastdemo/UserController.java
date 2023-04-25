package com.example.lastdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    public List<UserDTO> readAll() {
        List<User> users = userService.readAllUsers();
       return users.stream().map(UserDTO::new).toList();

    }

    public UserDTO getUserById(Integer id){
        User user = userService.getUserById(id);
        return new UserDTO(user);
    }

    public UserDTO addUser(UserDTO userDTO){
        User user = new User(userDTO);
        return new UserDTO(userService.addUser(user));
    }
    public void deleteUserById(Integer id) {
        userService.deleteUserById(id);
    }
    public void putUser(UserDTO userDTO,Integer id) {
        User user = new User(userDTO);
        userService.putUser(user,id);
    }
    public void patchUser(Integer id, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        User user = new User(getUserById(id));
        User userPathcher = userService.applyPatchtoUser(jsonPatch,user);
        userService.addUser(userPathcher);
    }

}
