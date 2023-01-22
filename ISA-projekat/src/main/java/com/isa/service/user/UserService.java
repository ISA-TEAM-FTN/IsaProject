package com.isa.service.user;

import com.isa.domain.dto.ChangePasswordDTO;
import com.isa.domain.dto.UserDTO;
import com.isa.domain.model.User;
import com.isa.enums.Role;

import java.util.List;

public interface UserService {

    User register(UserDTO userDTO);
    User add(UserDTO userDTO);
    Role stringToRole(String role);
    User getCurrentUser();
    User changePassword(ChangePasswordDTO changePasswordDTO);
    User updateProfile(UserDTO userDTO);
    List<User> findAllByRole(Role role);
    List<User> search(String term, Role role);
}
