package web.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.userdao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    @Override
    @Transactional
    public User getUserById(int id) {
        return dao.getUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        dao.deleteUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Transactional
    @Override
    public void insertUser(User user) {
        dao.insertUser(user);
    }

    @Transactional
    @Override
    public User loadUserByUsername(String name) {
        return dao.loadUserByUsername(name);
    }

    @Transactional
    @Override
    public Role getRole(String nameRole) {
        return dao.getRole(nameRole);
    }
}
