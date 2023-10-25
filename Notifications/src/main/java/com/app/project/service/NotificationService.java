package com.app.project.service;

import com.app.project.dao.NotificationDAO;
import com.app.project.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static com.betfairteam.UserProfile.util.Validators.*;

@Service
public class NotificationService {
    @Autowired
    private NotificationDAO userDAO;

    public void register(Notification user) {
        if(!user.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email");
        }
        if(user.getPassword().length() < 8) {
            throw new RuntimeException("Password too short");
        }
        if(!passwordSpecialCharacterValidator(user.getPassword())){
            throw new RuntimeException("Password should contain special characters");
        }
        if(!passwordNumberValidator(user.getPassword())){
            throw new RuntimeException("Password should contain numbers");
        }
        if(Period.between(user.getBirthdate(), LocalDate.now()).getYears() < 18) {
            throw new RuntimeException("You should be at least 18 years old");
        }
        userDAO.save(user);
    }

    public void login(Notification user) {
        Optional<Notification> optionalUser = userDAO.findById(user.getUserId());
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }

    public Notification getUser(Long id) {
        Optional<Notification> optionalUser = userDAO.findById(id);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return optionalUser.get();
    }

    public void updateUser(Notification user) {
        Optional<Notification> optionalUser = userDAO.findById(user.getUserId());
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }
}
