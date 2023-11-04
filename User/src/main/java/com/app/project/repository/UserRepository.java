package com.app.project.repository;

import com.app.project.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//
//    public default User findByEmail(String email) {
//        EntityManager entityManager = null;
//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
//        query.setParameter("email", email);
//        List<User> resultList = query.getResultList();
//        return resultList.isEmpty() ? null : resultList.get(0);
//    }
//
//    public default User findByUsername(String username) {
//        EntityManager entityManager = null;
//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
//        query.setParameter("username", username);
//        List<User> resultList = query.getResultList();
//        return resultList.isEmpty() ? null : resultList.get(0);
//    }
//
//    public default User findByPhoneNumber(String phoneNumber) {
//        EntityManager entityManager = null;
//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber", User.class);
//        query.setParameter("phoneNumber", phoneNumber);
//        List<User> resultList = query.getResultList();
//        return resultList.isEmpty() ? null : resultList.get(0);
//    }
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);
}