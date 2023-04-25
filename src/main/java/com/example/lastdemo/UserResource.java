package com.example.lastdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/v1/users";
    ObjectMapper objectMapper;
    @Autowired
    UserController userController;

    @GetMapping
    public List<UserDTO> users(){
        return userController.readAll();
    }

    @GetMapping("/{id}")
    public UserDTO user(@PathVariable Integer id){
        return userController.getUserById(id);
    }
    @GetMapping("/{id}/email")
    public Map<String,String> email(@PathVariable Integer id){
        return Collections.singletonMap("email",userController.getUserById(id).getEmail());
    }
    @PostMapping
    public UserDTO newUser(@RequestBody UserDTO userDTO){
        return userController.addUser(userDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Integer id) {
       userController.deleteUserById(id);
    }
    @PutMapping("/{id}")
    public void replace(@RequestBody UserDTO userDTO,@PathVariable Integer id) {
       userController.putUser(userDTO,id);
    }
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public void updateUser(@PathVariable Integer id,@RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        userController.patchUser(id,patch);


    }


}
