package com.betfairteam.UserProfile;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

import static com.betfairteam.UserProfile.Validators.*;

@Service
public class UserService {
    public void registerUser(User user) {
        if(!user.getEmail().contains("@")){
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
    }
    public void login(User user) {

    }
}
