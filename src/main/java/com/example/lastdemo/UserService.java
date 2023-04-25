package com.example.lastdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public List<User> readAllUsers() {
        return userDAO.findAll();
    }
    ObjectMapper objectMapper;

    public User getUserById(Integer id){
        Optional<User> userOptional = userDAO.findById(id);
        if (userOptional.isPresent())return userOptional.get();
        else return null;
    }
    public User addUser(User user){
        return userDAO.save(user);
    }
    public void deleteUserById(Integer id) {
        userDAO.deleteById(id);
    }
    public void putUser(User user,Integer id){
        Optional<User> userOptional;
        userOptional = userDAO.findById(id);
        if ((userOptional.isPresent())){
            userDAO.deleteById(id);
            userDAO.save(user);
        }else {
            userDAO.save(user);
        }
    }
    public User applyPatchtoUser(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }
}
