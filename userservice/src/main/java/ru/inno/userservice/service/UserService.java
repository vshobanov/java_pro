package ru.inno.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.userservice.model.User;

import java.util.List;

public interface UserService {

    void createUser(Long id, String name);
    void deleteUser(Long id);
    List<User> selectUsers();
    void updateUser(Long id, String newName);
    Integer maxId();
    User getUserById(Long id);
}
