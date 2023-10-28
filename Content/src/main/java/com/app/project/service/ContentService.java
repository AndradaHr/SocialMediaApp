package com.app.project.service;

import com.app.project.repository.ContentDAO;
import com.app.project.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static com.app.project.util.Validators.passwordNumberValidator;
import static com.app.project.util.Validators.passwordSpecialCharacterValidator;

@Service
public class ContentService {
    @Autowired
    private ContentDAO userDAO;

    public void register(Content user) {
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

    public void login(Content user) {
        Optional<Content> optionalUser = userDAO.findById(user.getUserId());
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }

    public Content getUser(Long id) {
        Optional<Content> optionalUser = userDAO.findById(id);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return optionalUser.get();
    }

    public void updateUser(Content user) {
        Optional<Content> optionalUser = userDAO.findById(user.getUserId());
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }
}
