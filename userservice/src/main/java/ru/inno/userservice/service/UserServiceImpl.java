package ru.inno.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.userservice.model.User;


import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private  UserDaoImpl userDaoImpl;
    @Autowired
    public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }
    @Override
    public void createUser(Long id, String name) {
        userDaoImpl.addUser(id, name);
    }
    @Override
    public void deleteUser(Long id) {
        userDaoImpl.deleteUser(id);
    }
    @Override
    public List<User> selectUsers() {
        return userDaoImpl.getAllUsers();
    }
    @Override
    public void updateUser(Long id, String newName) {
        userDaoImpl.updateUser(id, newName);
    }
    @Override
    public Integer maxId() {
        return userDaoImpl.maxIdUsers();
    }
    @Override
    public User getUserById(Long id) {
        return userDaoImpl.getUserById(id);
    }
}
