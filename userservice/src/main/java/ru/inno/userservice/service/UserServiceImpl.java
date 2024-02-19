package ru.inno.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.userservice.model.User;


import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private  UserDao userDao;
@Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(Long id, String name) {
        userDao.addUser(id, name);
    }
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
    @Override
    public List<User> selectUsers() {
        return userDao.getAllUsers();
    }
    @Override
    public void updateUser(Long id, String newName) {
        userDao.updateUser(id, newName);
    }
    @Override
    public Integer maxId() {
        return userDao.maxIdUsers();
    }
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
