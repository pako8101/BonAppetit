package com.bonappetit.service.impl;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.UserService;
import com.bonappetit.service.UserSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSession userSession;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserSession userSession, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        Optional<User> exitingUser =
        userRepository
                .findByUsernameOrEmail(data.getUsername(),
                        data.getEmail());

        if (exitingUser.isPresent()){
            return false;
        }

User user = new User();

user.setUsername(data.getUsername());
user.setEmail(data.getEmail());
user.setPassword(passwordEncoder.encode(data.getPassword()));
userRepository.save(user);
        return true;
    }

    @Override

    public boolean login(UserLoginDTO data) {
Optional<User> registeredUser = userRepository.findByUsername(data.getUsername());

if (registeredUser.isEmpty()){
    return false;
}
if (passwordEncoder.matches(data.getPassword(),registeredUser.get().getPassword())){
    return false;
        }

userSession.login(registeredUser.get());

        return true;
    }
    public void logout(){
        userSession.logout();
    }

    @Override
    @Transactional
    public List<Recipe> findFavourites(long id) {
      return   userRepository.findById(id)
                .map(User::getFavouriteRecipes)
                .orElseGet(ArrayList::new);


//        Optional<User> byId =
//                userRepository.findById(id);
//        if (byId.isEmpty()){
//            return new ArrayList<>();
//        }
//return byId.get().getFavouriteRecipes();
    }
}
