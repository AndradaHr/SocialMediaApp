package com.app.project.service;

import com.app.project.repository.MessageDAO;
import com.app.project.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static com.app.project.util.Validators.passwordNumberValidator;
import static com.app.project.util.Validators.passwordSpecialCharacterValidator;

@Service
public class MessageService {
    @Autowired
    private MessageDAO userDAO;

    public void register(Message user) {
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

    public void login(Message user) {
        Optional<Message> optionalUser = userDAO.findById(user.getUserId());
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }

    public Message getUser(Long id) {
        Optional<Message> optionalUser = userDAO.findById(id);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return optionalUser.get();
    }

    public void updateUser(Message user) {
        Optional<Message> optionalUser = userDAO.findById(user.getUserId());
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }
}
