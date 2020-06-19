package web.dao.userdao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getUserById(int id) {
        Query query = entityManager.createQuery("select user from User user join fetch user.roleSet where user.id =: id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select distinct user from User user join fetch user.roleSet").getResultList();
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("delete from User  where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void insertUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User loadUserByUsername(String name) {
        Query query = entityManager.createQuery("select distinct user from User user join fetch user.roleSet where user.name = :name");
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }

    @Override
    public Role getRole(String nameRole) {
        Query query = entityManager.createQuery("select role from Role role where role.role =: role");
        query.setParameter("role", nameRole);
        return (Role) query.getSingleResult();
    }
}
