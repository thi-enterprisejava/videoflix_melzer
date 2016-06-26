package de.thi.videoflix.services;

import de.thi.videoflix.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Stateless
public class UserService implements Serializable{

    @Inject
    EntityManager entityManager;

    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User as u", User.class);
        return query.getResultList();
    }

    public User findUsername(String username){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User as u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public User findId(Long userId){
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User as u WHERE u.userId = :userId", User.class);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }

    public User addUser(User user) throws NoSuchAlgorithmException {
        user.setPassword(encryptPassword(user.getPassword()));
        entityManager.persist(user);
        return user;

    }

    public void deleteUser(User user){
        User managedUser = entityManager.find(User.class, user.getUserId());
        entityManager.remove(managedUser);
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
            return Base64.getEncoder().encodeToString(
                    MessageDigest.getInstance("SHA-256").digest(password.getBytes()));
    }
}
