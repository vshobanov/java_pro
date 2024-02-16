package ru.inno.userservice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.inno.userservice.model.User;


import java.util.List;
@Component
public class UserService {

    private UserDaoImpl userDaoImpl;
@Autowired
    public void setUserDao(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }
    public void createUser(Long id,String name) {
        userDaoImpl.addUser(id,name);
    }
    public void deleteUser(Long id) {
        userDaoImpl.deleteUser(id);
    }
    public List<User> selectUsers() {
      return  userDaoImpl.getAllUsers();
    }
    public void updateUser(Long id,String newName) {
        userDaoImpl.updateUser(id,newName);
    }
    public Integer maxId() {
        return  userDaoImpl.maxIdUsers();
    }
    public User getUserById(Long id) {
        return  userDaoImpl.getUserById(id);
    }
}
