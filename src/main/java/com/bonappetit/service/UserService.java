package com.bonappetit.service;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;

import java.util.List;

public interface UserService {
    boolean register(UserRegisterDTO data);

    public boolean login(UserLoginDTO data);
    public void logout();

   public List<Recipe> findFavourites(long id);
}
