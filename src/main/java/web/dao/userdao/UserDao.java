package web.dao.userdao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import java.util.List;


public interface UserDao {
    User getUserById(int id);
    List<User> getAllUsers();
    void deleteUser(int id);
    void updateUser(User user);
    void insertUser(User user);
    User loadUserByUsername(String name);
    Role getRole(String name_role);
}
